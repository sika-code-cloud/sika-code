package com.sika.code.migrate.builder;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  迁移构建者
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 16:44
 */
@AllArgsConstructor
@Slf4j
public class MigrateRequestRuleBuilder {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /**
     * <p>
     * 构建迁移规则请求对象
     * </p>
     *
     * @return com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleRequest
     * @author sikadai
     * @since 2022/8/20 16:45
     */
    public MigrateRuleRequest build() {
        MigrateRuleRequest ruleRequest = new MigrateRuleRequest();
        ruleRequest.setRequest(request)
                .setResponse(response)
                .setRequestHeadParam(readRequestHeaderParamToMap())
                .setRequestQueryParam(readRequestQueryParamToMap())
                .setRequestUri(request.getRequestURI())
                .setRequestFullPath(request.getRequestURL().toString())
                .setRequestBody(readRequestBodyToMap())
        ;
        cacheToMigrateRuleRequestToRequest(ruleRequest);
        return ruleRequest;
    }

    protected void cacheToMigrateRuleRequestToRequest(MigrateRuleRequest ruleRequest) {
        request.setAttribute("MigrateRuleRequest", ruleRequest);
    }

    /**
     * <p>
     * 读取请求体到map
     * </p>
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author sikadai
     * @since 2022/8/20 17:19
     */
    private Map<String, Object> readRequestBodyToMap() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
            return JSON.parseObject(stringBuilder.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * 读取请求头参数到map
     * </p>
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author sikadai
     * @since 2022/8/20 17:47
     */
    private Map<String, List<String>> readRequestQueryParamToMap() {
        Map<String, String[]> parameterMap = request.getParameterMap();

        Map<String, List<String>> retMap = Maps.newLinkedHashMap();
        for (Map.Entry<String, String[]> requestParams : parameterMap.entrySet()) {
            retMap.put(requestParams.getKey(), Lists.newArrayList(requestParams.getValue()));
        }
        return retMap;
    }

    /**
     * <p>
     * 读取请求头参数到map
     * </p>
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author sikadai
     * @since 2022/8/20 17:47
     */
    private Map<String, List<String>> readRequestHeaderParamToMap() {
        Map<String, List<String>> retMap = Maps.newLinkedHashMap();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            retMap.put(headerName, headerValues);
        }
        return retMap;
    }
}
