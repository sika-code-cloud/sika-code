package com.easy.cloud.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 权限启动类
 * </p>
 *
 * @author daiqi
 * @date 2018/6/27 12:24
 * @return
 */
@SpringBootApplication
@ComponentScan(basePackages = {EcBaseComponentScan.COM_EASY_CLOUD})
@MapperScan({"**.dao"})
public class EcCoreOauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcCoreOauthApplication.class, args);
    }
}
