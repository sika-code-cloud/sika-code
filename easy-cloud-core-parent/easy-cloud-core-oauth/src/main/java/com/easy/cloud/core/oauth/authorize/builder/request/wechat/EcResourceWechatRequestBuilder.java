package com.easy.cloud.core.oauth.authorize.builder.request.wechat;

import com.easy.cloud.core.oauth.authorize.builder.request.EcBaseResourceRequestBuilder;

/**
 * @author daiqi
 * @create 2018-07-02 17:11
 */
public class EcResourceWechatRequestBuilder extends EcBaseResourceRequestBuilder {

    public EcResourceWechatRequestBuilder(String url) {
        super(url);
    }

    @Override
    public EcBaseResourceRequestBuilder setOpenId(String openId) {
        this.parameters.put("openid", openId);
        return this;
    }

    @Override
    public EcBaseResourceRequestBuilder setLang(String lang) {
        this.parameters.put("lang", lang);
        return this;
    }
}
