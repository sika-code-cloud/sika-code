package com.easy.cloud.core.lock.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;
import com.easy.cloud.core.cache.redis.config.EcRedisConfig;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisTemplateName;
import com.easy.cloud.core.lock.template.EcLockTemplate;
import com.easy.cloud.core.lock.template.impl.EcLockTemplateJedis;

/**
 * 实用jedis作为分布式锁的解决方案
 * 
 * @author daiqi
 * @date 2018年4月20日 下午9:14:04
 */
@Configuration
@Conditional(value = { EcRedisConditional.class })
@PropertySource("classpath:config/redis-lock.properties")
public class EcJedisLockConfig extends EcRedisConfig {
	
	@Bean(value = "redisConnectionFactoryLcok", destroyMethod = "destroy")
	@ConfigurationProperties(prefix = "ec.redis")
	public RedisConnectionFactory redisConnectionFactoryLcok() {
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
	@Bean(value = "stringRedisTemplateLock")
	public StringRedisTemplate stringRedisTemplateLock(@Qualifier(value = "redisConnectionFactoryLcok") RedisConnectionFactory redisConnectionFactory) {
		return new StringRedisTemplate(redisConnectionFactory);
	}

	@Bean(name = EcRedisTemplateName.REDIS_TEMPLATE_VALUE_STR_LOCK_NAME)
	public EcLockTemplate lockTemplateJedis(@Qualifier(value = "stringRedisTemplateLock") StringRedisTemplate stringRedisTemplate) {
		return new EcLockTemplateJedis(stringRedisTemplate);
	}
}
