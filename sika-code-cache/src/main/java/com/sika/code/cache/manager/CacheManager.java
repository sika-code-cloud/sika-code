package com.sika.code.cache.manager;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import com.sika.code.cache.executor.LocalCacheExecutor;
import com.sika.code.cache.executor.RedisCacheExecutor;
import com.sika.code.cache.pojo.*;
import com.sika.code.core.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *  缓存管理器-提供redis缓存+二级缓存的管理
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/8/5 17:57
 */
@Component
@Slf4j
public class CacheManager {
    public static final Map<String, Cache<String, Object>> CACHE = Maps.newConcurrentMap();

    /**
     * <p>
     * 清除本地缓存
     * </p >
     *
     * @param cacheDTO : 缓存的数据传输对象
     * @author sikadai
     * @since 2022/8/5 18:35
     */
    public void clearRedisCache(ClearRedisCacheDTO cacheDTO, RedisTemplate redisTemplate) {
        Assert.notNull(cacheDTO, "缓存的数据传输对象不能为空");
        // 先执行方法
        invoke(cacheDTO, null);
        // 删除缓存
        redisTemplate.delete(cacheDTO.getKey());
    }

    /**
     * <p>
     * 清除本地缓存
     * </p >
     *
     * @param cacheDTO : 缓存的数据传输对象
     * @author sikadai
     * @since 2022/8/5 18:35
     */
    public void clearLocalCache(ClearLocalCacheDTO cacheDTO) {
        Assert.notNull(cacheDTO, "缓存的数据传输对象不能为空");
        // 先执行方法
        invoke(cacheDTO, null);
        // 获取缓存容器
        Cache<String, Object> cacheContainer = CACHE.get(cacheDTO.getType());
        if (cacheContainer == null) {
            return;
        }
        cacheContainer.asMap().remove(cacheDTO.getKey());
    }

    /**
     * 获取缓存-先从内存缓存中获取缓存-若本地缓存不存在-则从redis中获取缓存
     *
     * @param cacheDTO           : 本地缓存数据传输对象
     * @param cacheExecutor      : 本地缓存执行器
     * @param redisCacheDTO      : redis缓存数据传输对象
     * @param redisCacheExecutor : redis缓存执行器
     * @param redisTemplate      : redis模板
     * @param <T>                : 返回缓存的对象
     * @return
     */
    public <T> T getCache(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor, GetRedisCacheDTO<T> redisCacheDTO, RedisCacheExecutor<T> redisCacheExecutor, RedisTemplate<String, T> redisTemplate) {
        // 从本地缓存获取对象
        T retObj = getLocalCache(cacheDTO, cacheExecutor);
        // 不为空-直接返回
        if (retObj != null) {
            return retObj;
        }
        // 从redis中获取对象并且放到本地缓存中
        retObj = getRedisAndAddLocal(cacheDTO, cacheExecutor, redisCacheDTO, redisCacheExecutor, redisTemplate);
        if (retObj != null) {
            return retObj;
        }
        // 走到此处说明缓存失效-先执行redis缓存-再缓存到本地
        return addRedisAndLocal(cacheDTO, cacheExecutor, redisCacheDTO, redisCacheExecutor, redisTemplate);
    }

    private  <T> T getRedisAndAddLocal(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor, GetRedisCacheDTO<T> redisCacheDTO, RedisCacheExecutor<T> redisCacheExecutor, RedisTemplate<String, T> redisTemplate) {
        // 从redis中获取对象
      T retObj = getRedisCache(redisCacheDTO, redisCacheExecutor, redisTemplate);
        if (retObj != null) {
            // 执行本地缓存
            cacheDTO.setExecute(true);
            cacheDTO.setCacheResult(retObj);
            // 将redis的缓存放到本地缓存中
            addLocalCache(cacheDTO, cacheExecutor);
            return retObj;
        }
        return null;
    }

    private <T> T addRedisAndLocal(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor, GetRedisCacheDTO<T> redisCacheDTO, RedisCacheExecutor<T> redisCacheExecutor, RedisTemplate<String, T> redisTemplate) {
        T retObj = addRedisCache(redisCacheDTO, redisCacheExecutor, redisTemplate);
        // 再将结果放到本地缓存
        cacheDTO.setExecute(true);
        cacheDTO.setCacheResult(retObj);
        addLocalCache(cacheDTO, cacheExecutor);
        return retObj;
    }

    public <T> T getAndAddLocalCache(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor) {
        T retValue = getLocalCache(cacheDTO, cacheExecutor);
        if (retValue != null) {
            return retValue;
        }
        return addLocalCache(cacheDTO, cacheExecutor);
    }

    /**
     * <p>
     * 只提供内存级别的缓存
     * </p >
     *
     * @param cacheDTO : 缓存数据传输对象
     * @return 返回本地中的缓存
     * @author sikadai
     * @since 2022/8/5 18:38
     */
    public <T> T getLocalCache(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor) {
        Assert.notNull(cacheDTO, "缓存的数据传输对象不能为空");
        // 构建
        cacheDTO.build();
        // 获取缓存容器
        Cache<String, Object> cacheContainer = CACHE.get(cacheDTO.getType());
        if (cacheContainer == null) {
            cacheContainer = buildLocalCache(cacheDTO);
        }
        // 获取缓存对象
        T objectCache = cacheExecutor.getCache(cacheDTO, cacheContainer);
        if (objectCache != null) {
            return objectCache;
        }
        return null;
    }

    public <T> T addLocalCache(GetLocalCacheDTO<T> cacheDTO, LocalCacheExecutor<T> cacheExecutor) {
        Cache<String, Object> cacheContainer = CACHE.get(cacheDTO.getType());
        if (cacheContainer == null) {
            cacheContainer = buildLocalCache(cacheDTO);
        }
        // 执行目标方法
        Object objectForInvoke = invoke(cacheDTO, cacheDTO.getDefaultValue());
        if (objectForInvoke == null) {
            return null;
        }
        // 执行缓存
        T object = cacheExecutor.doCache(cacheDTO, cacheContainer, objectForInvoke);
        CACHE.put(cacheDTO.getType(), cacheContainer);
        // 返回本地缓存
        return object;
    }

    public <T> T getAndAddRedisCache(GetRedisCacheDTO<T> cacheDTO, RedisCacheExecutor<T> cacheExecutor, RedisTemplate<String, T> redisTemplate) {
        T retValue = getRedisCache(cacheDTO, cacheExecutor, redisTemplate);
        if (retValue != null) {
            return retValue;
        }
        return addRedisCache(cacheDTO, cacheExecutor, redisTemplate);
    }

    /**
     * 获取redis缓存
     *
     * @param cacheDTO      : 缓存数据传输对象
     * @param cacheExecutor ： 缓存执行器
     * @param redisTemplate : redisTemplate
     * @return 返回redis中的缓存
     */
    public <T> T getRedisCache(GetRedisCacheDTO<T> cacheDTO, RedisCacheExecutor<T> cacheExecutor, RedisTemplate<String, T> redisTemplate) {
        Assert.notNull(cacheDTO, "缓存的数据传输对象不能为空");
        // 构建
        cacheDTO.build();
        // 先从缓存中获取数据
        T objectCache = cacheExecutor.getCache(cacheDTO, redisTemplate);
        if (objectCache != null) {
            return objectCache;
        }
        return null;
    }


    public <T> T addRedisCache(GetRedisCacheDTO<T> cacheDTO, RedisCacheExecutor<T> cacheExecutor, RedisTemplate<String, T> redisTemplate) {
        // 执行方法
        Object objectForInvoke = invoke(cacheDTO, cacheDTO.getDefaultValue());
        if (objectForInvoke == null) {
            return null;
        }
        // 执行缓存
        T object = cacheExecutor.doCache(cacheDTO, redisTemplate, objectForInvoke);
        // 设置缓存有效期
        redisTemplate.expire(cacheDTO.getKey(), cacheDTO.getExpire() + cacheDTO.getRandomExpire(), TimeUnit.SECONDS);
        return object;
    }

    /**
     * 构建本地缓存容器
     */
    private Cache<String, Object> buildLocalCache(GetLocalCacheDTO cacheDTO) {
        return Caffeine.newBuilder()
                .expireAfterWrite(cacheDTO.getExpire(), TimeUnit.SECONDS)
                .initialCapacity(cacheDTO.getInitCacheSize())
                .maximumSize(cacheDTO.getMaxCacheSize())
                .build();
    }

    /**
     * 执行目标方法-若为空则设置默认值
     *
     * @param cacheDTO     : 缓存数据传输对象
     * @param defaultValue : 默认值
     * @return : 返回目标对象
     */
    public Object invoke(CacheDTO cacheDTO, Object defaultValue) {
        if (BooleanUtil.isTrue(cacheDTO.getExecute())) {
            return cacheDTO.getCacheResult();
        }
        Assert.notNull(cacheDTO.getMethodClass(), "方法对象不能为空");
        Assert.notNull(cacheDTO.getMethodName(), "方法名称不能为空");
        log.info("没有命中缓存-开始执行目标类【{}】的方法【{}】参数【{}】", cacheDTO.getMethodClass().getName(), cacheDTO.getMethodName(), cacheDTO.getMethodArgs());
        // 根据目标Class获取对象
        Object target = BeanUtil.getBean(cacheDTO.getMethodClass());
        Assert.notNull(target, "targetClass对应的目标对象不能为空", cacheDTO.getMethodClass());
        // 通过反射执行目标对象方法
        Object objectValue = ReflectUtil.invoke(target, cacheDTO.getMethodName(), cacheDTO.getMethodArgs());
        // 设置执行标志
        cacheDTO.setExecute(true);
        if (objectValue != null) {
            cacheDTO.setCacheResult(objectValue);
            return objectValue;
        }
        if (defaultValue != null) {
            cacheDTO.setCacheResult(defaultValue);
        }
        return defaultValue;
    }

}
