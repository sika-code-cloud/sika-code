package com.sika.code.standard.footer.demo.common.demo.config;

import com.sika.code.standard.footer.demo.common.demo.interceptor.CreditCustomerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 风控配置类
 *
 * @author daiqi
 * @create 2019-05-29 20:16
 */
@Configuration
public class DemoConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CreditCustomerInterceptor initCreditCustomerInterceptor() {
        return new CreditCustomerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initCreditCustomerInterceptor()).addPathPatterns("/**");
    }
}
