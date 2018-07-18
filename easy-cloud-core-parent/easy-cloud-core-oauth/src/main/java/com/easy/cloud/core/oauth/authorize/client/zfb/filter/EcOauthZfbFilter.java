package com.easy.cloud.core.oauth.authorize.client.zfb.filter;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.easy.cloud.core.oauth.authorize.client.base.filter.EcBaseOauthFilter;
import com.easy.cloud.core.oauth.authorize.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.authorize.client.zfb.token.EcZfbOauthToken;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class EcOauthZfbFilter extends EcBaseOauthFilter {
    @Value(value = "${ec.oauth.zfb.gateway.url}")
    private String tokenUrl;
    @Value(value = "${ec.oauth.zfb.gateway.url}")
    private String resourceUrl;

    @Autowired
    @Qualifier(value = "defaultAliPayClient")
    private AlipayClient alipayClient;

    @Override
    protected EcBaseOauthToken getOAuth2Token(ServletRequest httpRequest) {
        EcZfbOauthToken oauthToken = new EcZfbOauthToken(httpRequest.getParameter(getOauthCodeKey()));
        oauthToken.alipayClient(alipayClient)
                .grantType(GrantType.AUTHORIZATION_CODE)
                .tokenRequestBuilder(tokenUrl)
                .resourceRequestBuilder(resourceUrl);
        return oauthToken;
    }

    @Override
    protected String getOauthCodeKey() {
        return "auth_code";
    }
}
