package com.easy.cloud.core.oauth.client.zfb.filter;

import com.alipay.api.AlipayClient;
import com.easy.cloud.core.common.spring.EcSpringUtil;
import com.easy.cloud.core.oauth.client.base.filter.EcBaseOauthFilter;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.client.zfb.config.EcOauthZfbConfigProperties;
import com.easy.cloud.core.oauth.client.zfb.token.EcZfbOauthToken;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.servlet.ServletRequest;

/**
 * 支付宝授权过滤器
 *
 * @author daiqi
 * @create 2018-06-29 17:40
 */
public class EcOauthZfbFilter extends EcBaseOauthFilter {

    @Override
    protected EcBaseOauthToken getOAuth2Token(ServletRequest httpRequest) {
        EcZfbOauthToken oauthToken = new EcZfbOauthToken(httpRequest.getParameter(getOauthCodeKey()));
        oauthToken.alipayClient(getAlipayClient())
                .grantType(GrantType.AUTHORIZATION_CODE)
                .tokenRequestBuilder(getEcZfbConfigProperties().getTokenUrl())
                .resourceRequestBuilder(getEcZfbConfigProperties().getResourceUrl());
        return oauthToken;
    }

    @Override
    protected String getOauthCodeKey() {
        return "auth_code";
    }

    protected AlipayClient getAlipayClient() {
        return EcSpringUtil.getBean("defaultAliPayClient", AlipayClient.class);
    }

    protected EcOauthZfbConfigProperties getEcZfbConfigProperties() {
        return EcSpringUtil.getBean(EcOauthZfbConfigProperties.class);
    }
}
