package com.sika.code.standard.footer.demo;

import com.sika.code.basic.constant.BaseComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 启动类
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}非常重要，如果没有关闭，在启动程序时会发生循环依赖的错误
 *
 * @author daiqi
 * @create 2019-05-10 21:16
 */
@SpringBootApplication(scanBasePackages = {BaseComponentScan.COM_EASY_CLOUD})
//@MapperScan({"com.sika.code.**.mapper"})
public class StandardFooterDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StandardFooterDemoApplication.class, args);
    }
}
