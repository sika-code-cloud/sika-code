package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class DqEasyCloudProducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudProducerApplication.class, args);
	}
}
