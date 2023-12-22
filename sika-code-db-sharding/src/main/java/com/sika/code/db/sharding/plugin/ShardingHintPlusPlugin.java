package com.sika.code.db.sharding.plugin;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.datanode.DataNode;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.infra.rule.ShardingSphereRule;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.rule.ShardingRule;
import org.apache.shardingsphere.sharding.rule.TableRule;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
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
    private static final String DATABASE_NAME_KEY = "databaseName";
    private static final String CONTEXT_MANAGER_KEY = "contextManager";
    private static final String SHARDING_ALGORITHM_KEY = "sharding-algorithm";
    private static final String SHARDING_COLUMNS_KEY = "sharding-columns";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.debug("准备-执行Hint加强逻辑-判断是否已经被hint处理-避免重复处理");
        if (HintManager.isInstantiated() || HintManagerPlus.getColumnShardingValue() == null) {
            return invocation.proceed();
        }
        logger.debug("执行Hint加强逻辑-获取分片算法规则");
        ShardingAlgorithmRule shardingAlgorithmRule = getShardingAlgorithm(invocation);
        if (shardingAlgorithmRule == null) {
            return invocation.proceed();
        }
        // 没有配置分片规则直接返回
        if (ObjectUtils.allNull(shardingAlgorithmRule.getTargetColumnDatasourceShardingAlgorithm(),
            shardingAlgorithmRule.getTargetColumnTableShardingAlgorithm())) {
            return invocation.proceed();
        }
        try (HintManager hintManager = HintManager.getInstance()) {
            Map<String, Collection<Comparable<?>>> clientShardingValuesMap = getShardingValues();
            // 加强datasource
            hintPlusDatasource(clientShardingValuesMap, shardingAlgorithmRule, hintManager);
            // 加强table
            hintPlusTable(clientShardingValuesMap, shardingAlgorithmRule, hintManager);
            return invocation.proceed();
        }
    }

    private void hintPlusTable(Map<String, Collection<Comparable<?>>> clientShardingValuesMap,
        ShardingAlgorithmRule shardingAlgorithmRule, HintManager hintManager) {
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

        hintPlusTable(clientShardingValuesMap, tableRule, plusTableShardingAlgorithm,
            targetColumnTableShardingAlgorithm, availableTableNames, hintManager);
    }

    public void hintPlusTable(Map<String, Collection<Comparable<?>>> clientShardingValuesMap, TableRule tableRule,
        ShardingAlgorithm plusShardingAlgorithm, ShardingAlgorithm targetColumnShardingAlgorithm,
        Collection<String> availableTargetNames, HintManager hintManager) {
        if (targetColumnShardingAlgorithm == null) {
            return;
        }
        String shardingFullName =
            getFullShardingName(clientShardingValuesMap, tableRule, tableRule.getTableDataNode(), plusShardingAlgorithm,
                targetColumnShardingAlgorithm, availableTargetNames);
        if (StrUtil.isBlank(shardingFullName)) {
            return;
        }
        hintManager.addTableShardingValue(tableRule.getLogicTable(), shardingFullName);
    }

    public void hintPlusDataSource(Map<String, Collection<Comparable<?>>> clientShardingValuesMap, TableRule tableRule,
        ShardingAlgorithm plusShardingAlgorithm, ShardingAlgorithm targetColumnShardingAlgorithm,
        Collection<String> availableTargetNames, HintManager hintManager) {
        if (targetColumnShardingAlgorithm == null) {
            return;
        }
        // 获取执行参数

        String shardingFullName =
            getFullShardingName(clientShardingValuesMap, tableRule, tableRule.getDataSourceDataNode(),
                plusShardingAlgorithm, targetColumnShardingAlgorithm, availableTargetNames);
        if (StrUtil.isBlank(shardingFullName)) {
            return;
        }
        hintManager.addDatabaseShardingValue(tableRule.getLogicTable(), shardingFullName);
    }

    private void hintPlusDatasource(Map<String, Collection<Comparable<?>>> clientShardingValuesMap,
        ShardingAlgorithmRule shardingAlgorithmRule, HintManager hintManager) {
        ShardingAlgorithm plusDatasourceShardingAlgorithm = shardingAlgorithmRule.getPlusDatasourceShardingAlgorithm();
        ShardingAlgorithm targetColumnDatasourceShardingAlgorithm =
            shardingAlgorithmRule.getTargetColumnDatasourceShardingAlgorithm();
        TableRule tableRule = shardingAlgorithmRule.getTableRule();

        hintPlusDataSource(clientShardingValuesMap, tableRule, plusDatasourceShardingAlgorithm,
            targetColumnDatasourceShardingAlgorithm, tableRule.getActualDataSourceNames(), hintManager);
    }

    /**
     * 获取完整的目标分片名称
     *
     * @param clientShardingValuesMap       - 数据源map
     * @param tableRule                     - 分片规则
     * @param plusShardingAlgorithm         - 加强的分片算法
     * @param targetColumnShardingAlgorithm - 列名目标分片算法StandardShardingAlgorithm和ComplexKeysShardingAlgorithm
     * @param availableTargetNames          - 可用的目标名称
     * @return
     */
    private String getFullShardingName(Map<String, Collection<Comparable<?>>> clientShardingValuesMap,
        TableRule tableRule, DataNodeInfo dataNodeInfo, ShardingAlgorithm plusShardingAlgorithm,
        ShardingAlgorithm targetColumnShardingAlgorithm, Collection<String> availableTargetNames) {
        // 此处获取分片列与分片值的映射
        // 解析分片列名 - 未配置直接返回
        Properties properties = plusShardingAlgorithm.getProps();
        String shardingColumns = properties.getProperty(SHARDING_COLUMNS_KEY);
        if (StrUtil.isBlank(shardingColumns)) {
            return null;
        }
        String clientColumn = clientShardingValuesMap.keySet().iterator().next();
        if (!shardingColumns.contains(clientColumn)) {
            throw new RuntimeException("配置的分片列名：" + shardingColumns + "，不支持当前分片KEY：" + clientColumn);
        }

        if (targetColumnShardingAlgorithm instanceof StandardShardingAlgorithm) {
            StandardShardingAlgorithm<?> standardShardingAlgorithm =
                (StandardShardingAlgorithm<?>)targetColumnShardingAlgorithm;
            PreciseShardingValue preciseShardingValue = new PreciseShardingValue<>(tableRule.getLogicTable(),
                clientShardingValuesMap.keySet().iterator().next(), dataNodeInfo,
                clientShardingValuesMap.values().iterator().next().iterator().next());
            return standardShardingAlgorithm.doSharding(availableTargetNames, preciseShardingValue);
        } else if (targetColumnShardingAlgorithm instanceof ComplexKeysShardingAlgorithm) {
            ComplexKeysShardingAlgorithm<?> complexKeysShardingAlgorithm =
                (ComplexKeysShardingAlgorithm<?>)targetColumnShardingAlgorithm;
            ComplexKeysShardingValue comparableComplexKeysShardingValue =
                new ComplexKeysShardingValue<>(tableRule.getLogicTable(), clientShardingValuesMap, null);
            return (String)complexKeysShardingAlgorithm.doSharding(availableTargetNames,
                comparableComplexKeysShardingValue).iterator().next();
        } else if (targetColumnShardingAlgorithm instanceof HintShardingAlgorithm) {
            // 不支持hint算法进行分片
            return null;
        } else {
            throw new RuntimeException("不支持的分片算法类型");
        }

    }

    private ShardingAlgorithmRule getShardingAlgorithm(Invocation invocation) {
        // 获取当前的数据源
        SpringManagedTransaction executor =
            (SpringManagedTransaction)((CachingExecutor)invocation.getTarget()).getTransaction();
        DataSource dataSource = (DataSource)ReflectUtil.getFieldValue(executor, "dataSource");

        // 获取shardingSphereRule列表拿到分片规则列表
        return buildShardingAlgorithmRule(dataSource, HintManagerPlus.getLogicName());
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
            ShardingAlgorithm targetColumnDatasourceShardingAlgorithm = shardingAlgorithmMap.get(
                plusDataSourceShardingAlgorithm.getProps().getProperty(SHARDING_ALGORITHM_KEY));
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

    private Map<String, Collection<Comparable<?>>> getShardingValues() {
        String columnName = HintManagerPlus.getColumnName();
        Comparable<?> shardingValue = HintManagerPlus.getShardingValue();
        if (StrUtil.isBlank(columnName)) {
            throw new RuntimeException("分片的列表为空");
        }
        if (ObjectUtils.isEmpty(shardingValue)) {
            throw new RuntimeException("分片的值为空");
        }
        // 优先从上下文中获取
        Map<String, Collection<Comparable<?>>> contextMap = Maps.newHashMap();
        contextMap.putIfAbsent(columnName, Lists.newArrayList(shardingValue));
        return contextMap;
    }

    private Object realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return target;
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