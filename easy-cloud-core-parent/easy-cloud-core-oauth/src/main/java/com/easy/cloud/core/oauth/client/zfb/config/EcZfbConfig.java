package com.easy.cloud.core.oauth.client.zfb.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2018-07-18 15:39
 */
@Configuration
public class EcZfbConfig {
    @Value(value = "${ec.oauth.zfb.gateway.url}")
    private String gatewayUrl;
    @Value(value = "${ec.oauth.zfb.appid}")
    private String appId;
    @Value(value = "${ec.oauth.zfb.payPubKey}")
    private String zfbPayPubKey;
    @Value(value = "${ec.oauth.zfb.applicationPrivateKey}")
    private String zfbApplicationPrivateKey;

    @Bean(name = "defaultAliPayClient")
    public AlipayClient defaultAliPayClient() {
        return new DefaultAlipayClient(gatewayUrl, appId, zfbApplicationPrivateKey, "JSON", "utf-8", zfbPayPubKey, "RSA2");
    }

}
