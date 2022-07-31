package com.sika.code.batch.standard.item.processor;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.batch.core.item.processor.BaseProcessor;
import com.sika.code.batch.standard.bean.common.ItemProcessorBean;
import com.sika.code.batch.standard.bean.processor.StandProcessorBean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 13:43
 */
public class StandardProcessor extends BaseProcessor<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> process(Map<String, Object> item) {
        ItemProcessorBean<?> processorItemBean = batchBean.getItemProcessorBean();
        StandProcessorBean standProcessorBean = (StandProcessorBean) processorItemBean.buildBeanObj();
        Map<String, String> inoutMapper = standProcessorBean.getReaderProcessorMapper();
        Map<String, Object> retMap = new LinkedHashMap<>();
        if (CollUtil.isEmpty(inoutMapper)) {
            retMap.putAll(item);
            return retMap;
        }
        for (Map.Entry<String, String> inOutMapper : inoutMapper.entrySet()) {
            Object value = item.get(inOutMapper.getKey());
            if (value == null) {
                continue;
            }
            retMap.put(inOutMapper.getValue(), value);
        }
        return retMap;
    }
}
