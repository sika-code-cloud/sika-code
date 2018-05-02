package com.easy.cloud.core.cache.redis.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;

import redis.clients.jedis.JedisPoolConfig;

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
@EnableCaching
public class EcRedisConfig {

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * 创建连接
	 * 
	 * @param host
	 * @param port
	 * @param timeout
	 * @return
	 */
	public JedisConnectionFactory newJedisConnectionFactory(String host, int port, int timeout) {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(timeout); // 设置连接超时时间

		// testOnBorrow为true时，返回的连接是经过测试可用的
		factory.setPoolConfig(jedisPoolConfig());

		return factory;
	}

	public JedisPoolConfig jedisPoolConfig() {
		return null;
	}

}
