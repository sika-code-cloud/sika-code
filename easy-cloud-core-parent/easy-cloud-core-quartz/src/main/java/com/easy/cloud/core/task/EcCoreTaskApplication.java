package com.easy.cloud.core.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;

/**
 * <p>
 * 定时任务启动类
 * </p>
 *
 * @author daiqi
 * @date 2018/6/21 14:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {EcBaseComponentScan.COM_EASY_CLOUD})
@MapperScan({"**.dao"})
public class EcCoreTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcCoreTaskApplication.class, args);
    }
}

