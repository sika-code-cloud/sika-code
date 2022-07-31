package com.sika.code.batch.standard.builder.writerdata;

import com.sika.code.batch.standard.bean.writer.BaseWriterBean;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 基础写数据构建者
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/19 17:05
 */
@Data
@Accessors(chain = true)
public abstract class BaseWriterDataBuilder<T> {
    protected BaseWriterBean baseWriterBean;
    public abstract List<? extends T> build(List<? extends T> items);
}
