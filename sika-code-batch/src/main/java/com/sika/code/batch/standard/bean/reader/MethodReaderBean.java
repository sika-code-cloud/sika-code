package com.sika.code.batch.standard.bean.reader;

import com.google.common.collect.Maps;
import com.sika.code.batch.standard.constant.BatchConstant;
import com.sika.code.core.util.BeanUtil;
import lombok.Data;

import java.util.Map;

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
public class MethodReaderBean extends BaseReaderBean {
    private String className;
    private String methodName;
    private String indexName;
    private Long startIndex;
    private String queryClassName;
    private Map<String, Object> query;

    public Object buildQuery() {
        if (query == null) {
            query = Maps.newLinkedHashMap();
        }
        if (pageSize == null) {
            pageSize = BatchConstant.PAGE_SIZE_INIT;
        }
        if (startIndex == null) {
            startIndex = BatchConstant.START_INDEX_INIT;
        }
        query.put(BatchConstant.PAGE_SIZE_KEY, pageSize);
        query.put(BatchConstant.START_INDEX_KEY, startIndex);
        return BeanUtil.toBean(query, BeanUtil.getTClass(queryClassName));
    }

}
