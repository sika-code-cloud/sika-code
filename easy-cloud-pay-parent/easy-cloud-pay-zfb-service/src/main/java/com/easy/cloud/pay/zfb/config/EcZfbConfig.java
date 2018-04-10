package com.easy.cloud.pay.zfb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.pay.zfb.config.dto.EcZfbPayConfigStorageDTO;
import com.easy.cloud.pay.zfb.service.EcZfbPayService;

/**
 * 
 * <p>
 * 支付宝支付配置类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午7:40:46
 */
@Configuration
@PropertySource(value = {"classpath:config/zfb.properties"}, ignoreResourceNotFound = true)
public class EcZfbConfig {

	@Bean
	@ConfigurationProperties(prefix="zfb", ignoreUnknownFields = false)  
	public EcZfbPayConfigStorageDTO dqZfbPayConfigStorage(){
		return EcZfbPayConfigStorageDTO.newInstance();
	}
	
	@Bean
	public EcZfbPayService ecZfbPayService(){
		return new EcZfbPayService(dqZfbPayConfigStorage());
	}
}
