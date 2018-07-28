package com.easy.cloud.core.oauth.client.base.config;

import com.easy.cloud.core.authority.config.EcBaseAuthorityCustomFilterConfig;
import com.easy.cloud.core.oauth.client.wechat.filter.EcOauthWechatFilter;
import com.easy.cloud.core.oauth.client.zfb.filter.EcOauthZfbFilter;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2018-07-18 9:17
 */
@Configuration
public class EcOauthCustomFilterConfig extends EcBaseAuthorityCustomFilterConfig {
    @Override
    protected synchronized void loadOtherFilters() {
        super.putCustomFilter("oauthWechatFilter", new EcOauthWechatFilter());
        super.putCustomFilter("oauthZfbFilter", new EcOauthZfbFilter());
    }

}
