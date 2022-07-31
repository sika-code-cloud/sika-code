package com.sika.code.batch.core.factory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sika.code.batch.core.builder.BaseItemProcessorBuilder;
import com.sika.code.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.core.item.writer.BaseWriterExecutor;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.reader.BaseReaderBean;
import com.sika.code.batch.standard.bean.writer.BaseWriterBean;
import com.sika.code.batch.standard.item.writer.StandardWriterExecutor;
import com.sika.code.core.util.BeanUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 批处理工厂类
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 9:30
 */
public class BatchFactory {
    public static <O> BaseWriterExecutor<O> getWriterExecutor(BatchBean batchBean) {
        String writerExecutorClassName = batchBean.getWriterExecutorClassName();
        if (StrUtil.isBlank(writerExecutorClassName)) {
            writerExecutorClassName = StandardWriterExecutor.class.getName();
        }
        return BeanUtil.newInstance(writerExecutorClassName);
    }

    public static ItemReader getItemReader(BatchBean batchBean, String itemReaderBuilderClass) {
        Class<? extends BaseItemReaderBuilder> itemReaderBuilderTypeClass = getClassByClassName(itemReaderBuilderClass);
        return BeanUtil.newInstance(itemReaderBuilderTypeClass).build(batchBean);
    }

    public static ItemProcessor getItemProcessor(BatchBean batchBean, String itemProcessorBuilderClassName) {
        Class<? extends BaseItemProcessorBuilder> itemProcessorBuilderClass = getClassByClassName(itemProcessorBuilderClassName);
        return BeanUtil.newInstance(itemProcessorBuilderClass).build(batchBean);
    }

    public static ItemWriter getItemWriter(BatchBean batchBean, String itemWriterBuilderClassName) {
        Class<? extends BaseItemWriterBuilder> itemProcessorBuilderClassName = getClassByClassName(itemWriterBuilderClassName);
        return BeanUtil.newInstance(itemProcessorBuilderClassName).build(batchBean);
    }

    public static Map<String, String> buildProcessorWriterMapper(Map<String, String> readerProcessorMapper, Map<String, String> processorWriterMapper) {
        if (CollUtil.isEmpty(processorWriterMapper)) {
            return readerProcessorMapper;
        }
        return processorWriterMapper;
    }

    public static List<? extends Map<String, Object>> buildWriterItemDatas(List<? extends Map<String, Object>> items, Map<String, String> processorWriterMapper) {
        if (CollUtil.isEmpty(processorWriterMapper)) {
            return items;
        }
        List<Map<String, Object>> retItems = Lists.newArrayList();
        for (Map<String, Object> item : items) {
            Map<String, Object> retItem = Maps.newLinkedHashMap();
            for (Map.Entry<String, Object> itemEntry : item.entrySet()) {
                String writerKey = processorWriterMapper.get(itemEntry.getKey());
                if (StrUtil.isNotBlank(writerKey)) {
                    retItem.put(writerKey, itemEntry.getValue());
                }
            }
            retItems.add(retItem);
        }
        return retItems;
    }

    public static DataSource getDataSource(BaseReaderBean batchBean) {
        return BeanUtil.getBean(DataSource.class);
    }

    public static DataSource getDataSource(BaseWriterBean batchBean) {
        return BeanUtil.getBean(DataSource.class);
    }

    public static <T> Class<T> getClassByClassName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
