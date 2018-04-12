package com.easy.cloud.core.distributionlock.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.easy.cloud.core.distributionlock.template.EcDistributedLockTemplate;
import com.easy.cloud.core.distributionlock.template.impl.EcDistributedLockRedissionTemplate;

@Configuration
@PropertySource(value = { "classpath:redisson/redisson-conf.yml" }, ignoreResourceNotFound = true)
public class EcDistributedLockConfig {
	// @Value("classpath:redisson/redisson-conf.yml")
	// Resource configFile;
//	@ConfigurationProperties(prefix = "singleServerConfig", ignoreUnknownFields = false)
//	EcSingleServerConfigDTO singleServerConfigDTO() {
//		return new EcSingleServerConfigDTO();
//	}
//
//	@ConfigurationProperties(prefix = "config", ignoreUnknownFields = false)
//	EcConfigDTO configDTO() {
//		return new EcConfigDTO();
//	}

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
	    return new EcDistributedLockRedissionTemplate(redissonClient);
	}
}
