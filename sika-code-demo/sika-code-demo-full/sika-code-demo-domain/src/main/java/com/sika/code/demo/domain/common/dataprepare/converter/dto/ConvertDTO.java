package com.sika.code.demo.domain.common.dataprepare.converter.dto;

import lombok.Data;

/**
 * <p>
 *  转化数据传输对象
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 10:46
 */
@Data
public class ConvertDTO<IN, OUT> {
    private Integer inDataType;
    private Integer outDataType;

    /** 输入的为字符串时候需要配置分隔符 */
    private String inSeparator;
    /** 输出的为字符串时候需要配置分隔符 */
    private String outSeparator;

    private Class<IN> inClass;
    private Class<OUT> outClass;
}
