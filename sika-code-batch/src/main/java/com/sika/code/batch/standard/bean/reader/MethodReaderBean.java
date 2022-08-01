package com.sika.code.batch.standard.bean.reader;

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
public class MethodReaderBean<Query extends PageQuery<Long>> extends BaseReaderBean {
    private String className;
    private String methodName;
    private String indexName;
    private String queryClassName;
    private Map<String, Object> queryParam;
    private Query query;

    public Query buildQuery() {
        if (this.query == null) {
            this.query = BeanUtil.toBean(queryParam, BeanUtil.getTClass(queryClassName));
        }
        return this.query;
    }

}
