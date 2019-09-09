package com.sika.code.gateway.config;

import com.sika.code.gateway.filter.Customer1GatewayFilterFactory;
import com.sika.code.gateway.filter.CustomerGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-08-26 23:03
 */
@Configuration
public class CustomerConfig {

    @Bean
    public CustomerGatewayFilterFactory customerFilter() {
        return new CustomerGatewayFilterFactory();
    }
    @Bean
    public Customer1GatewayFilterFactory customer1Filter() {
        return new Customer1GatewayFilterFactory();
    }
}
