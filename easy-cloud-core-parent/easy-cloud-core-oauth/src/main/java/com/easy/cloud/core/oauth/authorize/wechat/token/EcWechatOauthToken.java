package com.easy.cloud.core.oauth.authorize.wechat.token;

import com.easy.cloud.core.oauth.authorize.base.config.EcAccessTokenChannelConfig;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseTokenRequestBuilder;
import com.easy.cloud.core.oauth.authorize.wechat.pojo.dto.EcWechatAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.wechat.request.builder.EcResourceWechatRequestBuilder;
import com.easy.cloud.core.oauth.authorize.wechat.request.builder.EcTokenWechatRequestBuilder;
import com.easy.cloud.core.oauth.authorize.base.token.EcBaseOauthToken;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-16 9:23
 */
public class EcWechatOauthToken extends EcBaseOauthToken {

    public EcWechatOauthToken(String authCode) {
        super(authCode);
        putTokenRequestParam("code", authCode);
    }


    public EcWechatOauthToken clientId(String clientId) {
        putTokenRequestParam("appid", clientId);
        return this;
    }

    public EcWechatOauthToken clientSecret(String clientSecret) {
        putTokenRequestParam("secret", clientSecret);
        return this;
    }

    public EcWechatOauthToken redirectUrl(String redirectUrl) {
        putTokenRequestParam("redirectUrl", redirectUrl);
        return this;
    }
    public EcWechatOauthToken grantType(GrantType grantType) {
        putTokenRequestParam(OAuth.OAUTH_GRANT_TYPE,  grantType == null ? null : grantType.toString());
        return this;
    }
    @Override
    public EcBaseTokenRequestBuilder getTokenRequestBuilder() {
        return new EcTokenWechatRequestBuilder(tokenRequestUrl);
    }

    @Override
    public EcBaseResourceRequestBuilder getResourceRequestBuilder() {
        return new EcResourceWechatRequestBuilder(resourceRequestUrl);
    }

    @Override
    public EcBaseAccessTokenDTO getAccessTokenDTO(Map<String, Object> tokenRequestParam) {
        return new EcWechatAccessTokenDTO(tokenRequestParam);
    }

    @Override
    public Integer getAccessTokenChannel() {
        return EcAccessTokenChannelConfig.WECHAT;
    }
}
