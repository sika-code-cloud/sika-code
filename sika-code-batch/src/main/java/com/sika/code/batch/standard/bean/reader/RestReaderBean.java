package com.sika.code.batch.standard.bean.reader;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 方法阅读Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:17
 */
@Data
public class RestReaderBean extends BaseReaderBean {
    private String domain;
    private String path;
    private String requestType;
    private String indexName;
    private Integer pageSize;
    private Long startIndex;
    private Map<String, Object> query;
    private String fullUrl;
    private String codeName;
    private Set<String> successCodes;
    private String msgName;
    private String dataName;

    public String buildFullUrl() {
        if (this.fullUrl == null) {
            this.fullUrl = StrUtil.join(StrPool.SLASH, domain, path);
        }
        return this.fullUrl;
    }

    public Map<String, Object> buildQuery() {
        if (query == null) {
            query = Maps.newLinkedHashMap();
        }
        query.put("pageSize", pageSize);
        query.put("startIndex", startIndex);
        return query;
    }

    public String buildRequestType() {
        if (StrUtil.isBlank(this.requestType)) {
            this.requestType = "POST";
        }
        return requestType;
    }
}
