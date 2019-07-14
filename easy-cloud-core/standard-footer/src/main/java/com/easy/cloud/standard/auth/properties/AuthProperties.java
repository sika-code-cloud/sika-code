package com.easy.cloud.standard.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 认证配置类
 *
 * @author daiqi
 * @create 2019-05-16 9:12
 */
@Data
@ConfigurationProperties(prefix = "easy.cloud.auth")
@Component
public class AuthProperties {
    private String jwtSecret;
}
