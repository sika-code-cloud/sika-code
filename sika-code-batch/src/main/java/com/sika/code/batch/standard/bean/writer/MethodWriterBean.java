package com.sika.code.batch.standard.bean.writer;

import lombok.Data;

/**
 * <p>
 * 方法写入的Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:28
 */
@Data
public class MethodWriterBean extends BaseWriterBean {
    private String className;
    private String methodName;
    private String paramsClassName;
}
