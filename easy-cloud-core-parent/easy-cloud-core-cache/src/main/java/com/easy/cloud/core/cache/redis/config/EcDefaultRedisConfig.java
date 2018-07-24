package com.easy.cloud.core.cache.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisTemplateName;

/**
 * 
 * @ClassName : RedisConfig
 * @Description : redis配置文件
 * @author daiqi
 * @date 2017年12月7日 下午4:44:25
 *
 */
@Configuration
@Conditional(EcRedisConditional.class)
@PropertySource("classpath:config/redis-default.properties")
public class EcDefaultRedisConfig extends EcRedisConfig {
	
	@Primary
	@Bean(value = "defaultRedisConnectionFactory")
	@ConfigurationProperties(prefix = "ec.redis")
	public RedisConnectionFactory defaultRedisConnectionFactory() {
		return new JedisConnectionFactory(redisProperties);
	}

	/**
	 * <p>
	 * 实例化 RedisTemplate 对象--保存的值为JSON字符串类型
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * @date 2017年12月7日 下午5:19:59
	 */
	@Bean(value = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_STR_NAME)
	public StringRedisTemplate stringRedisTemplate(@Qualifier(value = "defaultRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * <p>
	 * 实例化 RedisTemplate 对象--保存的值为对象经过jdk序列化后的对象
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * @date 2017年12月7日 下午5:19:51
	 */
	@Bean(value = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_SERIALIZER_NAME)
	public RedisTemplate<String, Object> redisTemlateValueSerializer(@Qualifier(value = "defaultRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置RedisCacheManager 使用cache注解管理redis缓存
	 *
	 * @return
	 */
    @Bean(name = "stringCacheManager")
    public RedisCacheManager stringCacheManager(@Qualifier(value = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_STR_NAME) StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
        return redisCacheManager;
    }
    
}
