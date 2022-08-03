package com.sika.code.batch.standard.bean.reader;

import com.google.common.collect.Maps;
import com.sika.code.core.base.pojo.query.PageQuery;
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
    private Integer pageSize;
    private String queryClassName;
    private Map<String, Object> query;

    public Object buildQuery() {
        if (query == null) {
            query = Maps.newLinkedHashMap();
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        query.put("pageSize", pageSize);
        query.put("startIndex", startIndex);
        return BeanUtil.toBean(query, BeanUtil.getTClass(queryClassName));
    }

}
