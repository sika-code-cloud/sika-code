package com.sika.code.batch.config;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.dto.StepCommonData;
import com.sika.code.batch.dto.StepData;
import com.sika.code.batch.dto.StepListenerData;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author daiqi
 * @create 2019-09-12 0:12
 */
public class BaseBatchConfig {
    @Autowired
    protected StepBuilderFactory stepBuilderFactory;
    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    protected FaultTolerantStepBuilder initFaultTolerantStepBuilder(JobParametersData jobParametersData) {
        StepData stepData = jobParametersData.getStepData();
        ItemWriter itemWriter = stepData.getItemWriter();
        ItemReader itemReader = stepData.getItemReader();
        ItemProcessor itemProcessor = stepData.getItemProcessor();

        StepCommonData stepCommonData = stepData.getStepCommonData();
        FaultTolerantStepBuilder<?,?> builder = stepBuilderFactory
                .get(stepCommonData.getName())
                .chunk(stepCommonData.getChunk())
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipLimit(stepCommonData.getSkipLimit())
                .retryLimit(stepCommonData.getRetryLimit());
        // 循环设置跳过的异常
        for (Class<? extends Throwable> clazz : stepCommonData.getSkipExceptions()) {
            builder.skip(clazz);
        }
        // 设置重试的异常
        for (Class<? extends Throwable> clazz : stepCommonData.getRetryExceptions()) {
            builder.retry(clazz);
        }
        return builder;
    }

    /**
     * <p>
     * 构建监听器
     * </p>
     *
     * @param builder
     * @param stepListenerData
     * @return org.springframework.batch.core.step.builder.SimpleStepBuilder
     * @author daiqi
     * @date 2019/10/7 0:40
     */
    protected void buildListeners(FaultTolerantStepBuilder builder, StepListenerData stepListenerData) {
        if (BaseUtil.isNull(stepListenerData)) {
            return;
        }
        if (BaseUtil.isNotNull(stepListenerData.getStepListener())) {
            builder.listener(stepListenerData.getStepListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getChunkListener())) {
            builder.listener(stepListenerData.getChunkListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemReadListener())) {
            builder.listener(stepListenerData.getItemReadListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemProcessListener())) {
            builder.listener(stepListenerData.getItemProcessListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemWriteListener())) {
            builder.listener(stepListenerData.getItemWriteListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getSkipListener())) {
            builder.listener(stepListenerData.getSkipListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getRetryReadListener())) {
            builder.listener(stepListenerData.getRetryReadListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getRetryProcessListener())) {
            builder.listener(stepListenerData.getRetryProcessListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getRetryWriteListener())) {
            builder.listener(stepListenerData.getRetryWriteListener());
        }
    }
}
