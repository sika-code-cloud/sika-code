package com.easy.cloud.core.distributedlock.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.easy.cloud.core.distributedlock.template.EcDistributedLockTemplate;
import com.easy.cloud.core.distributedlock.template.impl.EcDistributedLockTemplateRedission;

@Configuration
public class EcDistributedLockConfig {

	@Value("classpath:redisson/redisson-conf.yml")
	Resource configFile;

	@Bean(destroyMethod = "shutdown")
	RedissonClient redisson()
	        throws IOException {
	    Config config = Config.fromYAML(configFile.getInputStream());
	    return Redisson.create(config);
	}

	@Bean
	EcDistributedLockTemplate distributedLockTemplate(RedissonClient redissonClient) {
	    return new EcDistributedLockTemplateRedission(redissonClient);
	}
}
