package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * 
 * @author daiqi
 * @date 2018年3月18日 上午12:47:25
 */
@SpringBootApplication
// 扫描ServletContextListener的监听器
@ServletComponentScan
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreApplication.class, args);
	}
	
}
