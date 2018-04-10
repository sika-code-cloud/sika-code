package com.easy.cloud.pay.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.pay.common.base.constant.EcEasyCloudPayComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { EcEasyCloudPayComponentScan.COM_EASY_CLOUD,
		EcEasyCloudPayComponentScan.COM_EASY_CLOUD_PAY_WX })
public class EcEasyCloudWxPayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcEasyCloudWxPayServiceApplication.class, args);
	}
}
