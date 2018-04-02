package com.dq.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.dq.easy.cloud.module.basic.constant.DqBaseComponentScan;


/**
 * 
 * @author daiqi
 * @date 2018年3月18日 上午12:47:25
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = { DqBaseComponentScan.COM_DQ_EASY_CLOUD})
public class DqEasyCloudModelApplication {
	public static void main(String[] args) {
		SpringApplication.run(DqEasyCloudModelApplication.class, args);
	}
	
}
