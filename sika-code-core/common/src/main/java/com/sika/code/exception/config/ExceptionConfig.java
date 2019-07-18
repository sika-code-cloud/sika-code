package com.sika.code.exception.config;

import com.sika.code.exception.properties.ExceptionProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-03-22 17:31
 */
@Configuration
public class ExceptionConfig {
    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "sika.code.exception")
    public ExceptionProperties exceptionProperties() {
        return new ExceptionProperties();
    }
}
