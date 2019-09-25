package com.sika.code.gateway.interceptor;

import com.sika.code.common.rest.interceptor.RestTemplateInterceptor;
import com.sika.code.common.thirdpart.constant.ThirdPartyConstant;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import com.sika.code.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author daiqi
 * @create 2019-08-27 22:48
 */
@Slf4j
public class GatewayRestInterceptor extends RestTemplateInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            HttpHeaders headers = request.getHeaders();
            HttpHeaders httpHeadersFromLocal = (HttpHeaders) ThreadLocalManager.getThreadLocalAndInheritable(ThirdPartyConstant.HTTP_HEADER_NAME);
            if (CollectionUtil.isNotEmpty(httpHeadersFromLocal)) {
                for (Map.Entry<String, List<String>> entry : httpHeadersFromLocal.entrySet()) {
                    String key = entry.getKey();
                    if (headers.containsKey(key)) {
                        continue;
                    }
                    headers.put(key, entry.getValue());
                }
            }
            System.out.println();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return super.intercept(request, body, execution);
    }
}
