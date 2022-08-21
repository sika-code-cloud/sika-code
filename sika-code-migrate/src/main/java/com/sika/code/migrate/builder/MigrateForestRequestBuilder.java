package com.sika.code.migrate.builder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 18:46
 */
@Data
@AllArgsConstructor
@Slf4j
public class MigrateForestRequestBuilder {
    private MigrateRuleRequest ruleRequest;
    private MigrateRuleResponse ruleResponse;

    public ForestRequest<?> build() {
        ForestRequest<?> forestRequest = forestRequest();
        buildRequestHeader(forestRequest);
        buildRequestQuery(forestRequest);
        buildRequestBody(forestRequest);

        return forestRequest;
    }

    private ForestRequest<?> forestRequest() {
        String method = ruleRequest.getRequest().getMethod();
        ForestRequest<?> forestRequest = null;
        if (StrUtil.equalsIgnoreCase(Method.POST.name(), method)) {
            forestRequest = Forest.post(ruleResponse.getRedirectUrl());
        } else if (StrUtil.equalsIgnoreCase(Method.GET.name(), method)) {
            forestRequest = Forest.get(ruleResponse.getRedirectUrl());
        } else {
            throw new RuntimeException(StrUtil.format("不支持的请求方式[{}]", method));
        }
        return forestRequest;
    }

    private void buildRequestHeader(ForestRequest<?> forestRequest) {
        // 先构建自己的额外参数
        for (Map.Entry<String, String> entry : ruleResponse.getExtraParam().entrySet()) {
            forestRequest.addHeader(entry.getKey(), entry.getValue());
        }
        // 再构建请求头的-此时若请求名一致-则会执行覆盖操作
        Map<String, List<String>> requestHeader = ruleRequest.getRequestHeadParam();
        for (Map.Entry<String, List<String>> entry : requestHeader.entrySet()) {
            forestRequest.addHeader(entry.getKey(), CollUtil.getFirst(entry.getValue()));;
        }
    }

    private void buildRequestQuery(ForestRequest<?> forestRequest) {
        Map<String, List<String>> requestQueryParam = ruleRequest.getRequestQueryParam();
        for (Map.Entry<String, List<String>> entry : requestQueryParam.entrySet()) {
            forestRequest.addQuery(entry.getKey(), entry.getValue());
        }
    }

    private void buildRequestBody(ForestRequest<?> forestRequest) {
        InputStream inputStream = null;
        try {
            inputStream = ruleRequest.getRequest().getInputStream();
            forestRequest.addBody(StreamUtils.copyToByteArray(inputStream));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
