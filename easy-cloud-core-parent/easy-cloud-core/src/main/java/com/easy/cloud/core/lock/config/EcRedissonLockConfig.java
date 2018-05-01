package com.easy.cloud.core.lock.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.easy.cloud.core.cache.redis.conditional.EcRedisConditional;
import com.easy.cloud.core.lock.template.EcLockTemplate;
import com.easy.cloud.core.lock.template.impl.EcLockTemplateRedission;

/**
 * 
 * <p>
 * redisson锁的配置文件
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月16日 上午9:52:48
 */
@Configuration
@Conditional(value = { EcRedisConditional.class })
public class EcRedissonLockConfig {

	@Value("classpath:redisson/redisson-conf.yml")
	Resource configFile;

	@Bean(destroyMethod = "shutdown")
	RedissonClient redisson() throws IOException {
		Config config = Config.fromYAML(configFile.getInputStream());
		return Redisson.create(config);
	}

	@Bean(name = "lockTemplateRedission")
	EcLockTemplate lockTemplateRedission(RedissonClient redissonClient) {
		return new EcLockTemplateRedission(redissonClient);
	}

}
