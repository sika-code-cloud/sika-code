package com.easy.cloud.core.oauth.client.wechat.filter;

import com.easy.cloud.core.oauth.client.base.filter.EcBaseOauthFilter;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.client.wechat.token.EcWechatOauthToken;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

/**
 * 微信授权过滤器
 *
 * @author daiqi
 * @create 2018-06-29 17:40
 */
@Component
public class EcOauthWechatFilter extends EcBaseOauthFilter {
    @Value(value = "${ec.oauth.wechat.token.url}")
    private String tokenUrl;
    @Value(value = "${ec.oauth.wechat.resource.url}")
    private String resourceUrl;
    @Value(value = "${ec.oauth.wechat.appid}")
    private String clientId;
    @Value(value = "${ec.oauth.wechat.appsecret}")
    private String clientSecret;
    @Value(value = "${ec.oauth.wechat.redirect.url}")
    private String redirectUrl;

    @Override
    protected EcBaseOauthToken getOAuth2Token(ServletRequest httpRequest) {
        String code = httpRequest.getParameter(getOauthCodeKey());
        EcWechatOauthToken oauthToken = new EcWechatOauthToken(code);
        oauthToken.clientId(clientId);
        oauthToken.clientSecret(clientSecret);
        oauthToken.redirectUrl(redirectUrl);
        oauthToken.grantType(GrantType.AUTHORIZATION_CODE);
        oauthToken.tokenRequestBuilder(tokenUrl);
        oauthToken.resourceRequestBuilder(resourceUrl);

        return oauthToken;
    }

    @Override
    protected String getOauthCodeKey() {
        return "code";
    }
}
