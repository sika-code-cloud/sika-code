package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
//Eureka客户端
@EnableDiscoveryClient
public class DqEasyCloudConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudConfigServerApplication.class, args);
	}
}
