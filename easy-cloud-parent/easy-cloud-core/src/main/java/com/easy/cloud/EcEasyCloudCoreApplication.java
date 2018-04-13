package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * 
 * @author daiqi
 * @date 2018年3月18日 上午12:47:25
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcEasyCloudCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcEasyCloudCoreApplication.class, args);
	}
	
}
