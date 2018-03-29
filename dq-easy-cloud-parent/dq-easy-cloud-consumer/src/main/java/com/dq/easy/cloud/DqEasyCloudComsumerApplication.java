package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
//@PropertySource("classpath:application-consumer.properties")
@EnableDiscoveryClient
public class DqEasyCloudComsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudComsumerApplication.class, args);
	}
}
