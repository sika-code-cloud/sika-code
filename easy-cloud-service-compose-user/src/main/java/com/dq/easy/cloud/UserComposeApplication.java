package com.dq.easy.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 
 * @ClassName : UserComposeApplication 
 * @Description : 用户组合服务启动类 
 * @author daiqi
 * @date 2017年12月28日 下午2:40:59 
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserComposeApplication {

}
