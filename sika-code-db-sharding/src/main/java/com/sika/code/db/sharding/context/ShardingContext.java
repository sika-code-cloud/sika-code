package com.sika.code.db.sharding.context;

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


    /**
     * 将分片的值-若分库分表为同字段放入上下文中
     */
    public static void addShardValue(Object value) {
        addShardDbValue(value);
        addShardTableValue(value);
    }

    /**
     * 将分库的值放入上下文中
     */
    public static void addShardDbValue(Object value) {
        add(SHARD_DB_KEY, value);
    }

    /**
     * 将分表的值放入上下文中
     */
    public static void addShardTableValue(Object value) {
        add(SHARD_TABLE_KEY, value);
    }

    public static void add(String key, Object value) {
        Map<String, Object> stringObjectMap = CONTEXT.get();
        if (stringObjectMap == null) {
            CONTEXT.set(new ConcurrentHashMap<>());
        }
        CONTEXT.get().put(key, value);
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

    public static void remove() {
        CONTEXT.remove();
    }
}
