package com.easy.cloud.core.oauth.client.base.config;

import com.easy.cloud.core.oauth.client.base.service.EcOauthService;
import com.easy.cloud.core.oauth.client.base.service.impl.EcMemoryOauthServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2018-07-25 17:29
 */
@Configuration
public class EcOauthConfig {
    @Bean
    @ConditionalOnMissingBean
    public EcOauthService oauthService() {
        return new EcMemoryOauthServiceImpl();
    }


}
