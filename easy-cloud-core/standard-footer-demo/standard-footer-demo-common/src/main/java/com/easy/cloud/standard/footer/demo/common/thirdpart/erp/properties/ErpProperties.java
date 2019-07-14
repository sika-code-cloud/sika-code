package com.easy.cloud.standard.footer.demo.common.thirdpart.erp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * erp的属性类
 *
 * @author daiqi
 * @create 2019-04-04 17:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "easy.cloud.thirdparty.erp")
public class ErpProperties {
    /**
     * AppId
     */
    private String appId;
    /**
     * appSecret
     */
    private String appSecret;
}
