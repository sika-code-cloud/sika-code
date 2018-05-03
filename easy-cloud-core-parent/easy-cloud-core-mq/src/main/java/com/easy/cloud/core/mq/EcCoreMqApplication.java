package com.easy.cloud.core.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * EcCoreMqApplication
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreMqApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreMqApplication.class, args);
	}
}
