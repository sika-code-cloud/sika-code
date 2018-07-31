package com.easy.cloud.core.jdbc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 声明是配置类
@EnableConfigurationProperties(SqlLogInterceptorProperties.class)
// 开启属性注入,以@EnableConfigurationProperties声明，使用@Autowired注入
@ConditionalOnClass(SqlLogInterceptorConfig.class)// 当SqlLogInterceptorConfig在类路劲的条件下
@ConditionalOnProperty(prefix = "logging.sql", value = "enabled", matchIfMissing = true)
// // 当设置logging.sql=enabled的情况下，如果没有设置默认是true，即符合条件
public class SqlLogInterceptorAutoConfiguration {

    @Autowired
    private SqlLogInterceptorProperties sqlLogInterceptorProperties;

    @Bean
    @ConditionalOnMissingBean(SqlLogInterceptorConfig.class)
    public SqlLogInterceptorConfig sqlLogInterceptorConfig() {
        SqlLogInterceptorConfig sqlLogInterceptorConfig = new SqlLogInterceptorConfig();
        sqlLogInterceptorConfig.setOpenLog(sqlLogInterceptorProperties.getOpenLog());
        return sqlLogInterceptorConfig;
    }
}