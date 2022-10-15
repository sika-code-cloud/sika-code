package com.sika.code.batch.standard.bean.reader;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.batch.standard.constant.BatchConstant;
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
        if (startIndex == null) {
            this.startIndex = BatchConstant.START_INDEX_INIT;
        }
        if (pageSize == null) {
            this.pageSize = BatchConstant.PAGE_SIZE_INIT;
        }
        query.put(BatchConstant.PAGE_SIZE_KEY, pageSize);
        query.put(BatchConstant.START_INDEX_KEY, startIndex);
        return query;
    }

    public String buildRequestType() {
        if (StrUtil.isBlank(this.requestType)) {
            this.requestType = "POST";
        }
        return requestType;
    }
}
