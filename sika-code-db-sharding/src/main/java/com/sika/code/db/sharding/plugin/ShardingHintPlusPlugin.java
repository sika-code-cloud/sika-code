package com.sika.code.db.sharding.plugin;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.sika.code.db.sharding.annotation.Sharding;
import com.sika.code.db.sharding.utils.ShardingUtils;
import lombok.AllArgsConstructor;
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
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.infra.rule.ShardingSphereRule;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.apache.shardingsphere.sharding.rule.ShardingRule;
import org.apache.shardingsphere.sharding.rule.TableRule;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        ShardingAlgorithmRule shardingAlgorithmRule = getShardingAlgorithm(invocation);
        if (shardingAlgorithmRule == null) {
            return invocation.proceed();
        }
        String shardingValue = getShardingValue(invocation);
        if (StrUtil.isBlank(shardingValue)) {
            return invocation.proceed();
        }
        logger.debug("执行Hint加强逻辑");
        // 获取分片的表索引
        int tableModNumber = ShardingUtils.getTableModNumber(shardingAlgorithmRule.getShardingAlgorithm().getProps());
        List<Integer> availableTargetSequences =
            ShardingUtils.getAllTableSequences(shardingAlgorithmRule.getShardingAlgorithm().getProps());
        int tableIndex = ShardingUtils.twiceHashMod(shardingValue, tableModNumber, availableTargetSequences);
        logger.debug("表【{}】,分片值为：{}】分片表索引为：{}", shardingAlgorithmRule.getLogicTableName(), shardingAlgorithmRule.getLogicTableName(), tableIndex);

        try (HintManager hintManager = HintManager.getInstance()) {
            if (CollUtil.isEmpty(HintManager.getDatabaseShardingValues(shardingAlgorithmRule.getLogicTableName()))) {
                hintManager.addDatabaseShardingValue(shardingAlgorithmRule.getLogicTableName(), tableIndex);
            }
            if (CollUtil.isEmpty(HintManager.getTableShardingValues(shardingAlgorithmRule.getLogicTableName()))) {
                hintManager.addTableShardingValue(shardingAlgorithmRule.getLogicTableName(), tableIndex);
            }
            return invocation.proceed();
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
        ShardingSphereDataSource dataSource =
            (ShardingSphereDataSource)mappedStatement.getConfiguration().getEnvironment().getDataSource();
        ContextManager contextManager = (ContextManager)ReflectUtil.getFieldValue(dataSource, "contextManager");
        String databaseName = (String)ReflectUtil.getFieldValue(dataSource, "databaseName");
        Collection<ShardingSphereRule> rules =
            contextManager.getMetaDataContexts().getMetaData().getDatabase(databaseName).getRuleMetaData().getRules();
        // 轮询获取分表分片算法-主要目的是为了拿到表的序列即可
        for (ShardingSphereRule shardingSphereRule : rules) {
            if (shardingSphereRule instanceof ShardingRule) {
                ShardingRule shardingRule = (ShardingRule)shardingSphereRule;
                TableRule tableRule = shardingRule.getTableRule(tableName);
                return new ShardingAlgorithmRule(shardingRule.getShardingAlgorithms()
                    .get(tableRule.getDatabaseShardingStrategyConfig().getShardingAlgorithmName()), tableName);
            }
        }
        return null;
    }

    private String getShardingValue(Invocation invocation) {
        MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>)invocation.getArgs()[1];
        Object param = paramMap.get("param1");
        // 集合不支持加强
        if (param instanceof Collection) {
            return null;
        }
        // 如果参数为map - 此时若要分片则取key = shardingKey 对应的值作为值
        if (param instanceof Map) {
            return (String)((Map<?, ?>)param).get("shardingKey");
        }
        // 普通的POJO对象 - 先从属性中获取对应的值
        String fieldValue = getShardingValueFromField(param);
        if (StrUtil.isNotBlank(fieldValue)) {
            return fieldValue;
        }
        // 属性为空 - 则从方法上获取对应的值
        return getShardingValueFromMethod(param);
    }

    private String getShardingValueFromField(Object param) {
        // 普通的POJO对象 - 先从属性中获取对应的值
        Field[] fields = ReflectUtil.getFields(param.getClass());
        for (Field field : fields) {
            if (!AnnotationUtil.hasAnnotation(field, Sharding.class)) {
                continue;
            }
            Object value = ReflectUtil.getFieldValue(param, field);
            if (value instanceof String) {
                return (String)value;
            }
        }
        return null;
    }

    private String getShardingValueFromMethod(Object param) {
        // 普通的POJO对象 - 先从属性中获取对应的值
        Method[] methods = ReflectUtil.getMethods(param.getClass());
        for (Method method : methods) {
            if (!AnnotationUtil.hasAnnotation(method, Sharding.class)) {
                continue;
            }
            Object value = ReflectUtil.invoke(param, method);
            if (value instanceof String) {
                return (String)value;
            }
        }
        return null;
    }

    @AllArgsConstructor
    public static class ShardingAlgorithmRule {
        private ShardingAlgorithm shardingAlgorithm;
        private String logicTableName;

        public String getLogicTableName() {
            return logicTableName;
        }

        public ShardingAlgorithm getShardingAlgorithm() {
            return shardingAlgorithm;
        }
    }
}