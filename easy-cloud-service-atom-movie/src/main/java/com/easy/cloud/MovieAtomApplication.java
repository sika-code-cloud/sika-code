package com.easy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 
 * @ClassName : MovieAtomApplication 
 * @Description : 电影原子服务启动类 
 * @author daiqi
 * @date 2017年12月28日 下午2:15:26 
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MovieAtomApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieAtomApplication.class, args);
	}
}
