package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EcEurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcEurekaServerApplication.class,args);
	}
}
