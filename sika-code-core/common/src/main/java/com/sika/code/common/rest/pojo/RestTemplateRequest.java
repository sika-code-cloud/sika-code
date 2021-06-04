package com.sika.code.common.rest.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

/**
 * restTemplate请求类
 *
 * @author daiqi
 * @create 2018-12-29 9:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestTemplateRequest<RequestData> {
    private HttpHeaders httpHeaders;
    private RequestData requestData;
    private String requestPath;
    private String requestUri;
    private String fullRequestUrl;

    public String getFullRequestUrl() {
        if (this.fullRequestUrl == null) {
            if (StrUtil.isNotBlank(requestUri)) {
                this.fullRequestUrl = requestPath + "/" + requestUri;
            } else {
                this.fullRequestUrl = requestPath;
            }
        }
        return fullRequestUrl;
    }

    public RestTemplateRequest addHeader(String headName, String headValue) {
        if (this.httpHeaders == null) {
            this.httpHeaders = new HttpHeaders();
        }
        this.httpHeaders.add(headName, headValue);
        return this;
    }
}
