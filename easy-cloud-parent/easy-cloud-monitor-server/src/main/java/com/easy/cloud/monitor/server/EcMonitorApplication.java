package com.easy.cloud.monitor.server;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 监控启动类
 * </p>
 *
 * @author daiqi
 * @date 2018-07-19 19:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {EcBaseComponentScan.COM_EASY_CLOUD})
@EnableAdminServer
public class EcMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcMonitorApplication.class, args);
    }

}
