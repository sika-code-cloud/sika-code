package com.sika.code.batch.config;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.dto.StepData;
import com.sika.code.batch.dto.StepListenerData;
import com.sika.code.batch.listener.job.DefaultJobExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-10-06 13:46
 */
@Configuration
@EnableBatchProcessing
@Slf4j
@ConditionalOnClass(value = StepBuilderFactory.class)
public class DefaultBaseBatchConfig extends BaseBatchConfig {


    /**
     * 默认的job执行监听器
     */
    @Bean
    @ConditionalOnBean
    public JobExecutionListener defaultJobExecutionListener() {
        return new DefaultJobExecutionListener();
    }

    /**
     * 默认的job参数校验器
     */
    @Bean
    @ConditionalOnBean
    public JobParametersValidator defaultJobParametersValidator() {
        return new DefaultJobParametersValidator();
    }

    /**
     * <p>
     * 默认的step
     * </p>
     *
     * @return org.springframework.batch.core.Step
     * @author daiqi
     * @date 2019/10/6 23:35
     */
    @Bean
    @JobScope
    public Step defaultStep() {
        JobParametersData jobParametersData = JobParametersBuilderExp.fromData();
        // 初始化构建者
        FaultTolerantStepBuilder builder = initFaultTolerantStepBuilder(jobParametersData);
        StepData stepData = jobParametersData.getStepData();
        if (BaseUtil.isNull(stepData)) {
            return builder.build();
        }
        // 构建监听器
        buildListeners(builder, stepData.getStepListenerData());
        return builder.build();
    }

    /**
     * <p>
     * 默认的Job
     * </p>
     *
     * @return org.springframework.batch.core.Job
     * @author daiqi
     * @date 2019/10/6 23:36
     */
    @Bean
    public Job defaultJob(Step defaultStep, JobExecutionListener jobExecutionListener, JobParametersValidator jobParametersValidator) {
        return jobBuilderFactory.get("defaultJob")
                .incrementer(new RunIdIncrementer())
                .start(defaultStep)
                .listener(jobExecutionListener)
                .validator(jobParametersValidator)
                .build();
    }


}
