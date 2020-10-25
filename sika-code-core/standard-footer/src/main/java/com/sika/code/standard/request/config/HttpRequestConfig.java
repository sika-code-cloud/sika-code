package com.sika.code.standard.request.config;

import com.sika.code.standard.base.constant.StandardPropertyConstant;
import com.sika.code.standard.request.filter.HttpRequestFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author daiqi
 * @create 2019-07-05 13:24
 */
@Configuration
@ConditionalOnProperty(value = StandardPropertyConstant.HTTP_REQUEST_FIRE, havingValue = "true", matchIfMissing = true)
public class HttpRequestConfig {

    @Bean
    @ConditionalOnProperty(value = StandardPropertyConstant.HTTP_REQUEST_FILTER_FIRE, havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean httpRequestFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new HttpRequestFilter());
        bean.setName("httpRequestFilter");
        bean.addUrlPatterns("/*");
        // 使用注解的order没有效果
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return bean;
    }
}
