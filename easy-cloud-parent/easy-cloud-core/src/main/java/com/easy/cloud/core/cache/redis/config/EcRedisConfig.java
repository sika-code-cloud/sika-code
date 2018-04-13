package com.easy.cloud.core.cache.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant;

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
public class EcRedisConfig {
	/**
	 * 注入 RedisConnectionFactory
	 */
	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	/**
	 * <p>
	 * 实例化 RedisTemplate 对象--保存的值为JSON字符串类型
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * @date 2017年12月7日 下午5:19:59
	 */
	@Bean(value = EcRedisConstant.REDIS_TEMPLATE_VALUE_STR_NAME)
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
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
	@Bean(value = EcRedisConstant.REDIS_TEMPLATE_VALUE_SERIALIZER_NAME)
	public RedisTemplate<String, Object> redisTemlateValueSerializer() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

}
