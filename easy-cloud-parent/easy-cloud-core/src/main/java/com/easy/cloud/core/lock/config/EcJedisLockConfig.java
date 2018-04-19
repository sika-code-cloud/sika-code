package com.easy.cloud.core.lock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;
import com.easy.cloud.core.cache.redis.config.EcRedisConfig;
import com.easy.cloud.core.lock.template.EcLockTemplate;
import com.easy.cloud.core.lock.template.impl.EcLockTemplateJedis;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Conditional(value = { EcRedisConditional.class })
@PropertySource("classpath:config/redis-lock.properties")
public class EcJedisLockConfig extends EcRedisConfig {
	@Value("${redis.hostName}")
	private String hostName;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.port}")
	private Integer port;
	@Value("${redis.maxIdle}")
	private Integer maxIdle;

	@Value("${redis.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${redis.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;

	@Value("${redis.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;

	@Value("${redis.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${redis.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${redis.testWhileIdle}")
	private boolean testWhileIdle;

	/**
	 * JedisPoolConfig 连接池
	 * 
	 * @return
	 */
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

	@Bean(name = "jedisConnectionFactoryLcok")
	public JedisConnectionFactory jedisConnectionFactoryLcok() {
		return newJedisConnectionFactory(hostName, port, 5000);
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
	@Bean
	public StringRedisTemplate stringRedisTemplateLock() {
		return new StringRedisTemplate(jedisConnectionFactoryLcok());
	}

	@Bean(name = "lockTemplateJedis")
	public EcLockTemplate lockTemplateJedis() {
		StringRedisTemplate redisTemplate = stringRedisTemplateLock();
		return new EcLockTemplateJedis(redisTemplate);
	}
}
