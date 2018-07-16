package com.easy.cloud.core.oauth.authorize.base.request.builder;

import com.easy.cloud.core.oauth.authorize.base.response.resource.EcBaseOauthTokenResponse;
import com.easy.cloud.core.oauth.authorize.base.token.EcBaseOauthToken;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-03 9:05
 */
public abstract class EcBaseTokenRequestBuilder extends TokenRequestBuilder {

    public EcBaseTokenRequestBuilder(String url) {
        super(url);
    }

    public abstract Class<? extends EcBaseOauthTokenResponse> getTokenResponseClass();


    /**
     * <p>
     * 构建授权客户端请求对象
     * </p>
     *
     * @param oauthTokenParam
     * @return org.apache.oltu.oauth2.client.request.OAuthClientRequest
     * @author daiqi
     * @date 2018/7/16 14:48
     */
    public abstract OAuthClientRequest buildClientRequest(Map<String, Object> oauthTokenParam) throws OAuthSystemException;
}
