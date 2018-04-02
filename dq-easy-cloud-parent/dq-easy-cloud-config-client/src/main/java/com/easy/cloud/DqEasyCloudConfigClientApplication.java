package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
//Eureka客户端
@EnableDiscoveryClient
public class DqEasyCloudConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudConfigClientApplication.class, args);
	}
}
