package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.dq.easy.cloud.model.basic.constant.DqBaseComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@AutoConfigureDataJpa
@ComponentScan(basePackages = { DqBaseComponentScan.COM_DQ_EASY_CLOUD})
public class DqEasyCloudModelApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudModelApplication.class, args);
	}
	
}
