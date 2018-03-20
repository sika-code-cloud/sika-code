package com.dq.easy.cloud.pay.wx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.dq.easy.cloud.model.common.http.pojo.dto.DqHttpConfigStorageDTO;
import com.dq.easy.cloud.pay.wx.config.dto.DqWxPayConfigStorageDTO;
import com.dq.easy.cloud.pay.wx.service.DqWxPayService;


/**
 * 
 * <p>
 * 微信支付配置类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午7:40:46
 */
@Configuration
@PropertySource(value = {"classpath:config/wx.properties"}, ignoreResourceNotFound = true)
public class DqWxPayConfig {
	
	@Bean
	@ConfigurationProperties(prefix="wx", ignoreUnknownFields = false)  
	public DqWxPayConfigStorageDTO dqWxPayConfigStorage(){
		return DqWxPayConfigStorageDTO.newInstance();
	}
	
	@Bean
	@ConfigurationProperties(prefix="wxkeystore", ignoreUnknownFields = false)  
	public DqHttpConfigStorageDTO dqHttpConfigStorage() {
		DqHttpConfigStorageDTO dqHttpConfigStorageDTO = new DqHttpConfigStorageDTO();
		dqHttpConfigStorageDTO.setPath(true);
		dqHttpConfigStorageDTO.setAuthUsername(dqWxPayConfigStorage().getPid());
		dqHttpConfigStorageDTO.setAuthPassword(dqWxPayConfigStorage().getPid());
		dqHttpConfigStorageDTO.setStorePassword(dqWxPayConfigStorage().getPid());
		return dqHttpConfigStorageDTO;
	}
	
	@Bean
	public DqWxPayService dqWxPayService(){
		return new DqWxPayService(dqWxPayConfigStorage(), dqHttpConfigStorage());
	}
}
