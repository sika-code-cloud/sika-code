package com.sika.code.core.util;

import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.lang.Snowflake;

import java.util.HashMap;

/**
 * <p>
 *  拓展hutool的工具类
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/9/10 10:24
 */
public class IdUtil extends cn.hutool.core.util.IdUtil {

    private static final SimpleCache<Class<?>, Snowflake> POOL = new SimpleCache<>(new HashMap<>());

    /**
     * <p>
     * 根据实体的类名-生成对应的雪花算法对象-避免共用雪花对象
     * </p>
     *
     * @param tClazz
     * @return cn.hutool.core.lang.Snowflake
     * @author sikadai
     * @since 2022/9/4 20:59
     */
    public static Snowflake getSnowflake(Class<?> tClazz) {
        if (tClazz == null) {
            return getSnowflake();
        }
        Snowflake snowflakeFromCache = POOL.get(tClazz);
        if (snowflakeFromCache != null) {
            return snowflakeFromCache;
        }
        snowflakeFromCache = new Snowflake();
        POOL.put(tClazz, snowflakeFromCache);
        return snowflakeFromCache;
    }

    /**
     * <p>
     * 根据实体的类名-生成对应的雪花算法对象-避免共用雪花对象-生成雪花ID
     * </p>
     *
     * @param tClazz
     * @return cn.hutool.core.lang.Snowflake
     * @author sikadai
     * @since 2022/9/4 20:59
     */
    public static Long nextId(Class<?> tClazz) {
        return getSnowflake(tClazz).nextId();
    }
}
