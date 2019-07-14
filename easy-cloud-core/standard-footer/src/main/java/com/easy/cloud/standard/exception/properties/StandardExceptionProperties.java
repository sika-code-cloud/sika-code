package com.easy.cloud.standard.exception.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2019-05-15 14:08
 */
 @Component
 @Data
 @ConfigurationProperties("easy.cloud.exception.inform")
public class StandardExceptionProperties {
    /** 钉钉通知群url */
    private String defaultWebHookForDingding;

}
