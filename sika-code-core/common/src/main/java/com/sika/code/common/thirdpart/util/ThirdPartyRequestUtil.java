package com.sika.code.common.thirdpart.util;

import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.log.util.LogUtil;
import com.sika.code.common.rest.util.RestTemplateUtil;
import com.sika.code.common.string.constant.StringConstant;
import com.sika.code.common.thirdpart.request.ThirdPartyRequestDTO;
import com.sika.code.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 请求第三方的工具类
 *
 * @author daiqi
 * @create 2019-03-14 11:07
 */
@Slf4j
@Component
public class ThirdPartyRequestUtil {

    private static RestTemplateUtil restTemplateUtil;

    @Autowired
    public void setRestTemplateUtil(RestTemplateUtil restTemplateUtil) {
        ThirdPartyRequestUtil.restTemplateUtil = restTemplateUtil;
    }

    /**
     * <p>
     * 构建完整的请求url
     * </p>
     *
     * @param requestDomain 请求域名不包含uri
     * @param uri           请求的接口uri
     * @return java.lang.String
     * @author daiqi
     * @date 2019/3/14 11:08
     */
    public static String buildFullRequestUrl(String requestDomain, String uri) {
        Assert.verifyStrEmpty(requestDomain, "请求的域名不能为空");
        if (!requestDomain.endsWith(StringConstant.Symbol.FORWARD_SLASH)) {
            requestDomain = requestDomain + StringConstant.Symbol.FORWARD_SLASH;
        }
        return requestDomain + uri;
    }

    /**
     * <p>
     * 请求第三方使用json的方式请求
     * </p>
     *
     * @param thirdPartyRequestDTO
     * @param responseClass
     * @return T
     * @author daiqi
     * @date 2018/8/15 18:20
     */
    public static <T> T requestForJson(ThirdPartyRequestDTO thirdPartyRequestDTO, Class<T> responseClass) {
        String requestUrlForFull = thirdPartyRequestDTO.getRequestUrlForFull();
        Object requestData = thirdPartyRequestDTO.getData();

        LogUtil.info("【请求消息】", "请求url：" + requestUrlForFull + "\n请求数据\t" + JSONUtil.toJSONString(requestData), log);

        RestTemplate restTemplate = restTemplateUtil.selectRestTemplate(thirdPartyRequestDTO.getRequestDomain());
        T t = null;
        if (RequestMethod.POST.equals(thirdPartyRequestDTO.getRequestMethod())) {
            t = restTemplate.postForObject(requestUrlForFull, buildJsonEntity(thirdPartyRequestDTO), responseClass);
        } else {
            t = restTemplate.getForObject(requestUrlForFull, responseClass, requestData);
        }

        LogUtil.info("【响应消息】", "请求url：" + requestUrlForFull + "\n响应数据\t" + JSONUtil.toJSONString(t), log);

        return t;
    }


    /**
     * application/x-www-form-urlencoded格式的消息请求
     *
     * @param thirdPartyRequestDTO
     * @param responseClass
     * @param <T>
     * @return
     */
    public static <T> T requestForForm(ThirdPartyRequestDTO thirdPartyRequestDTO, Class<T> responseClass) {
        String requestUrlForFull = thirdPartyRequestDTO.getRequestUrlForFull();
        LogUtil.info("【请求消息】", "请求url：" + requestUrlForFull + "\n请求数据\t" + JSONUtil.toJSONString(thirdPartyRequestDTO.getData()), log);

        RestTemplate restTemplate = restTemplateUtil.selectRestTemplate(thirdPartyRequestDTO.getRequestDomain());
        ResponseEntity<T> t = null;
        if (RequestMethod.POST.equals(thirdPartyRequestDTO.getRequestMethod())) {
            t = restTemplate.postForEntity(requestUrlForFull, buildFormEntity(thirdPartyRequestDTO), responseClass);
        } else {
            t = restTemplate.exchange(requestUrlForFull, HttpMethod.GET, buildFormEntity(thirdPartyRequestDTO), responseClass);
        }

        LogUtil.info("【响应消息】", "请求url：" + requestUrlForFull + "\n响应数据\t" + JSONUtil.toJSONString(t), log);

        return t.getBody();
    }

    /**
     * <p>
     * 构建表单Entity
     * </p>
     *
     * @param thirdPartyRequestDTO
     * @return org.springframework.http.HttpEntity
     * @author daiqi
     * @date 2019/5/15 14:51
     */
    private static HttpEntity buildFormEntity(ThirdPartyRequestDTO thirdPartyRequestDTO) {

        HttpHeaders requestHeaders = buildHeaders(thirdPartyRequestDTO);
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 封装参数,不能使用map或者haspmap，否则参数无法传递
        HashMap<String, String> dataMap = JSONUtil.parseObject(thirdPartyRequestDTO.getData(), HashMap.class);
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        if (CollectionUtil.isNotEmpty(dataMap)) {
            data.setAll(dataMap);
        }
        return new HttpEntity<>(data, requestHeaders);
    }

    /**
     * <p>
     * 构建json的Http实体
     * </p>
     *
     * @param thirdPartyRequestDTO
     * @return org.springframework.http.HttpEntity
     * @author daiqi
     * @date 2019/4/4 16:20
     */
    private static HttpEntity buildJsonEntity(ThirdPartyRequestDTO thirdPartyRequestDTO) {
        HttpHeaders requestHeaders = buildHeaders(thirdPartyRequestDTO);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(thirdPartyRequestDTO.getData(), requestHeaders);
    }

    protected static HttpHeaders buildHeaders(ThirdPartyRequestDTO thirdPartyRequestDTO) {
        HttpHeaders requestHeaders = thirdPartyRequestDTO.getHttpHeaders();
        if (BaseUtil.isNull(requestHeaders)) {
            requestHeaders = new HttpHeaders();
        }
        return requestHeaders;
    }

}
