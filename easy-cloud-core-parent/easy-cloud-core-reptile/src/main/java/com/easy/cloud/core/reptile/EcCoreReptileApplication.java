package com.easy.cloud.core.reptile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;

/**
 * <p>
 * 爬虫启动类
 * </p>
 * @author daiqi
 * @date 2018/6/11 14:22
 * @return
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
@MapperScan({"**.dao"})
public class EcCoreReptileApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EcCoreReptileApplication.class, args);
	}
}
