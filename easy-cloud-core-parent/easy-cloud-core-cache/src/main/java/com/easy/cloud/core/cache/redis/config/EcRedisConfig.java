package com.easy.cloud.core.cache.redis.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @ClassName : RedisConfig
 * @Description : redis配置文件
 * @author daiqi
 * @date 2017年12月7日 下午4:44:25
 *
 */
@EnableCaching
public class EcRedisConfig {
	@Value("${ec.redis.maxIdle}")
	protected Integer maxIdle;

	@Value("${ec.redis.maxTotal}")
	protected Integer maxTotal;

	@Value("${ec.redis.maxWaitMillis}")
	protected Integer maxWaitMillis;

	@Value("${ec.redis.minEvictableIdleTimeMillis}")
	protected Integer minEvictableIdleTimeMillis;

	@Value("${ec.redis.numTestsPerEvictionRun}")
	protected Integer numTestsPerEvictionRun;

	@Value("${ec.redis.timeBetweenEvictionRunsMillis}")
	protected long timeBetweenEvictionRunsMillis;

	@Value("${ec.redis.testOnBorrow}")
	protected boolean testOnBorrow;

	@Value("${ec.redis.testWhileIdle}")
	protected boolean testWhileIdle;

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
	public RedisConnectionFactory newRedisConnectionFactory(String host, int port,String password, int timeout) {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(timeout); // 设置连接超时时间
		factory.setPassword(password);

		// testOnBorrow为true时，返回的连接是经过测试可用的
		factory.setPoolConfig(jedisPoolConfig());
		factory.afterPropertiesSet();
		return factory;
	}

	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		// 在空闲时检查有效性, 默认false
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}

}
