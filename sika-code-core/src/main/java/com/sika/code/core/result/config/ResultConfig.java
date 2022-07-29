package com.sika.code.core.result.config;

import com.sika.code.core.result.generator.ResultGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-07-03 21:29
 */
@Configuration
public class ResultConfig {

    @Bean
    @ConditionalOnMissingBean
    public ResultGenerator resultGenerator() {
        return new ResultGenerator();
    }
}
