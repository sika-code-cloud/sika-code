package com.sika.code.cache.executor;

import com.sika.code.cache.pojo.GetRedisCacheDTO;
import com.sika.code.core.base.util.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 15:53
 */
public interface RedisCacheExecutor<T> {
    /**
     * 从Redis中获取缓存
     * @param cacheDTO : 缓存数据传输对象
     * @param redisTemplate : redis模板
     * @return 缓存的值
     */
    T getCache(GetRedisCacheDTO<T> cacheDTO, RedisTemplate redisTemplate);

    /**
     * 将目标对象缓存到redis中
     * @param cacheDTO : 缓存数据传输对象
     * @param redisTemplate : redis模板
     * @param value : 缓存的值
     * @return 缓存的值
     */
    default T doCache(GetRedisCacheDTO<T> cacheDTO, RedisTemplate redisTemplate, Object value) {
        redisTemplate.opsForValue().set(cacheDTO.getKey(), JSONUtil.toJSONString(value));
        return (T) value;
    }
}
