package com.sika.code.gateway.interceptor.config;

import com.sika.code.common.rest.interceptor.RestTemplateInterceptor;
import com.sika.code.gateway.interceptor.GatewayRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-08-27 23:36
 */
@Configuration
public class InterceptorConfig {

    @Bean
    public RestTemplateInterceptor gatewayRestInterceptor() {
        return new GatewayRestInterceptor();
    }
}
