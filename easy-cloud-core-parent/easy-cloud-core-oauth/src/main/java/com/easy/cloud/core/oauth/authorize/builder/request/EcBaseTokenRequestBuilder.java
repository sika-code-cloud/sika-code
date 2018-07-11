package com.easy.cloud.core.oauth.authorize.builder.request;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;

/**
 * @author daiqi
 * @create 2018-07-03 9:05
 */
public class EcBaseTokenRequestBuilder extends TokenRequestBuilder {
    public EcBaseTokenRequestBuilder(String url) {
        super(url);
    }
}
