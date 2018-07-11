package com.easy.cloud.core.oauth.authorize.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author daiqi
 * @create 2018-06-29 17:45
 */
public class EcOAuth2Token implements AuthenticationToken {
    private String authCode;
    private String principal;

    public EcOAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return authCode;
    }
}
