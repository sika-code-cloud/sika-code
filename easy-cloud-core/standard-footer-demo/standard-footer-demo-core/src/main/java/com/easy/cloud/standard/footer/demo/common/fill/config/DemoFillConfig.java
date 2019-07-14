package com.easy.cloud.standard.footer.demo.common.fill.config;

import com.easy.cloud.database.common.handler.DefaultMetaObjectHandler;
import com.easy.cloud.standard.auth.properties.AuthProperties;
import com.easy.cloud.standard.footer.demo.common.fill.handler.DemoMetaObjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFillConfig {
    @Autowired
    private AuthProperties authProperties;

    @Bean
    @ConditionalOnMissingBean
    public DefaultMetaObjectHandler defaultMetaObjectHandler() {
        return new DemoMetaObjectHandler()
                .setJwtSecret(authProperties.getJwtSecret());
    }
}
