package com.sika.code.batch.standard.bean.reader;

import com.sika.code.core.base.pojo.query.PageQuery;
import lombok.Data;

import java.io.Serializable;

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
public class MethodReaderBean<Primary extends Serializable> extends BaseReaderBean {
    private String className;
    private String methodName;
    private String indexName;
    private PageQuery<Primary> query;
}
