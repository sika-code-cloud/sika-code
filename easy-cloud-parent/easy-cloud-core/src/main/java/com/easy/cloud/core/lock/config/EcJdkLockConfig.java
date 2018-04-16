package com.easy.cloud.core.lock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.easy.cloud.core.lock.template.EcLockTemplate;
import com.easy.cloud.core.lock.template.impl.EcLockTemplateJdk;

/**
 * 
 * <p>
 * jdk锁的配置文件
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月16日 上午9:53:17
 */
@Configuration
public class EcJdkLockConfig {

	@Bean
	EcLockTemplate jdkLockTemplate() {
	    return new EcLockTemplateJdk();
	}
}
