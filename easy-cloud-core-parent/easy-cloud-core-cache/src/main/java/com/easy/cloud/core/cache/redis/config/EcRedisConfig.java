package com.easy.cloud.core.cache.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 
 * @ClassName : RedisConfig
 * @Description : redis配置文件
 * @author daiqi
 * @date 2017年12月7日 下午4:44:25
 *
 */
public class EcRedisConfig {
	@Autowired
	protected EcRedisProperties redisProperties;

	/**
	 * 创建连接
	 * 
	 * @param host
	 * @param port
	 * @param timeout
	 * @return
	 */
	public RedisConnectionFactory newRedisConnectionFactory(String host, int port,String password, int timeout) {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(timeout); // 设置连接超时时间
		factory.setPassword(password);

		// testOnBorrow为true时，返回的连接是经过测试可用的
		factory.setPoolConfig(redisProperties);
		factory.afterPropertiesSet();
		return factory;
	}

}
