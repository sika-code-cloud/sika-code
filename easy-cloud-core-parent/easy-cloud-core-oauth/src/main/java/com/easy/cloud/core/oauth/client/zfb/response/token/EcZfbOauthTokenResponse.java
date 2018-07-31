package com.easy.cloud.core.oauth.client.zfb.response.token;

import com.easy.cloud.core.oauth.client.base.response.resource.EcBaseOauthTokenResponse;

/**
 * @author daiqi
 * @create 2018-07-16 10:48
 */
public class EcZfbOauthTokenResponse extends EcBaseOauthTokenResponse {

    @Override
    public String getOpenId() {
        return getParam("openid");
    }

    @Override
    public String getLang() {
        return "zh_CN";
    }
}
