package com.sika.code.demo.interfaces;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}非常重要，如果没有关闭，在启动程序时会发生循环依赖的错误
 *
 * @author daiqi
 * @create 2019-05-10 21:16
 */
@SpringBootApplication(scanBasePackages = {"com.sika.code.demo", "com.sika.code"})
@MapperScan({"com.sika.code.demo.**.mapper"})
@Slf4j
@EnableDynamicThreadPool
@EnableDynamicTp
@EnableDiscoveryClient
@EnableApolloConfig
public class SikaCodeDemoApplication {
    //    static {
    //        System.setProperty("log4j2.isThreadContextMapInherimeble", "true");
    //        System.setProperty("dubbo.application.logger", "log4j2");
    //    }
    public static void main(String[] args) {
        System.setProperty("env", "dev");
        System.setProperty("apollo.config-service", "http://apollo.configservice.sikacode.com/");
        SpringApplication.run(SikaCodeDemoApplication.class, args);

    }

}
