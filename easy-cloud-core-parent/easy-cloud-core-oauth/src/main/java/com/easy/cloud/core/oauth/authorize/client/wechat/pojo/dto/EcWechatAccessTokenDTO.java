package com.easy.cloud.core.oauth.authorize.client.wechat.pojo.dto;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.oauth.authorize.client.base.pojo.dto.EcBaseAccessTokenDTO;
import org.apache.oltu.oauth2.common.OAuth;

import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-16 15:43
 */
public class EcWechatAccessTokenDTO extends EcBaseAccessTokenDTO{
    public EcWechatAccessTokenDTO() {
    }

    public EcWechatAccessTokenDTO(Map<String, Object> accessTokenParam) {
        super(accessTokenParam);
    }

    @Override
    public String getAccessToken() {
       return EcMapUtils.getString(super.accessTokenParam, OAuth.OAUTH_ACCESS_TOKEN);
    }

    @Override
    public String getTokenType() {
        return EcMapUtils.getString(super.accessTokenParam, OAuth.OAUTH_TOKEN_TYPE);
    }

    @Override
    public Long getExpiresIn() {
        return EcMapUtils.getLong(super.accessTokenParam, OAuth.OAUTH_EXPIRES_IN);
    }

    @Override
    public String getRefreshToken() {
        return EcMapUtils.getString(super.accessTokenParam, OAuth.OAUTH_REFRESH_TOKEN);
    }

    @Override
    public String getScope() {
        return EcMapUtils.getString(super.accessTokenParam, OAuth.OAUTH_SCOPE);
    }

    @Override
    public Long getCustomEffectiveTimeSec() {
        return 6000L;
    }
}
