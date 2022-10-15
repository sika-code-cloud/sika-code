package com.sika.code.db.sharding.context;

import cn.hutool.core.lang.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 分片上下文
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/7/3 12:32
 */
public class ShardingContext {
    private ShardingContext() {
    }

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    private static final String SHARD_DB_KEY = "shardDbKey";
    private static final String SHARD_TABLE_KEY = "shardTableKey";
    private static final String DB_NAME_KEY = "dbNameKey";
    private static final String TABLE_NAME_KEY = "tableNameKey";
    /**
     * 自动清除标志KEY - 默认为自动清除-若设置为false-则需要用户手动清除
     */
    private static final String AUTO_CLEAR_FLAG_KEY = "autoClearFlagKey";

    /**
     * 将分片的值-若分库分表为同字段放入上下文中
     */
    public static void addShardValue(Object value, boolean autoClear) {
        addShardDbValue(value);
        addShardTableValue(value);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }

    public static void addShardValue(Object value) {
        addShardDbValue(value);
        addShardTableValue(value);
    }

    /**
     * 将分库的值放入上下文中
     */
    public static void addShardDbValue(Object value) {
        addShardDbValue(value, true);
    }

    /**
     * 将分库的值放入上下文中
     */
    public static void addShardDbValue(Object value, boolean autoClear) {
        add(SHARD_DB_KEY, value);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }


    /**
     * 将分表的值放入上下文中
     */
    public static void addShardTableValue(Object value) {
        addShardTableValue(value, true);
    }


    /**
     * 将分表的值放入上下文中
     */
    public static void addShardTableValue(Object value, boolean autoClear) {
        add(SHARD_TABLE_KEY, value);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }


    /**
     * 将原始的DB名称和表名称放入上下文
     */
    public static void addDbTableName(String dbName, String tableName) {
        addDbTableName(dbName, tableName, true);
    }

    /**
     * 将原始的DB名称和表名称放入上下文
     */
    public static void addDbTableName(String dbName, String tableName, boolean autoClear) {
        addDbName(dbName);
        addTableName(tableName);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }

    /**
     * 将原始的DB名称放入上下文
     */
    public static void addDbName(String shardDbName) {
        addDbName(shardDbName, true);
    }

    /**
     * 将原始的DB名称放入上下文
     */
    public static void addDbName(String shardDbName, boolean autoClear) {
        Assert.notEmpty(shardDbName, "原始数据库名称不能为空");
        add(DB_NAME_KEY, shardDbName);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }


    /**
     * 将原始的table名称放入上下文
     */
    public static void addTableName(String shardTableName) {
        addTableName(shardTableName, true);
    }

    /**
     * 将原始的table名称放入上下文
     */
    public static void addTableName(String shardTableName, boolean autoClear) {
        Assert.notEmpty(shardTableName, "原始表名称不能为空");
        add(TABLE_NAME_KEY, shardTableName);
        add(AUTO_CLEAR_FLAG_KEY, autoClear);
    }

    public static void add(String key, Object value) {
        Assert.notEmpty(key, "分库分表上下文的KEY不能为空");
        Assert.notNull(value, "分库分表上下文的值不能为空");
        Map<String, Object> stringObjectMap = CONTEXT.get();
        if (stringObjectMap == null) {
            CONTEXT.set(new ConcurrentHashMap<>());
        }
        CONTEXT.get().put(key, value);
    }

    public static String getDbName() {
        return (String) get(DB_NAME_KEY);
    }

    public static String getTableName() {
        return (String) get(TABLE_NAME_KEY);
    }

    public static Object getShardDbValue() {
        return get(SHARD_DB_KEY);
    }

    public static Object getShardTableValue() {
        return get(SHARD_TABLE_KEY);
    }

    public static Object get(String key) {
        Map<String, Object> stringObjectMap = CONTEXT.get();
        if (stringObjectMap == null) {
            return null;
        }
        return stringObjectMap.get(key);
    }

    public static void remove(String key) {
        Map<String, Object> stringObjectMap = CONTEXT.get();
        if (stringObjectMap == null) {
            return;
        }
        stringObjectMap.remove(key);
    }

    public static boolean isAutoClearFlag() {
        Object autoClearFlag = CONTEXT.get().get(AUTO_CLEAR_FLAG_KEY);
        if (autoClearFlag == null) {
            return true;
        }
        return (boolean) autoClearFlag;
    }

    public static void remove() {
        CONTEXT.remove();
    }

}
