package com.sika.code.demo.sharding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/10 9:12
 */
@SpringBootApplication(scanBasePackages = {"com.sika.code.demo.sharding"})
@MapperScan({"com.sika.code.demo.sharding.**.mapper"})
@Slf4j
public class DemoShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoShardingApplication.class, args);

    }

}
