package com.sika.code.cache.redis.config;

import com.sika.code.basic.constant.PropertyConstant;
import com.sika.code.cache.redis.aspect.RedisAspect;
import com.sika.code.cache.redis.selector.RedisProxySelector;
import com.sika.code.cache.redis.serializer.FastJson2JsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author daiqi
 * @ClassName : RedisConfig
 * @Description : redis配置文件
 * @date 2017年12月7日 下午4:44:25
 */
@Configuration
@ConditionalOnProperty(name = PropertyConstant.REDIS_FIRE, havingValue = "true")
public class RedisConfig {


    /**
     * redisObject的序列化
     */
    @Primary
    @Bean
    @ConditionalOnMissingBean
    public RedisSerializer redisObjectSerializer() {
        return new FastJson2JsonRedisSerializer(Object.class);
    }

    @Primary
    @Bean("redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.cluster.nodes")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory, RedisSerializer redisSerializer) {
        return buildRedisTemplate(factory, redisSerializer);
    }

    @Primary
    @Bean("redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.host", matchIfMissing = true)
    public RedisTemplate<String, Object> getSingleRedisTemplate(RedisConnectionFactory factory, RedisSerializer redisSerializer) {
        return buildRedisTemplate(factory, redisSerializer);
    }

    protected RedisTemplate<String, Object> buildRedisTemplate(RedisConnectionFactory factory, RedisSerializer redisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        RedisSerializer stringSerializer = new StringRedisSerializer();
        // key的序列化类型
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // value的序列化类型
        redisTemplate.setValueSerializer(redisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


    @Bean
    @ConditionalOnMissingBean
    public RedisProxySelector redisProxySelector() {
        return new RedisProxySelector();
    }

    @Bean
    public RedisAspect redisAspect(RedisProxySelector redisProxySelector) {
        return new RedisAspect(redisProxySelector);
    }
}
