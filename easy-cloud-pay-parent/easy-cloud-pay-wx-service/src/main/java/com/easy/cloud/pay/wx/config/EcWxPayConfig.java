package com.easy.cloud.pay.wx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.core.common.http.pojo.dto.EcHttpConfigStorageDTO;
import com.easy.cloud.pay.wx.config.dto.EcWxPayConfigStorageDTO;
import com.easy.cloud.pay.wx.service.EcWxPayService;


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
public class EcWxPayConfig {
	
	@Bean
	@ConfigurationProperties(prefix="wx", ignoreUnknownFields = false)  
	public EcWxPayConfigStorageDTO EcWxPayConfigStorage(){
		return EcWxPayConfigStorageDTO.newInstance();
	}
	
	@Bean
	@ConfigurationProperties(prefix="wxkeystore", ignoreUnknownFields = false)  
	public EcHttpConfigStorageDTO EcHttpConfigStorage() {
		EcHttpConfigStorageDTO EcHttpConfigStorageDTO = new EcHttpConfigStorageDTO();
		EcHttpConfigStorageDTO.setPath(true);
		EcHttpConfigStorageDTO.setAuthUsername(EcWxPayConfigStorage().getPid());
		EcHttpConfigStorageDTO.setAuthPassword(EcWxPayConfigStorage().getPid());
		EcHttpConfigStorageDTO.setStorePassword(EcWxPayConfigStorage().getPid());
		return EcHttpConfigStorageDTO;
	}
	
	@Bean
	public EcWxPayService EcWxPayService(){
		return new EcWxPayService(EcWxPayConfigStorage(), EcHttpConfigStorage());
	}
}
