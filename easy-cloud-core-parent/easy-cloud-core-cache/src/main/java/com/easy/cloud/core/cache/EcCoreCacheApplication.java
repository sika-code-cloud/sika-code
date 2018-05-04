package com.easy.cloud.core.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;


/**
 * 
 * <p>
 * 不自动注入数据源
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月4日 上午11:26:03
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD })
public class EcCoreCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcCoreCacheApplication.class, args);
	}
}
