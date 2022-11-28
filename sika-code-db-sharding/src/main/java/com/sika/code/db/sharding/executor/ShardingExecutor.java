package com.sika.code.db.sharding.executor;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.sika.code.db.sharding.annotation.ShardingRule;
import com.sika.code.db.sharding.config.ShardingRuleConfig;
import com.sika.code.db.sharding.context.ShardingContext;
import com.sika.code.db.sharding.manager.ShardingStrategyManager;
import com.sika.code.db.sharding.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <pre>
 *  分片执行器
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/7/9 15:42
 */
@Slf4j
public abstract class ShardingExecutor {

    protected static final String SQL = "delegate.boundSql.sql";
    protected static final String MAPPED_STATEMENT = "delegate.mappedStatement";
    protected static final String PARAM1 = "param1";
    protected static final String RULE_CONFIG_ENUM_NAME = "name";

    protected static final Integer SHARDING_TYPE_DB = 1;
    protected static final Integer SHARDING_TYPE_TABLE = 2;

    private ShardingStrategyManager shardingStrategyManager;

    public void doShardingDbTable(MetaObject metaStatementHandler, Object param) throws ClassNotFoundException {
        String originalSql = (String) metaStatementHandler.getValue(SQL);
        if (CharSequenceUtil.isBlank(originalSql)) {
            log.error("从metaStatementHandler获取的sql为空-请立即核实");
            return;
        }
//        log.info("分表前的SQL：{}", originalSql);
        // 获取分片规则配置-默认从枚举中获取
        ShardingRuleConfig shardingRuleConfig = getShardingRuleConfig(metaStatementHandler);
        if (shardingRuleConfig == null || !BooleanUtil.isTrue(shardingRuleConfig.getSharingFlag())) {
            return;
        }
        // 校验分片规则校验
        verifyShardingConfig(shardingRuleConfig);
        //获取完整的表名
        String newTableName = getNewDbTableName(shardingRuleConfig, param);
        // 替换原始的SQL
        String convertedSql = buildNewSql(shardingRuleConfig, originalSql, newTableName);
//        log.info("分表后的SQL：{}", convertedSql);
        metaStatementHandler.setValue(SQL, convertedSql);
    }

    protected String getNewDbTableName(ShardingRuleConfig shardingRuleConfig, Object param) {
        // 获取分片策略类
        Strategy strategy = getSharingStrategy(shardingRuleConfig);
        // 获取分库的值
        Object shardDbValue = getShardValue(param, shardingRuleConfig.getDbShardingKey(), SHARDING_TYPE_DB);
        // 获取分表的值
        Object shardTableValue = getShardValue(param, shardingRuleConfig.getTableShardingKey(), SHARDING_TYPE_TABLE);
        if (shardDbValue == null && shardTableValue == null) {
            throw new IllegalArgumentException(CharSequenceUtil.format("分片的库的值【{}】和分片的表的值【{}】同时为空，请立即核实", shardDbValue, shardTableValue));
        }
        String dbName = getDbName(shardingRuleConfig);
        String tableName = getTableName(shardingRuleConfig);
        //获取完整的表名
        return strategy.returnDbTableName(dbName, shardDbValue, tableName, shardTableValue);
    }

    protected String getDbName(ShardingRuleConfig shardingRuleConfig) {
        String dbName = ShardingContext.getDbName();
        if (StringUtils.isNotBlank(dbName)) {
            return dbName;
        }
        return shardingRuleConfig.getDbName();
    }

    protected String getTableName(ShardingRuleConfig shardingRuleConfig) {
        String tableName = ShardingContext.getTableName();
        if (StringUtils.isNotBlank(tableName)) {
            return tableName;
        }
        return shardingRuleConfig.getTableName();
    }

    protected String buildNewSql(ShardingRuleConfig shardingRuleConfig, String sql, String newTableName) {
        TableNameParser parser = new TableNameParser(sql);
        List<TableNameParser.SqlToken> names = new ArrayList<>();
        parser.accept(names::add);
        // 过滤出需要替换的表名
        List<TableNameParser.SqlToken> filterNames = names.stream().filter(item -> item.getValue().
                equalsIgnoreCase(shardingRuleConfig.getTableName())).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        int last = 0;
        for (TableNameParser.SqlToken name : filterNames) {
            int start = name.getStart();
            if (start != last) {
                builder.append(sql, last, start);
                builder.append(newTableName);
            }
            last = name.getEnd();
        }
        if (last != sql.length()) {
            builder.append(sql.substring(last));
        }
        return builder.toString();
    }

    protected ShardingRule getShardingRule(MetaObject metaStatementHandler) {
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue(MAPPED_STATEMENT);
        String id = mappedStatement.getId();
        String className = CharSequenceUtil.sub(id, 0, id.lastIndexOf(StrPool.DOT));
        Class<?> classObj = null;
        try {
            classObj = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        // 根据配置自动生成分表SQL
        return classObj.getAnnotation(ShardingRule.class);
    }

    protected ShardingRuleConfig getShardingRuleConfig(MetaObject metaStatementHandler) {
        // 根据配置自动生成分表SQL
        ShardingRule shardingRule = getShardingRule(metaStatementHandler);
        if (shardingRule == null) {
            return null;
        }
        return getShardingRuleConfig(shardingRule);
    }

    protected abstract ShardingRuleConfig getShardingRuleConfig(ShardingRule shardingRule);

    protected Object getShardValue(Object obj, String paramName, Integer shardType) {
        // 1. 从上下文里面获取
        Object paramValue = getShardValueFromContext(shardType);
        if (paramValue != null) {
            return paramValue;
        }
        // 2 从Map中获取
        paramValue = getShardValueFromMap(obj, paramName);
        if (paramValue != null) {
            return paramValue;
        }
        // 3. 从对象中获取
        return getShardValueFromObj(obj, paramName);
    }

    protected Object getShardValueFromContext(Integer shardType) {
        // 1. 从上下文里面获取
        if (SHARDING_TYPE_DB.equals(shardType)) {
            return ShardingContext.getShardDbValue();
        } else {
            return ShardingContext.getShardTableValue();
        }
    }

    protected Object getShardValueFromMap(Object obj, String paramName) {
        // 从Map中获取数据
        if (obj instanceof MapperMethod.ParamMap) {
            return null;
        } else if (obj instanceof Map) {
            return ((Map) obj).get(paramName);
        }
        return null;
    }

    protected Object getShardValueFromObj(Object obj, String paramName) {
        Object tempObj = obj;
        if (obj instanceof MapperMethod.ParamMap) {
            tempObj = ((MapperMethod.ParamMap) obj).get(PARAM1);
        }
        // 从对象的属性中获取数据
        Field[] fields = ReflectUtil.getFields(tempObj.getClass());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (CharSequenceUtil.equalsIgnoreCase(field.getName(), paramName)) {
                    return field.get(tempObj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    protected void verifyShardingConfig(ShardingRuleConfig config) {
        Assert.notNull(config.getStrategyClass(), "分片策略类为空，请立即核实");
        if (CharSequenceUtil.isBlank(config.getDbShardingKey()) && CharSequenceUtil.isBlank(config.getTableShardingKey())) {
            throw new IllegalArgumentException("分片数据库的分片KEY或者分片表的分片KEY不能同时为空，请立即核实");
        }
        if (CharSequenceUtil.isBlank(config.getDbName()) && CharSequenceUtil.isBlank(config.getTableName())) {
            throw new IllegalArgumentException("分片数据库名或者分片表名不能同时为空，请立即核实");
        }
        if (CharSequenceUtil.isNotBlank(config.getDbName()) && CharSequenceUtil.isBlank(config.getDbShardingKey())) {
            throw new IllegalArgumentException("数据库名不为空，但是数据库分片KEY为空，请立即核实");
        }
        if (CharSequenceUtil.isNotBlank(config.getTableName()) && CharSequenceUtil.isBlank(config.getTableShardingKey())) {
            throw new IllegalArgumentException("数据表名不为空，但是数据表分片KEY为空，请立即核实");
        }
    }

    protected Strategy getSharingStrategy(ShardingRuleConfig shardingRuleConfig) {
        return getStrategyManager().getStrategy(shardingRuleConfig.getStrategyClass());
    }

    protected ShardingStrategyManager getStrategyManager() {
        return this.shardingStrategyManager;
    }

    public void setShardingStrategyManager(ShardingStrategyManager shardingStrategyManager) {
        this.shardingStrategyManager = shardingStrategyManager;
    }
}
