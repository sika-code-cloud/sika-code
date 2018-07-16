package com.easy.cloud.core.oauth.authorize.base.token;

import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseTokenRequestBuilder;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-06-29 17:45
 */
public abstract class EcBaseOauthToken implements AuthenticationToken {
    protected String authCode;
    protected String principal;
    protected String tokenRequestUrl;
    protected String resourceRequestUrl;
    protected Map<String, Object> tokenRequestParam = new HashMap<>();
    protected Map<String, Object> resourceRequestParam = new HashMap<>();

    /**
     * token请求构建者
     */
    protected EcBaseTokenRequestBuilder tokenRequestBuilder;
    /**
     * 资源请求构建者
     */
    protected EcBaseResourceRequestBuilder resourceRequestBuilder;

    public EcBaseOauthToken(String authCode) {
        this.authCode = authCode;
    }


    public EcBaseOauthToken code(String code) {
        this.authCode = code;
        return this;
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

    public EcBaseOauthToken tokenRequestBuilder(String tokenRequestUrl) {
        this.tokenRequestUrl = tokenRequestUrl;
        return this;
    }

    public EcBaseOauthToken resourceRequestBuilder(String resourceRequestUrl) {
        this.resourceRequestUrl = resourceRequestUrl;
        return this;
    }

    public Map<String, Object> getTokenRequestParam() {
        return tokenRequestParam;
    }

    public Map<String, Object> getResourceRequestParam() {
        return resourceRequestParam;
    }

    public EcBaseOauthToken putTokenRequestParam(String key, Object value) {
        this.tokenRequestParam.put(key, value);
        return this;
    }

    public EcBaseOauthToken puttResourceRequestParam(String key, Object value) {
        this.resourceRequestParam.put(key, value);
        return this;
    }

    public abstract EcBaseTokenRequestBuilder getTokenRequestBuilder();

    public abstract EcBaseResourceRequestBuilder getResourceRequestBuilder();

    public abstract EcBaseAccessTokenDTO getAccessTokenDTO(Map<String, Object> tokenRequestParam);

    public abstract Integer getAccessTokenChannel();
}
