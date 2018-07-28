package com.easy.cloud.core.oauth.client.wechat.filter;

import com.easy.cloud.core.common.spring.EcSpringUtil;
import com.easy.cloud.core.oauth.client.base.filter.EcBaseOauthFilter;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.client.wechat.cofig.EcOauthWechatProperties;
import com.easy.cloud.core.oauth.client.wechat.token.EcWechatOauthToken;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.servlet.ServletRequest;

/**
 * 微信授权过滤器
 *
 * @author daiqi
 * @create 2018-06-29 17:40
 */
public class EcOauthWechatFilter extends EcBaseOauthFilter {

    @Override
    protected EcBaseOauthToken getOAuth2Token(ServletRequest httpRequest) {
        String code = httpRequest.getParameter(getOauthCodeKey());
        EcWechatOauthToken oauthToken = new EcWechatOauthToken(code);
        EcOauthWechatProperties wechatProperties= getOauthWechatProperties();
        oauthToken.clientId(wechatProperties.getClientId());
        oauthToken.clientSecret(wechatProperties.getClientSecret());
        oauthToken.redirectUrl(wechatProperties.getRedirectUrl());
        oauthToken.grantType(GrantType.AUTHORIZATION_CODE);
        oauthToken.tokenRequestBuilder(wechatProperties.getTokenUrl());
        oauthToken.resourceRequestBuilder(wechatProperties.getResourceUrl());

        return oauthToken;
    }

    @Override
    protected String getOauthCodeKey() {
        return "code";
    }

    protected EcOauthWechatProperties getOauthWechatProperties() {
        return EcSpringUtil.getBean(EcOauthWechatProperties.class);
    }
}
