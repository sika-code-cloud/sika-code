package com.easy.cloud.core.oauth.authorize.builder.request.wechat;

import com.easy.cloud.core.oauth.authorize.builder.request.EcBaseTokenRequestBuilder;

/**
 * @author daiqi
 * @create 2018-07-02 16:29
 */
public class EcTokenWechatRequestBuilder extends EcBaseTokenRequestBuilder {
    public EcTokenWechatRequestBuilder(String url) {
        super(url);
    }

    @Override
    public EcTokenWechatRequestBuilder setClientId(String clientId) {
        this.parameters.put("appid", clientId);
        return this;
    }

    @Override
    public EcTokenWechatRequestBuilder setClientSecret(String secret) {
        this.parameters.put("secret", secret);
        return this;
    }
}
