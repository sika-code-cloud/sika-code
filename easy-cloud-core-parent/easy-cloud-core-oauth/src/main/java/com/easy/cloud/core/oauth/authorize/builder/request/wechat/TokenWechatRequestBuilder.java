package com.easy.cloud.core.oauth.authorize.builder.request.wechat;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;

/**
 * @author daiqi
 * @create 2018-07-02 16:29
 */
public class TokenWechatRequestBuilder extends TokenRequestBuilder {
    public TokenWechatRequestBuilder(String url) {
        super(url);
    }

    @Override
    public TokenRequestBuilder setClientId(String clientId) {
        this.parameters.put("appid", clientId);
        return this;
    }

    @Override
    public TokenRequestBuilder setClientSecret(String secret) {
        this.parameters.put("secret", secret);
        return this;
    }

    public TokenWechatRequestBuilder setLang(String lang) {
        this.parameters.put("lang", lang);
        return this;
    }
}
