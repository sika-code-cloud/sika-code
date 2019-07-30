package com.sika.code.standard.exception.properties;

import com.sika.code.standard.base.constant.StandardPropertiesConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2019-05-15 14:08
 */
 @Component
 @Data
 @ConfigurationProperties(StandardPropertiesConstant.EXCEPTION_INFORM)
public class StandardExceptionProperties {
    /** 钉钉通知群url */
    private String defaultWebHookForDingding;

}
