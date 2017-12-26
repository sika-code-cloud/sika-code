package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DqEasyCloudEurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudEurekaServerApplication.class,args);
	}
}
