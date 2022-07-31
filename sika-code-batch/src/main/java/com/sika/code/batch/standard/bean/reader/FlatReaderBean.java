package com.sika.code.batch.standard.bean.reader;

import lombok.Data;

import java.util.LinkedHashSet;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:17
 */
@Data
public class FlatReaderBean extends BaseReaderBean {
    private LinkedHashSet<String> names;
    private String source;
    private String delimiter;
    private int linesToSkip;
}
