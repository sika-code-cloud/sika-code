package com.sika.code.cache.executor;

import com.github.benmanes.caffeine.cache.Cache;
import com.sika.code.cache.pojo.GetLocalCacheDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 15:53
 */
public interface LocalCacheExecutor<T> {
    /**
     *
     * @param cacheDTO : 缓存数据传输对象
     * @param cache : 本地缓存对象
     * @return 缓存中的对象
     */
    T getCache(GetLocalCacheDTO<T> cacheDTO, Cache<String, Object> cache);

    /**
     *
     * @param cacheDTO : 缓存数据传输对象
     * @param cache : 本地缓存对象
     * @param cacheValue : 缓存值
     * @return : 需要缓存的对象
     */
    T doCache(GetLocalCacheDTO<T> cacheDTO, Cache<String, Object> cache, Object cacheValue);
}
