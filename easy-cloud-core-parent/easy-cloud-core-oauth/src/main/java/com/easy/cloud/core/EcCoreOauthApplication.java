package com.easy.cloud.core;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan({"**.dao"})
public class EcCoreOauthApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreOauthApplication.class, args);
	}
}
