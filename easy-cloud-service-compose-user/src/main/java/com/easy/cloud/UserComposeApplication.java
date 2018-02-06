package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.base.constant.EasyCloudComponentScan;

/**
 * 
 * @ClassName : UserComposeApplication
 * @Description : 用户组合服务启动类
 * @author daiqi
 * @date 2017年12月28日 下午2:40:59
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages={EasyCloudComponentScan.COM_DQ_EASY_CLOUD, EasyCloudComponentScan.COM_EASY_CLOUD})
public class UserComposeApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserComposeApplication.class, args);
	}
}
