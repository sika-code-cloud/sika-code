package com.sika.code.batch.standard.builder.writerdata;

import com.sika.code.batch.core.factory.BatchFactory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 默认的写数据构建者
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/19 17:05
 */
@Data
@Accessors(chain = true)
public class DefaultWriterDataBuilder extends BaseWriterDataBuilder<Map<String, Object>> {
    @Override
    public List<? extends Map<String, Object>> build(List<? extends Map<String, Object>> items) {
        return BatchFactory.buildWriterItemDatas(items, baseWriterBean.getProcessorWriterMapper());
    }
}
