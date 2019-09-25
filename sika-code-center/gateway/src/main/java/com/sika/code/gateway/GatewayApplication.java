package com.sika.code.gateway;

import com.sika.code.basic.constant.BaseComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动类
 *
 * @author daiqi
 * @create 2019-08-20 23:45
 */
@SpringBootApplication(scanBasePackages = {BaseComponentScan.COM_EASY_CLOUD}, exclude = {DataSourceAutoConfiguration.class})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
