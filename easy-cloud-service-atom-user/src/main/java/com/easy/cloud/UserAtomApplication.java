package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @ClassName : UserAtomApplication
 * @Description : 用户原子服务启动类
 * @author daiqi
 * @date 2017年12月28日 下午2:18:39
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages={"com.dq.easy.cloud","com.easy.cloud"})
public class UserAtomApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserAtomApplication.class, args);
	}
}
