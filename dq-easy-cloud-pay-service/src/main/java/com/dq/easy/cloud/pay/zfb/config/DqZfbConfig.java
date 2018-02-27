package com.dq.easy.cloud.pay.zfb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.dq.easy.cloud.pay.wx.service.DqWxPayService;
import com.dq.easy.cloud.pay.zfb.config.dto.DqZfbPayConfigStorageDTO;
import com.dq.easy.cloud.pay.zfb.service.DqZfbPayService;

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
public class DqZfbConfig {

	@Bean
	@ConfigurationProperties(prefix="zfb", ignoreUnknownFields = false)  
	public DqZfbPayConfigStorageDTO dqZfbPayConfigStorage(){
		return DqZfbPayConfigStorageDTO.newInstance();
	}
	
	@Bean
	public DqZfbPayService dqZfbPayService(){
		return new DqZfbPayService(dqZfbPayConfigStorage());
	}
}
