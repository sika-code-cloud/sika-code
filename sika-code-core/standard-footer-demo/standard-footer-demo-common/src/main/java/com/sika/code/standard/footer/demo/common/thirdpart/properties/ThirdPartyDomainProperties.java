package com.sika.code.standard.footer.demo.common.thirdpart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 第三方域名属性类
 * @author daiqi
 * @create 2019-03-14 10:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "sika.code.thirdparty.url")
public class ThirdPartyDomainProperties {
    /** erp的请求的domain（完整域名/ip+端口+项目名称）、不包括接口 */
    private String erpRequestDomain;
    private String erpRequestDomainHttps;

    /** sjmh的请求domain */
    private String sjmhRequestDomainHttps;

    /** 鹏元征信的请求domain **/
    private String pyzxRequestDomainHttps;

    /** xdbb的请求domain */
    private String xdbbRequestDomainHttps;
}

