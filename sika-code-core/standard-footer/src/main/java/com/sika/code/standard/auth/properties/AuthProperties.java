package com.sika.code.standard.auth.properties;

import com.sika.code.standard.base.constant.StandardPropertiesConstant;
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
@ConfigurationProperties(prefix = StandardPropertiesConstant.AUTH)
@Component
public class AuthProperties {
    private String jwtSecret;
}
