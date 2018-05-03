package com.easy.cloud.core.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD, "com.easy.cloud.core.jdbc"})
@MapperScan("com.easy.cloud.core.jdbc.**.dao")
public class EcCoreJdbcApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreJdbcApplication.class, args);
	}
}
