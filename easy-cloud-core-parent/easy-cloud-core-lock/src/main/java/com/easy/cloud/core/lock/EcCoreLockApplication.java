package com.easy.cloud.core.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreLockApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreLockApplication.class, args);
	}
}
