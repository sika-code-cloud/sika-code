package com.easy.cloud.core.oauth.authorize.base.response.resource;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
import org.apache.oltu.oauth2.common.token.OAuthToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-16 10:35
 */
public abstract class EcBaseOauthTokenResponse extends OAuthAccessTokenResponse {
    @Override
    public String getAccessToken() {
        return getParam(OAuth.OAUTH_ACCESS_TOKEN);
    }

    @Override
    public String getTokenType() {
        return getParam(OAuth.OAUTH_TOKEN_TYPE);
    }

    @Override
    public Long getExpiresIn() {
        String value = getParam(OAuth.OAUTH_EXPIRES_IN);
        return value == null ? null : Long.valueOf(value);
    }

    @Override
    public String getRefreshToken() {
        return getParam(OAuth.OAUTH_REFRESH_TOKEN);
    }

    @Override
    public String getScope() {
        return getParam(OAuth.OAUTH_SCOPE);
    }

    @Override
    public OAuthToken getOAuthToken() {
        return new BasicOAuthToken(getAccessToken(), getTokenType(), getExpiresIn(), getRefreshToken(), getScope());
    }

    @Override
    protected void setBody(String body) {
        this.body = body;
        parameters = EcJSONUtils.parseObject(this.body, HashMap.class);
    }

    @Override
    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    protected void setResponseCode(int code) {
        this.responseCode = code;
    }

    public Map<String, Object> getParameters() {
        return super.parameters;
    }

    public abstract String getOpenId();

    public abstract String getLang();
}
