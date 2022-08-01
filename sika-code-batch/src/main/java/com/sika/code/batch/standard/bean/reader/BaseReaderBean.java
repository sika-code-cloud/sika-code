package com.sika.code.batch.standard.bean.reader;

import lombok.Data;

import java.util.LinkedHashSet;

/**
 * <p>
 * 基础读取器bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 11:18
 */
@Data
public class BaseReaderBean {
    private String builderClassName;
    private LinkedHashSet<String> listenerClassNames;
}
