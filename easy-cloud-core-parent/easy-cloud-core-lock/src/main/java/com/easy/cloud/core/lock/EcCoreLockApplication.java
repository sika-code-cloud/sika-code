package com.easy.cloud.core.lock;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 不自动注入数据源
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreLockApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreLockApplication.class, args);
	}
}
