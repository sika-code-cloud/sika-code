package com.easy.cloud.core.oauth.client.base.request.builder;

import com.easy.cloud.core.oauth.client.base.response.token.EcBaseOauthResourceResponse;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-13 17:41
 */
public abstract class EcBaseResourceRequestBuilder extends OAuthClientRequest.TokenRequestBuilder {

    public EcBaseResourceRequestBuilder(String url) {
        super(url);
    }

    public abstract Class<? extends EcBaseOauthResourceResponse> getResourceResponseClass();

    public abstract OAuthClientRequest buildClientRequest(Map<String, Object> resourceRequestParam) throws OAuthSystemException;
}
