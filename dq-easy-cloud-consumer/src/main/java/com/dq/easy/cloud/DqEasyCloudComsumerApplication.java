package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@AutoConfigureDataJpa
@PropertySource("classpath:application-server.properties")
public class DqEasyCloudComsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudComsumerApplication.class, args);
	}
}
