package com.sika.code.batch.standard.bean.reader;

import com.sika.code.batch.standard.constant.BatchConstant;
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
    protected Integer pageSize = BatchConstant.PAGE_SIZE_INIT;
}
