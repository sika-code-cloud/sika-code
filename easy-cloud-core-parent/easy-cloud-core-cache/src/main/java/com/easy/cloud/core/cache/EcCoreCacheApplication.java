package com.easy.cloud.core.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * 
 * @author daiqi
 * @创建时间 2018年5月4日 上午11:26:03
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD })
public class EcCoreCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreCacheApplication.class, args);
	}
}
