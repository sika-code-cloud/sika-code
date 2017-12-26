package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class DqEasyCloudModelApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudModelApplication.class, args);
	}
}
