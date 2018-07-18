package com.easy.cloud.core.oauth.authorize.client.base.token;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.oauth.authorize.client.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.client.base.pojo.dto.EcBaseOauthResourceDTO;
import com.easy.cloud.core.oauth.authorize.client.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.authorize.client.base.request.builder.EcBaseTokenRequestBuilder;
import com.easy.cloud.core.oauth.authorize.client.base.response.resource.EcBaseOauthTokenResponse;
import com.easy.cloud.core.oauth.authorize.client.base.response.token.EcBaseOauthResourceResponse;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-06-29 17:45
 */
public abstract class EcBaseOauthToken implements AuthenticationToken {
    private static OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    protected String authCode;
    protected String principal;
    protected String tokenRequestUrl;
    protected String resourceRequestUrl;
    protected Map<String, Object> tokenRequestParam = new HashMap<>();
    protected Map<String, Object> resourceRequestParam = new HashMap<>();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

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
        this.tokenRequestBuilder = newTokenRequestBuilder();
        return this;
    }

    public EcBaseOauthToken resourceRequestBuilder(String resourceRequestUrl) {
        this.resourceRequestUrl = resourceRequestUrl;
        this.resourceRequestBuilder = newResourceRequestBuilder();
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

    public EcBaseTokenRequestBuilder getTokenRequestBuilder() {
        return tokenRequestBuilder;
    }

    public EcBaseResourceRequestBuilder getResourceRequestBuilder() {
        return resourceRequestBuilder;
    }

    public abstract EcBaseTokenRequestBuilder newTokenRequestBuilder();

    public abstract EcBaseResourceRequestBuilder newResourceRequestBuilder();

    public abstract EcBaseAccessTokenDTO getAccessTokenDTO(Map<String, Object> tokenRequestParam);

    public abstract Integer getAccessTokenChannel();

    /**
     * <p>
     * 执行获取授权accessToken
     * </p>
     * <pre>
     *     子类可以通过重写该方法实现自己的获取授权accessToken的数据
     * </pre>
     *
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author daiqi
     * @date 2018/7/18 11:54
     */
    public Map<String, Object> doGetOauthAccessToken() throws Exception {
        // 获取token请求构建者
        EcBaseTokenRequestBuilder tokenRequestBuilder = getTokenRequestBuilder();
        // 构建客户端请求数据
        OAuthClientRequest accessTokenRequest = tokenRequestBuilder.buildClientRequest(getTokenRequestParam());
        // 返回accessToken响应对象
        EcBaseOauthTokenResponse response = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST, tokenRequestBuilder.getTokenResponseClass());
        // 将accessToken放入map中
        return response.getParameters();
    }

    /**
     * <p>
     * 执行获取授权资源信息
     * </p>
     * <pre>
     *     子类可以通过重写该方法实现自己的获取授权资源数据传输对象的数据
     * </pre>
     *
     * @param oAuthResponseParam
     * @return com.easy.cloud.core.oauth.authorize.client.base.pojo.dto.EcBaseOauthResourceDTO
     * @author daiqi
     * @date 2018/7/18 11:54
     */
    public EcBaseOauthResourceDTO doGetOauthResource(Map<String, Object> oAuthResponseParam) throws Exception {
        // 获取资源请求构建者
        EcBaseResourceRequestBuilder resourceRequestBuilder = getResourceRequestBuilder();
        // 构建客户端请求参数
        OAuthClientRequest resourceRequest = resourceRequestBuilder.buildClientRequest(oAuthResponseParam);
        // 执行请求资源
        EcBaseOauthResourceResponse resourceResponse = oAuthClient.resource(resourceRequest, OAuth.HttpMethod.GET, resourceRequestBuilder.getResourceResponseClass());
        // 从资源响应对象中获取资源数据传输对象
        EcBaseOauthResourceDTO resourceDTO = resourceResponse.getResourceObj(resourceResponse.getResourceDTOClass());
        EcLogUtils.info("授权获取到的用户信息", resourceDTO, logger);
        return resourceDTO;
    }
}
