package com.sika.code.batch;

import com.sika.code.basic.constant.BaseComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 统一授权认证中心
 *
 * @author daiqi
 * @create 2019-08-22 23:01
 */
@SpringBootApplication(scanBasePackages = {BaseComponentScan.COM_EASY_CLOUD}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.sika.code.**.mapper"})
public class BatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
