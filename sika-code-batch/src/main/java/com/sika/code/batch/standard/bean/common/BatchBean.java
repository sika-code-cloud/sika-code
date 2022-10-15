package com.sika.code.batch.standard.bean.common;

import com.sika.code.batch.standard.bean.processor.StandProcessorBean;
import lombok.Data;

import java.util.*;

/**
 * <p>
 * 批处理的入口Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 15:40
 */
@Data
public class BatchBean {

    private String jobName;
    private String stepName;
    private Integer skipLimit;
    private Integer chunk = 10000;
    // 是否开启多写源异步写、当不为空的时候为异步写、为空为同步写
    private Integer asynWrite;

    private ItemReaderBean<?> itemReaderBean;
    private ItemProcessorBean<?> itemProcessorBean;
    private List<ItemWriterBean<?>> itemWriterBeans;

    private ItemWriterBean<?> currentItemWriterBean;

    private String writerExecutorClassName;

    // listener
    private LinkedHashSet<String> jobExecutionListenerClassNames;
    private LinkedHashSet<String> stepExecutionListenerClassNames;
    // 跳过的异常和无须跳过的异常
    private String skipExceptionClassName;
    private String noSkipExceptionClassName;
    private Set<String> chunkListenerClassNames;
    private Map<String, Object> contextMap;

    public LinkedHashMap<String, String> readerProcessorMapper() {
        if (StandProcessorBean.class.equals(itemProcessorBean.getBeanClass())) {
            StandProcessorBean standProcessorBean = (StandProcessorBean) itemProcessorBean.getBeanObj();
            return standProcessorBean.getReaderProcessorMapper();
        }
        return null;
    }

}
