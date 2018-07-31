package com.easy.cloud.core.oauth.client.wechat.cofig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-07-28 9:17
 */
@Component
@ConfigurationProperties(prefix="ec.oauth.wechat")
public class EcOauthWechatProperties {
    private String tokenUrl;
    private String resourceUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setAppId(String appId) {
        this.clientId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.clientSecret = appSecret;
    }
}
