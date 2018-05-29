package com.easy.cloud.pay.zfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.pay.core.base.constant.EcEasyCloudPayComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { EcEasyCloudPayComponentScan.COM_EASY_CLOUD,
		EcEasyCloudPayComponentScan.COM_EASY_CLOUD_PAY_ZFB })
public class EcZfbPayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcZfbPayServiceApplication.class, args);
	}
}
