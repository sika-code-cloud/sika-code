package com.sika.code.db.sharding.plugin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.google.common.collect.Maps;
import com.sika.code.db.sharding.algorithm.key.hint.HintInlinePlusShardingAlgorithm;
import com.sika.code.db.sharding.utils.ShardingUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.datanode.DataNode;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.infra.rule.ShardingSphereRule;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.rule.ShardingRule;
import org.apache.shardingsphere.sharding.rule.TableRule;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * ShardingHintPlusPlugin hint分片策略增强插件 - 支持按照
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
            BoundSql.class}), @Signature(type = Executor.class, method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ShardingHintPlusPlugin implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.debug("准备-执行Hint加强逻辑");
        if (HintManager.isInstantiated()) {
            return invocation.proceed();
        }
        logger.debug("执行Hint加强逻辑");
        ShardingAlgorithmRule shardingAlgorithmRule = getShardingAlgorithm(invocation);
        if (shardingAlgorithmRule == null) {
            return invocation.proceed();
        }
        try (HintManager hintManager = HintManager.getInstance()) {
            // 加强datasource
            hintPlusDatasource(invocation, shardingAlgorithmRule, hintManager);
            // 加强table
            hintPlusTable(invocation, shardingAlgorithmRule, hintManager);
            return invocation.proceed();
        }
    }

    private void hintPlusTable(Invocation invocation, ShardingAlgorithmRule shardingAlgorithmRule,
        HintManager hintManager) {
        ShardingAlgorithm shardingAlgorithm = shardingAlgorithmRule.getTargetTableShardingAlgorithm();
        if (shardingAlgorithm instanceof HintInlinePlusShardingAlgorithm) {
            TableRule tableRule = shardingAlgorithmRule.getTableRule();
            Collection<String> availableTableNames =
                tableRule.getActualDataNodes().stream().map(DataNode::getTableName).distinct()
                    .collect(Collectors.toList());
            Map<String, Collection<Comparable<?>>> tableShardingValuesMap =
                getShardingValues(invocation, shardingAlgorithm);
            String tableShardingName = getFullShardingName(tableRule, tableShardingValuesMap,
                shardingAlgorithmRule.getPlusTableShardingAlgorithm(), availableTableNames);
            hintManager.addTableShardingValue(tableRule.getLogicTable(), tableShardingName);
        }
    }

    private void hintPlusDatasource(Invocation invocation, ShardingAlgorithmRule shardingAlgorithmRule,
        HintManager hintManager) {
        ShardingAlgorithm shardingAlgorithm = shardingAlgorithmRule.getTargetDatasourceShardingAlgorithm();
        if (shardingAlgorithm instanceof HintInlinePlusShardingAlgorithm) {
            TableRule tableRule = shardingAlgorithmRule.getTableRule();
            Map<String, Collection<Comparable<?>>> databaseShardingValuesMap =
                getShardingValues(invocation, shardingAlgorithm);
            String datasourceName = getFullShardingName(shardingAlgorithmRule.getTableRule(), databaseShardingValuesMap,
                shardingAlgorithmRule.getPlusDatasourceShardingAlgorithm(), tableRule.getActualDataSourceNames());
            hintManager.addDatabaseShardingValue(tableRule.getLogicTable(), datasourceName);
        }
    }

    private String getFullShardingName(TableRule tableRule, Map<String, Collection<Comparable<?>>> shardingValueMaps,
        ShardingAlgorithm plusShardingAlgorithm, Collection<String> availableTargetNames) {
        if (plusShardingAlgorithm == null) {
            return null;
        }
        if (plusShardingAlgorithm instanceof StandardShardingAlgorithm) {
            StandardShardingAlgorithm<?> standardShardingAlgorithm =
                (StandardShardingAlgorithm<?>)plusShardingAlgorithm;
            PreciseShardingValue preciseShardingValue =
                new PreciseShardingValue<>(tableRule.getLogicTable(), shardingValueMaps.keySet().iterator().next(),
                    tableRule.getTableDataNode(), shardingValueMaps.values().iterator().next().iterator().next());
            return standardShardingAlgorithm.doSharding(availableTargetNames, preciseShardingValue);
        } else if (plusShardingAlgorithm instanceof ComplexKeysShardingAlgorithm) {
            ComplexKeysShardingAlgorithm<?> complexKeysShardingAlgorithm =
                (ComplexKeysShardingAlgorithm<?>)plusShardingAlgorithm;
            ComplexKeysShardingValue comparableComplexKeysShardingValue =
                new ComplexKeysShardingValue<>(tableRule.getLogicTable(), shardingValueMaps, null);
            return (String)complexKeysShardingAlgorithm.doSharding(availableTargetNames,
                comparableComplexKeysShardingValue).iterator().next();
        } else {
            throw new RuntimeException("不支持的分片算法类型");
        }

    }

    private ShardingAlgorithmRule getShardingAlgorithm(Invocation invocation) {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        String originalSql = mappedStatement.getBoundSql(invocation.getArgs()[1]).getSql();

        TableNameParser parser = new TableNameParser(originalSql);
        parser.accept(new ArrayList<>()::add);
        String tableName = parser.tables().stream().findFirst().orElse(null);
        if (StrUtil.isBlank(tableName)) {
            return null;
        }
        DataSource dataSource = mappedStatement.getConfiguration().getEnvironment().getDataSource();
        if (!(dataSource instanceof ShardingSphereDataSource)) {
            return null;
        }
        // 获取shardingSphereRule列表
        ShardingSphereDataSource shardingSphereDataSource = (ShardingSphereDataSource)dataSource;
        ContextManager contextManager =
            (ContextManager)ReflectUtil.getFieldValue(shardingSphereDataSource, "contextManager");
        String databaseName = (String)ReflectUtil.getFieldValue(shardingSphereDataSource, "databaseName");
        Collection<ShardingSphereRule> rules =
            contextManager.getMetaDataContexts().getMetaData().getDatabase(databaseName).getRuleMetaData().getRules();
        // 轮询获取分表分片算法-构建分片规则
        for (ShardingSphereRule shardingSphereRule : rules) {
            ShardingAlgorithmRule shardingAlgorithmRule =
                getShardingAlgorithmRuleFromRule(shardingSphereRule, tableName);
            if (shardingAlgorithmRule != null) {
                return shardingAlgorithmRule;
            }
        }
        return null;
    }

    private ShardingAlgorithmRule getShardingAlgorithmRuleFromRule(ShardingSphereRule shardingSphereRule,
        String tableName) {
        if (shardingSphereRule instanceof ShardingRule) {
            ShardingRule shardingRule = (ShardingRule)shardingSphereRule;
            TableRule tableRule = shardingRule.getTableRule(tableName);
            if (tableRule == null) {
                return null;
            }
            Map<String, ShardingAlgorithm> shardingAlgorithmMap = shardingRule.getShardingAlgorithms();
            ShardingAlgorithmRule shardingAlgorithmRule = new ShardingAlgorithmRule().setTableRule(tableRule);
            if (tableRule.getDatabaseShardingStrategyConfig() != null) {
                ShardingAlgorithm dataSourceShardingAlgorithm =
                    shardingAlgorithmMap.get(tableRule.getDatabaseShardingStrategyConfig().getShardingAlgorithmName());
                ShardingAlgorithm plusDatasourceShardingAlgorithm =
                    shardingAlgorithmMap.get(dataSourceShardingAlgorithm.getProps().getProperty("sharding-algorithm"));
                shardingAlgorithmRule.setTargetDatasourceShardingAlgorithm(dataSourceShardingAlgorithm)
                    .setPlusDatasourceShardingAlgorithm(plusDatasourceShardingAlgorithm);
            }

            if (tableRule.getTableShardingStrategyConfig() != null) {
                ShardingAlgorithm tableShardingAlgorithm =
                    shardingAlgorithmMap.get(tableRule.getTableShardingStrategyConfig().getShardingAlgorithmName());
                ShardingAlgorithm plusTableShardingAlgorithm =
                    shardingAlgorithmMap.get(tableShardingAlgorithm.getProps().getProperty("sharding-algorithm"));
                shardingAlgorithmRule.setTargetTableShardingAlgorithm(tableShardingAlgorithm)
                    .setPlusTableShardingAlgorithm(plusTableShardingAlgorithm);
            }

            return shardingAlgorithmRule;
        }
        return null;
    }

    private Map<String, Collection<Comparable<?>>> getShardingValues(Invocation invocation,
        ShardingAlgorithm shardingAlgorithm) {
        MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>)invocation.getArgs()[1];
        Object param = paramMap.get("param1");
        // 集合不支持加强
        if (param instanceof Collection) {
            return null;
        }
        // 解析分片列名
        Properties properties = shardingAlgorithm.getProps();
        Map<String, String> shardingColumnsMap =
            ShardingUtils.yamlMapPropToMap(properties.getProperty("sharding-columns"));
        if (CollUtil.isEmpty(shardingColumnsMap)) {
            return null;
        }
        // 如果参数为map
        if (param instanceof Map) {
            return getShardingValuesFromMap((Map<?, ?>)param, shardingColumnsMap);
        }
        // 普通的POJO对象 - 先从属性中获取对应的值
        return getShardingValuesFromField(param, shardingColumnsMap);
    }

    private Map<String, Collection<Comparable<?>>> getShardingValuesFromMap(Map<?, ?> paramMap,
        Map<String, String> shardingColumnsMap) {
        Map<String, Collection<Comparable<?>>> shardingValues = Maps.newHashMap();
        for (Map.Entry<String, String> entry : shardingColumnsMap.entrySet()) {
            Comparable<?> sharingValue = (Comparable<?>)(paramMap).get(entry.getValue());
            if (sharingValue == null) {
                continue;
            }
            shardingValues.put(entry.getKey(), Lists.newArrayList(sharingValue));
        }
        return shardingValues;
    }

    private Map<String, Collection<Comparable<?>>> getShardingValuesFromField(Object param,
        Map<String, String> shardingColumnsMap) {
        // 普通的POJO对象 - 先从属性中获取对应的值
        Field[] fields = ReflectUtil.getFields(param.getClass());
        Map<String, Collection<Comparable<?>>> shardingValues = Maps.newHashMap();
        for (Field field : fields) {
            String columnName = null;
            for (Map.Entry<String, String> entry : shardingColumnsMap.entrySet()) {
                if (field.getName().equals(entry.getValue())) {
                    columnName = entry.getKey();
                }
            }
            if (StrUtil.isBlank(columnName)) {
                continue;
            }
            Comparable<?> value = (Comparable<?>)ReflectUtil.getFieldValue(param, field);
            if (value == null) {
                continue;
            }
            shardingValues.put(columnName, Lists.newArrayList(value));
        }
        return shardingValues;
    }

    @Data
    @Accessors(chain = true)
    public static class ShardingAlgorithmRule {
        private ShardingAlgorithm targetDatasourceShardingAlgorithm;
        private ShardingAlgorithm targetTableShardingAlgorithm;
        private ShardingAlgorithm plusDatasourceShardingAlgorithm;
        private ShardingAlgorithm plusTableShardingAlgorithm;
        private TableRule tableRule;
    }

}