package com.sika.code.demo.domain.common.reids.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// 标志为配置类
@Configuration
public class RedisConfig {

    // 把这个bean的name设置为redisTemplate，这样我们才能全面接管redisTemplate！
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // jackson序列化所有的类
        Jackson2JsonRedisSerializer Jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // jackson序列化的一些配置
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance);
        Jackson2JsonRedisSerializer.setObjectMapper(om);
        // String的序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        //将我们的key采用String的序列化方式
        template.setKeySerializer(stringSerializer);
        //将我们的hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringSerializer);
        //value采用jackson序列化方式
        template.setValueSerializer(stringSerializer);
        //hash的value也采用jackson序列化方式
        template.setHashValueSerializer(stringSerializer);

        template.afterPropertiesSet();


        return template;
    }
}