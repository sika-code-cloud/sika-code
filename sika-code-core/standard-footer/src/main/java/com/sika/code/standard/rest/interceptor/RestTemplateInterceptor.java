package com.sika.code.standard.rest.interceptor;

import com.sika.code.common.util.CollectionUtil;
import com.sika.code.standard.request.manager.HttpRequestManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 使用restTemplate请求过滤器
 *
 * @author daiqi
 * @create 2019-01-08 17:01
 */
@Component
@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        try {
            HttpHeaders headers = request.getHeaders();
            HttpHeaders httpHeadersFromLocal = HttpRequestManager.getHttpHeadersFromLocal();
            if (CollectionUtil.isNotEmpty(httpHeadersFromLocal)) {
                // 添加原请求的header
                headers.putAll(httpHeadersFromLocal);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        // 保证请求继续被执行
        return execution.execute(request, body);
    }
}
