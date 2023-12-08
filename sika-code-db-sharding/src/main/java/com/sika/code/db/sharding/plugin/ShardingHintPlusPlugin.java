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
import org.apache.ibatis.executor.CachingExecutor;
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
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
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
    private static final String PARAM1_KEY = "param1";
    private static final String DATA_SOURCE_KEY = "dataSource";
    private static final String DATABASE_NAME_KEY = "databaseName";
    private static final String CONTEXT_MANAGER_KEY = "contextManager";
    private static final String SHARDING_ALGORITHM_KEY = "sharding-algorithm";
    private static final String SHARDING_COLUMNS_KEY = "sharding-columns";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.debug("准备-执行Hint加强逻辑-判断是否已经被hint处理-避免重复处理");
        if (HintManager.isInstantiated()) {
            return invocation.proceed();
        }
        logger.debug("执行Hint加强逻辑-获取分片算法规则");
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
        // 加强的表分片算法
        ShardingAlgorithm plusTableShardingAlgorithm = shardingAlgorithmRule.getPlusTableShardingAlgorithm();
        // 目标列的分片算法
        ShardingAlgorithm targetColumnTableShardingAlgorithm =
            shardingAlgorithmRule.getTargetColumnTableShardingAlgorithm();
        // 分片表的规则
        TableRule tableRule = shardingAlgorithmRule.getTableRule();
        // 可用的表名称
        Collection<String> availableTableNames =
            tableRule.getActualDataNodes().stream().map(DataNode::getTableName).distinct().collect(Collectors.toList());

        hintPlusDataSourceOrTable(invocation, tableRule, plusTableShardingAlgorithm, targetColumnTableShardingAlgorithm,
            availableTableNames, s -> hintManager.addTableShardingValue(tableRule.getLogicTable(), s));
    }

    /**
     * 加强数据源、表hint
     *
     * @param invocation                    - 执行的invoke
     * @param tableRule                     - 表规则
     * @param plusShardingAlgorithm         - 加强的分片算法
     * @param targetColumnShardingAlgorithm - 目标列分片算法
     * @param availableTargetNames          - 可用的目标名称
     * @param hintInvoke                    - 执行加强逻辑
     */
    public void hintPlusDataSourceOrTable(Invocation invocation, TableRule tableRule,
        ShardingAlgorithm plusShardingAlgorithm, ShardingAlgorithm targetColumnShardingAlgorithm,
        Collection<String> availableTargetNames, Consumer<String> hintInvoke) {
        // 分片算法为非hintPlus无需加强
        if (!(plusShardingAlgorithm instanceof HintInlinePlusShardingAlgorithm)) {
            return;
        }
        if (targetColumnShardingAlgorithm == null) {
            return;
        }
        // 获取执行参数
        Object param = getParam(invocation);

        String sharindFullName =
            getFullShardingName(param, tableRule, plusShardingAlgorithm, targetColumnShardingAlgorithm,
                availableTargetNames);
        if (StrUtil.isBlank(sharindFullName)) {
            return;
        }
        hintInvoke.accept(sharindFullName);
    }

    private void hintPlusDatasource(Invocation invocation, ShardingAlgorithmRule shardingAlgorithmRule,
        HintManager hintManager) {
        ShardingAlgorithm plusDatasourceShardingAlgorithm = shardingAlgorithmRule.getPlusDatasourceShardingAlgorithm();
        ShardingAlgorithm targetColumnDatasourceShardingAlgorithm =
            shardingAlgorithmRule.getTargetColumnDatasourceShardingAlgorithm();
        TableRule tableRule = shardingAlgorithmRule.getTableRule();

        hintPlusDataSourceOrTable(invocation, tableRule, plusDatasourceShardingAlgorithm,
            targetColumnDatasourceShardingAlgorithm, tableRule.getActualDataSourceNames(),
            s -> hintManager.addTableShardingValue(tableRule.getLogicTable(), s));
    }

    private Object getParam(Invocation invocation) {
        MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>)invocation.getArgs()[1];
        return paramMap.get(PARAM1_KEY);
    }

    /**
     * 获取完整的目标分片名称
     *
     * @param params                        - 需要传入的处理对象-如查询对象、更新对象等等
     * @param tableRule                     - 分片规则
     * @param plusShardingAlgorithm         - 加强的分片算法
     * @param targetColumnShardingAlgorithm - 列名目标分片算法StandardShardingAlgorithm和ComplexKeysShardingAlgorithm
     * @param availableTargetNames          - 可用的目标名称
     * @return
     */
    private String getFullShardingName(Object params, TableRule tableRule, ShardingAlgorithm plusShardingAlgorithm,
        ShardingAlgorithm targetColumnShardingAlgorithm, Collection<String> availableTargetNames) {
        // 此处获取分片列与分片值的映射
        Map<String, Collection<Comparable<?>>> datasourceShardingValuesMap =
            getShardingValues(params, plusShardingAlgorithm);
        if (CollUtil.isEmpty(datasourceShardingValuesMap) || CollUtil.isEmpty(datasourceShardingValuesMap.values())) {
            return null;
        }

        if (targetColumnShardingAlgorithm instanceof StandardShardingAlgorithm) {
            StandardShardingAlgorithm<?> standardShardingAlgorithm =
                (StandardShardingAlgorithm<?>)targetColumnShardingAlgorithm;
            PreciseShardingValue preciseShardingValue = new PreciseShardingValue<>(tableRule.getLogicTable(),
                datasourceShardingValuesMap.keySet().iterator().next(), tableRule.getTableDataNode(),
                datasourceShardingValuesMap.values().iterator().next().iterator().next());
            return standardShardingAlgorithm.doSharding(availableTargetNames, preciseShardingValue);
        } else if (targetColumnShardingAlgorithm instanceof ComplexKeysShardingAlgorithm) {
            ComplexKeysShardingAlgorithm<?> complexKeysShardingAlgorithm =
                (ComplexKeysShardingAlgorithm<?>)targetColumnShardingAlgorithm;
            ComplexKeysShardingValue comparableComplexKeysShardingValue =
                new ComplexKeysShardingValue<>(tableRule.getLogicTable(), datasourceShardingValuesMap, null);
            return (String)complexKeysShardingAlgorithm.doSharding(availableTargetNames,
                comparableComplexKeysShardingValue).iterator().next();
        } else {
            throw new RuntimeException("不支持的分片算法类型");
        }

    }

    private ShardingAlgorithmRule getShardingAlgorithm(Invocation invocation) {
        // 获取原始sql解析出tableName
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        String originalSql = mappedStatement.getBoundSql(invocation.getArgs()[1]).getSql();
        TableNameParser parser = new TableNameParser(originalSql);
        parser.accept(new ArrayList<>()::add);
        String tableName = parser.tables().stream().findFirst().orElse(null);
        if (StrUtil.isBlank(tableName)) {
            return null;
        }

        // 获取当前的数据源
        SpringManagedTransaction executor =
            (SpringManagedTransaction)((CachingExecutor)invocation.getTarget()).getTransaction();
        DataSource dataSource = (DataSource)ReflectUtil.getFieldValue(executor, DATA_SOURCE_KEY);

        // 获取shardingSphereRule列表拿到分片规则列表
        return buildShardingAlgorithmRule(dataSource, tableName);
    }

    /**
     * 根据数据源和表名构建算法规则
     *
     * @param dataSource - 数据源
     * @param tableName  - 表名
     * @return
     */
    private ShardingAlgorithmRule buildShardingAlgorithmRule(DataSource dataSource, String tableName) {
        if (!(dataSource instanceof ShardingSphereDataSource)) {
            return null;
        }
        // 获取shardingSphereRule列表拿到分片规则列表
        ContextManager contextManager = (ContextManager)ReflectUtil.getFieldValue(dataSource, CONTEXT_MANAGER_KEY);
        String databaseName = (String)ReflectUtil.getFieldValue(dataSource, DATABASE_NAME_KEY);
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

    /**
     * 获取分片算法规则
     *
     * @param shardingSphereRule
     * @param tableName
     * @return
     */
    private ShardingAlgorithmRule getShardingAlgorithmRuleFromRule(ShardingSphereRule shardingSphereRule,
        String tableName) {
        if (!(shardingSphereRule instanceof ShardingRule)) {
            return null;
        }
        TableRule tableRule = ((ShardingRule)shardingSphereRule).getTableRule(tableName);
        if (tableRule == null) {
            return null;
        }
        Map<String, ShardingAlgorithm> shardingAlgorithmMap =
            ((ShardingRule)shardingSphereRule).getShardingAlgorithms();
        ShardingAlgorithmRule shardingAlgorithmRule = new ShardingAlgorithmRule().setTableRule(tableRule);
        // 设置数据源算法
        if (tableRule.getDatabaseShardingStrategyConfig() != null) {
            ShardingAlgorithm plusDataSourceShardingAlgorithm =
                shardingAlgorithmMap.get(tableRule.getDatabaseShardingStrategyConfig().getShardingAlgorithmName());
            ShardingAlgorithm targetColumnDatasourceShardingAlgorithm =
                shardingAlgorithmMap.get(plusDataSourceShardingAlgorithm.getProps().getProperty(SHARDING_ALGORITHM_KEY));
            shardingAlgorithmRule.setPlusDatasourceShardingAlgorithm(plusDataSourceShardingAlgorithm)
                .setTargetColumnDatasourceShardingAlgorithm(targetColumnDatasourceShardingAlgorithm);
        }
        // 设置数据表算法
        if (tableRule.getTableShardingStrategyConfig() != null) {
            ShardingAlgorithm plusTableShardingAlgorithm =
                shardingAlgorithmMap.get(tableRule.getTableShardingStrategyConfig().getShardingAlgorithmName());
            ShardingAlgorithm targetColumnTableShardingAlgorithm =
                shardingAlgorithmMap.get(plusTableShardingAlgorithm.getProps().getProperty(SHARDING_ALGORITHM_KEY));
            shardingAlgorithmRule.setPlusTableShardingAlgorithm(plusTableShardingAlgorithm)
                .setTargetColumnTableShardingAlgorithm(targetColumnTableShardingAlgorithm);
        }
        return shardingAlgorithmRule;
    }

    private Map<String, Collection<Comparable<?>>> getShardingValues(Object param,
        ShardingAlgorithm shardingAlgorithm) {
        if (param == null) {
            return null;
        }
        // 集合不支持加强
        if (param instanceof Collection) {
            return null;
        }
        // 解析分片列名
        Properties properties = shardingAlgorithm.getProps();
        Map<String, String> shardingColumnsMap =
            ShardingUtils.yamlMapPropToMap(properties.getProperty(SHARDING_COLUMNS_KEY));
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
            Object value = ReflectUtil.getFieldValue(param, field);
            if (value instanceof Comparable) {
                shardingValues.put(columnName, Lists.newArrayList((Comparable<?>)value));
            }
        }
        return shardingValues;
    }

    @Data
    @Accessors(chain = true)
    public static class ShardingAlgorithmRule {
        private ShardingAlgorithm plusDatasourceShardingAlgorithm;
        private ShardingAlgorithm plusTableShardingAlgorithm;
        private ShardingAlgorithm targetColumnDatasourceShardingAlgorithm;
        private ShardingAlgorithm targetColumnTableShardingAlgorithm;
        private TableRule tableRule;
    }

}