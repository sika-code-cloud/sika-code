package com.easy.cloud.core.oauth.client.zfb.token;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.easy.cloud.core.basic.factory.EcBeanFactory;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.oauth.client.base.config.EcAccessTokenChannelConfig;
import com.easy.cloud.core.oauth.client.base.constant.EcOauthConstant.EcOauthAccessToken;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO;
import com.easy.cloud.core.oauth.client.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.client.base.request.builder.EcBaseTokenRequestBuilder;
import com.easy.cloud.core.oauth.client.base.response.token.EcBaseOauthResourceResponse;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.client.zfb.pojo.dto.EcZfbAccessTokenDTO;
import com.easy.cloud.core.oauth.client.zfb.request.builder.EcResourceZfbRequestBuilder;
import com.easy.cloud.core.oauth.client.zfb.request.builder.EcTokenZfbRequestBuilder;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-16 9:23
 */
public class EcZfbOauthToken extends EcBaseOauthToken {
    private String grantType;
    private AlipayClient alipayClient;

    public EcZfbOauthToken(String authCode) {
        super(authCode);
        putTokenRequestParam(OAuth.OAUTH_CODE, authCode);
    }


    public EcZfbOauthToken alipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
        return this;
    }

    public EcZfbOauthToken grantType(GrantType grantType) {
        this.grantType = (grantType == null ? null : grantType.toString());
        putTokenRequestParam(OAuth.OAUTH_GRANT_TYPE, grantType);
        return this;
    }

    @Override
    public EcBaseTokenRequestBuilder newTokenRequestBuilder() {
        return new EcTokenZfbRequestBuilder(tokenRequestUrl);
    }

    @Override
    public EcBaseResourceRequestBuilder newResourceRequestBuilder() {
        return new EcResourceZfbRequestBuilder(resourceRequestUrl);
    }

    @Override
    public EcBaseAccessTokenDTO getAccessTokenDTO(Map<String, Object> tokenRequestParam) {
        return new EcZfbAccessTokenDTO(tokenRequestParam);
    }

    @Override
    public Integer getAccessTokenChannel() {
        return EcAccessTokenChannelConfig.ZFB;
    }

    @Override
    public Map<String, Object> doGetOauthAccessToken() throws Exception {
        AlipaySystemOauthTokenRequest oauthTokenRequest = new AlipaySystemOauthTokenRequest();
        oauthTokenRequest.setCode(super.authCode);
        oauthTokenRequest.setGrantType(grantType);

        AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(oauthTokenRequest);
        EcAssert.verifyObjNull(oauthTokenResponse, "oauthTokenResponse");
        if (oauthTokenResponse != null && oauthTokenResponse.isSuccess()) {
            Map<String, Object> tokenRepParam = EcMapUtils.newHashMap();
            tokenRepParam.put(EcOauthAccessToken.ACCESS_TOKEN, oauthTokenResponse.getAccessToken());
            tokenRepParam.put(EcOauthAccessToken.EXPIRES_IN, oauthTokenResponse.getExpiresIn());
            tokenRepParam.put(EcOauthAccessToken.REFRESH_TOKEN, oauthTokenResponse.getRefreshToken());
            tokenRepParam.put(EcOauthAccessToken.AUTH_TOKEN_TYPE, oauthTokenResponse.getAuthTokenType());
            tokenRepParam.put(EcOauthAccessToken.OPENID, oauthTokenResponse.getUserId());
            tokenRepParam.put(EcOauthAccessToken.RE_EXPIRES_IN, oauthTokenResponse.getReExpiresIn());
            return tokenRepParam;
        } else {
            throw new EcBaseBusinessException(oauthTokenResponse.getCode(), oauthTokenResponse.getMsg()).buildExceptionDetail(oauthTokenResponse);
        }
    }

    @Override
    public EcBaseOauthResourceDTO doGetOauthResource(Map<String, Object> oAuthResponseParam) throws Exception {
        AlipayUserInfoShareRequest userInfoShareRequest = new AlipayUserInfoShareRequest();
        String accessToken = EcMapUtils.getString(oAuthResponseParam, EcOauthAccessToken.ACCESS_TOKEN);
        AlipayUserInfoShareResponse shareResponse = alipayClient.execute(userInfoShareRequest, accessToken);
        EcAssert.verifyObjNull(shareResponse, "shareResponse");
        if (shareResponse != null && shareResponse.isSuccess()) {
            EcBaseOauthResourceResponse resourceResponse = EcBeanFactory.newInstance(getResourceRequestBuilder().getResourceResponseClass());
            EcBaseOauthResourceDTO resourceDTO = EcJSONUtils.parseObject(shareResponse, resourceResponse.getResourceDTOClass());
            EcLogUtils.info("授权获取到的用户信息", resourceDTO, logger);
            return resourceDTO;
        } else {
            throw new EcBaseBusinessException(shareResponse.getCode(), shareResponse.getMsg()).buildExceptionDetail(shareResponse);
        }
    }
}
