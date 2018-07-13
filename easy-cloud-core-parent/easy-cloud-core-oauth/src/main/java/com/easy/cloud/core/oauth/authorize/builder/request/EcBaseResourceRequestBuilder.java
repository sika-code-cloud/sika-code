package com.easy.cloud.core.oauth.authorize.builder.request;

import org.apache.oltu.oauth2.common.OAuth;

/**
 * @author daiqi
 * @create 2018-07-13 17:41
 */
public abstract class EcBaseResourceRequestBuilder extends EcBaseTokenRequestBuilder {
    public EcBaseResourceRequestBuilder(String url) {
        super(url);
    }

    public EcBaseResourceRequestBuilder setAccessToken(String accessToken) {
        this.parameters.put(OAuth.OAUTH_BEARER_TOKEN, accessToken);
        return this;
    }
    public EcBaseResourceRequestBuilder setOpenId(String openId) {
       return this;
    }

    public EcBaseResourceRequestBuilder setLang(String lang) {
        this.parameters.put("lang", lang);
        return this;
    }
}
