package com.sika.code.batch.standard.bean.writer;

import cn.hutool.core.util.StrUtil;
import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import com.sika.code.batch.standard.builder.writerdata.DefaultWriterDataBuilder;
import com.sika.code.core.util.BeanUtil;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * <p>
 * 基础写Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 11:18
 */
@Data
public class BaseWriterBean {
    private String builderClassName;
    private LinkedHashSet<String> listenerClassNames;
    private LinkedHashMap<String, String> processorWriterMapper;
    private String writerDataBuilderClassName;

    private BaseWriterDataBuilder<Map<String, Object>> baseWriterDataBuilder;

    private boolean build = false;

    protected void buildBaseWriterDataBuilder() {
        if (baseWriterDataBuilder != null) {
            return;
        }
        if (StrUtil.isBlank(writerDataBuilderClassName)) {
            this.writerDataBuilderClassName = DefaultWriterDataBuilder.class.getName();
        }
        this.baseWriterDataBuilder = ((BaseWriterDataBuilder<Map<String, Object>>) BeanUtil.newInstance(writerDataBuilderClassName)).setBaseWriterBean(this);
    }

    public final void build() {
        if (build) {
            return;
        }
        doBuild();
        this.build = true;
    }

    protected void doBuild() {
        buildBaseWriterDataBuilder();
    }
}
