package com.dq.easy.cloud.pay.zfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.dq.easy.cloud.pay.model.base.constant.DqEasyCloudPayComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@AutoConfigureDataJpa
@ComponentScan(basePackages = { DqEasyCloudPayComponentScan.COM_DQ_EASY_CLOUD,
		DqEasyCloudPayComponentScan.COM_DQ_EASY_CLOUD_PAY_ZFB })
public class DqEasyCloudZfbPayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudZfbPayServiceApplication.class, args);
	}
}
