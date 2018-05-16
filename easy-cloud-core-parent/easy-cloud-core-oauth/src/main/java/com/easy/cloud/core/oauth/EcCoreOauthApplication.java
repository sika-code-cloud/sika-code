package com.easy.cloud.core.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
public class EcCoreOauthApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreOauthApplication.class, args);
	}
}
