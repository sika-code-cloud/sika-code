package com.easy.cloud.core.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreCacheApplication.class, args);
	}
}

