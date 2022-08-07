package com.sika.code.cache.executor;

import com.github.benmanes.caffeine.cache.Cache;
import com.sika.code.cache.pojo.GetLocalCacheDTO;

/**
 * <p>
 *  本地缓存默认执行器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 17:22
 */
public class LocalCacheDefaultExecutor<T> implements LocalCacheExecutor<T> {
    @Override
    public T getCache(GetLocalCacheDTO<T> cacheDTO, Cache<String, Object> cache) {
        return (T) cache.asMap().get(cacheDTO.getKey());
    }

    @Override
    public T doCache(GetLocalCacheDTO<T> cacheDTO, Cache<String, Object> cache, Object cacheValue) {
        cache.put(cacheDTO.getKey(), cacheValue);
        return (T) cacheValue;
    }
}
