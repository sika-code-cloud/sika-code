package com.sika.code.standard.rest.util;

import cn.hutool.core.util.StrUtil;
import com.sika.code.standard.rest.pojo.RestTemplateRequest;
import com.google.common.collect.Maps;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author daiqi
 * @create 2018-12-29 9:50
 */
@Component
public class RestTemplateUtil {
    @Autowired
    private HttpServletRequest httpServletRequest;
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

    /**
     * <p>
     * post请求返回Result对象
     * </p>
     *
     * @param restTemplateRequest
     * @param restTemplate
     * @return Result
     * @author daiqi
     * @date 2018/12/29 9:56
     */
    public static Result postForResult(RestTemplateRequest restTemplateRequest, RestTemplate restTemplate) {
        verifyRestRequest(restTemplateRequest);
        Object requestData = restTemplateRequest.getRequestData();
        if (BaseUtil.isNull(requestData)) {
            requestData = Maps.newHashMap();
        }
        HttpHeaders requestHeaders = restTemplateRequest.getHttpHeaders();
        if (BaseUtil.isNull(requestHeaders)) {
            requestHeaders = new HttpHeaders();
        }
        requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Object> formEntity = new HttpEntity<>(requestData, restTemplateRequest.getHttpHeaders());
        return restTemplate.postForObject(restTemplateRequest.getFullRequestUrl(), formEntity, Result.class);
    }

    /**
     * <p>
     * get请求返回Result对象
     * </p>
     *
     * @param restTemplateRequest
     * @param restTemplate
     * @return Result
     * @author daiqi
     * @date 2018/12/29 9:56
     */
    public static Result getForResult(RestTemplateRequest restTemplateRequest, RestTemplate restTemplate) {
        verifyRestRequest(restTemplateRequest);
        return restTemplate.getForObject(restTemplateRequest.getFullRequestUrl(), Result.class);
    }

    /**
     * <p>
     * 校验请求数据
     * </p>
     *
     * @param restTemplateRequest
     * @return void
     * @author daiqi
     * @date 2018/12/29 10:07
     */
    private static void verifyRestRequest(RestTemplateRequest restTemplateRequest) {
        Assert.verifyObjNull(restTemplateRequest, "请求对象");
        Assert.verifyStrEmpty(restTemplateRequest.getFullRequestUrl(), "完整的请求对象");
    }

}
