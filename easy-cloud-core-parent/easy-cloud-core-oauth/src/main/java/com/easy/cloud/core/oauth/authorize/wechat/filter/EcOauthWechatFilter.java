package com.easy.cloud.core.oauth.authorize.wechat.filter;

import com.easy.cloud.core.oauth.authorize.base.filter.EcBaseOauthFilter;
import com.easy.cloud.core.oauth.authorize.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.authorize.wechat.token.EcWechatOauthToken;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Component;

/**
 * 微信授权过滤器
 * @author daiqi
 * @create 2018-06-29 17:40
 */
@Component
public class EcOauthWechatFilter extends EcBaseOauthFilter {
    private String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String resourceUrl = "https://api.weixin.qq.com/sns/userinfo";
    private String clientId = "wxcb207fb7c8f9ddf0";
    private String clientSecret = "b36be91c0377797699d73fd2b7fcfa77";
    private String redirectUrl = "http://www.baidu.com";
    @Override
    protected EcBaseOauthToken getOAuth2Token(String code) {
        EcWechatOauthToken oauthToken = new EcWechatOauthToken(code);
        oauthToken.clientId(clientId);
        oauthToken.clientSecret(clientSecret);
        oauthToken.redirectUrl(redirectUrl);
        oauthToken.grantType(GrantType.AUTHORIZATION_CODE);
        oauthToken.tokenRequestBuilder(tokenUrl);
        oauthToken.resourceRequestBuilder(resourceUrl);

        return oauthToken;
    }
}
