package com.sika.code.common.rest.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author daiqi
 * @create 2018-12-29 9:50
 */
@Component
public class RestTemplateUtil {
    /**
     * Https请求前缀
     */
    private static final String HTTPS_PREFIX = "https:";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RestTemplate restTemplateForHttps;

    /**
     * <p>
     * 根据请求的url选择不同的restTemplate
     * </p>
     *
     * @param requestUrl
     * @return org.springframework.web.client.RestTemplate
     * @author daiqi
     * @date 2019/5/14 14:27
     */
    public RestTemplate selectRestTemplate(String requestUrl) {
        if (StrUtil.isBlank(requestUrl)) {
            return restTemplate;
        }
        if (requestUrl.startsWith(HTTPS_PREFIX)) {
            return restTemplateForHttps;
        } else {
            return restTemplate;
        }
    }

}
