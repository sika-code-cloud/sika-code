package com.sika.code.batch.standard.bean.writer;

import lombok.Data;

/**
 * <p>
 *  平面文件写的Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:28
 */
@Data
public class FlatWriterBean extends BaseWriterBean {
    private String subBuilderClassName;
    private String source;
    // 每个文件的数量
    private Integer perSourceNum;
    private String delimiter;
}
