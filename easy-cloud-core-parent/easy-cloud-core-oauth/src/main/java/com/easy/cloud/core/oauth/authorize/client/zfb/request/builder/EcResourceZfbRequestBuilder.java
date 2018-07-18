package com.easy.cloud.core.oauth.authorize.client.zfb.request.builder;

import com.easy.cloud.core.oauth.authorize.client.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.authorize.client.base.response.token.EcBaseOauthResourceResponse;
import com.easy.cloud.core.oauth.authorize.client.zfb.response.resource.EcZfbOauthResourceResponse;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-02 17:11
 */
public class EcResourceZfbRequestBuilder extends EcBaseResourceRequestBuilder {

    public EcResourceZfbRequestBuilder(String url) {
        super(url);
    }

    @Override
    public Class<? extends EcBaseOauthResourceResponse> getResourceResponseClass() {
        return EcZfbOauthResourceResponse.class;
    }

    @Override
    public OAuthClientRequest buildClientRequest(Map<String, Object> resourceRequestParam) throws OAuthSystemException {
       return null;
    }
}
