package com.sika.code.core.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  唯一键Id工具类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/9/4 20:57
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
        Assert.notNull(tClazz, "class对象不能为空");
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
