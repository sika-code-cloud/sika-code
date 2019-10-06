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
        ItemWriter itemWriter = jobParametersData.getItemWriter();
        ItemReader itemReader = jobParametersData.getItemReader();
        ItemProcessor itemProcessor = jobParametersData.getItemProcessor();

        StepData stepData = jobParametersData.getStepData();
        StepCommonData stepCommonData = stepData.getStepCommonData();

        return stepBuilderFactory
                .get(stepCommonData.getName())
                .chunk(stepCommonData.getChunk())
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipLimit(stepCommonData.getSkipLimit())
                .skip(stepCommonData.getSkipException())
                .retryLimit(stepCommonData.getRetryLimit())
                .retry(stepCommonData.getRetryException());
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
        if (BaseUtil.isNotNull(stepListenerData.getSkipListener())) {
            builder.listener(stepListenerData.getSkipListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemWriteListener())) {
            builder.listener(stepListenerData.getItemWriteListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemReadListener())) {
            builder.listener(stepListenerData.getItemReadListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getItemProcessListener())) {
            builder.listener(stepListenerData.getItemProcessListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getChunkListener())) {
            builder.listener(stepListenerData.getChunkListener());
        }
        if (BaseUtil.isNotNull(stepListenerData.getStepExecutionListener())) {
            builder.listener(stepListenerData.getStepExecutionListener());
        }
    }
}
