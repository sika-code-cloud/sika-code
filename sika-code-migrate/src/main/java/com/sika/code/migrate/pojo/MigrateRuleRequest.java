package com.sika.code.migrate.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  迁移数据传输对象
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 8:42
 */
@Data
@Accessors(chain = true)
public class MigrateRuleRequest {
    /**
     * 当前请求的request
     */
    @JsonIgnore
    private HttpServletRequest request;
    /**
     * 当前请求的response
     */
    @JsonIgnore
    private HttpServletResponse response;
    /**
     * 请求完整路径
     */
    private String requestFullPath;
    /**
     * 请求的Uri - 不包含域名和端口号
     */
    private String requestUri;
    /**
     * 请求的参数-请求体参数
     */
    private Map<String, Object> requestBody;
    /**
     * 请求的参数-请求查询参数-值有可能是数组
     */
    private Map<String, List<String>> requestQueryParam;
    /**
     * 请求头的参数key+value
     */
    private Map<String, List<String>> requestHeadParam;
    /**
     * 其他额外的参数
     */
    private Map<String, Object> extraParam;
}
