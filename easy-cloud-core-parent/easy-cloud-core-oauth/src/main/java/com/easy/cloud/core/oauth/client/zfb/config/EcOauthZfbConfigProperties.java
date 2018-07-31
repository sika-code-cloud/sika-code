package com.easy.cloud.core.oauth.client.zfb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-07-28 9:14
 */
@Component
@ConfigurationProperties(prefix="ec.oauth.zfb")
public class EcOauthZfbConfigProperties {
    private String tokenUrl;
    private String resourceUrl;

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

}
