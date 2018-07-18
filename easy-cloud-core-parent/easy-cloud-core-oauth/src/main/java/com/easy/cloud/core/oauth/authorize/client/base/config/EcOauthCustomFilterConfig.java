package com.easy.cloud.core.oauth.authorize.client.base.config;

import com.easy.cloud.core.authority.config.EcBaseAuthorityCustomFilterConfig;
import com.easy.cloud.core.oauth.authorize.client.wechat.filter.EcOauthWechatFilter;
import com.easy.cloud.core.oauth.authorize.client.zfb.filter.EcOauthZfbFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2018-07-18 9:17
 */
@Configuration
public class EcOauthCustomFilterConfig extends EcBaseAuthorityCustomFilterConfig{
    @Autowired
    private EcOauthWechatFilter oauthWechatFilter;
    @Autowired
    private EcOauthZfbFilter oauthZfbFilter;
    @Override
    protected synchronized void loadOtherFilters() {
        super.putCustomFilter("oauthWechatFilter", oauthWechatFilter);
        super.putCustomFilter("oauthZfbFilter", oauthZfbFilter);
    }

}
