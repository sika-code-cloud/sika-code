package com.sika.code.standard.fill.config;

import com.sika.code.standard.auth.properties.AuthProperties;
import com.sika.code.database.common.handler.DefaultMetaObjectHandler;
import com.sika.code.standard.fill.handler.StandardMetaObjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 填充配置类
 *
 * @author daiqi
 * @create 2019-05-16 9:13
 */
@Configuration
public class FillConfig {
    @Autowired
    private AuthProperties authProperties;

    @Bean
    @ConditionalOnMissingBean
    public DefaultMetaObjectHandler defaultMetaObjectHandler() {
        return new StandardMetaObjectHandler().setJwtSecret(authProperties.getJwtSecret());
    }
}
