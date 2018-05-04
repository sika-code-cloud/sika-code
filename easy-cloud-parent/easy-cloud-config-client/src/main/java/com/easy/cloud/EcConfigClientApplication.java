package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
//Eureka客户端
@EnableDiscoveryClient
public class EcConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcConfigClientApplication.class, args);
	}
}
