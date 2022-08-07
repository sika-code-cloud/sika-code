package com.sika.code.cache.executor;

import com.alibaba.fastjson.JSON;
import com.sika.code.cache.pojo.GetRedisCacheDTO;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * redis缓存默认执行器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 17:22
 */
public class RedisCacheDefaultExecutor<T> implements RedisCacheExecutor<T> {
    @Override
    public T getCache(GetRedisCacheDTO<T> cacheDTO, RedisTemplate redisTemplate) {
        return JSON.parseObject((String) redisTemplate.opsForValue().get(cacheDTO.getKey()), cacheDTO.getValueClass());
    }

    @Override
    public T doCache(GetRedisCacheDTO<T> cacheDTO, RedisTemplate redisTemplate, Object value) {
        redisTemplate.opsForValue().set(cacheDTO.getKey(), JSON.toJSONString(value));
        return (T) value;
    }
}
