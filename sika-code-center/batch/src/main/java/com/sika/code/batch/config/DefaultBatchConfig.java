package com.sika.code.batch.config;

import cn.hutool.core.util.RandomUtil;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import com.sika.code.batch.constant.BatchConstant;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.dto.StepData;
import com.sika.code.batch.listener.job.DefaultJobExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
public class DefaultBatchConfig extends BaseBatchConfig {
    /**
     * 默认的job执行监听器
     */
    @Bean
    @ConditionalOnMissingBean
    public JobExecutionListener defaultJobExecutionListener() {
        return new DefaultJobExecutionListener();
    }

    /**
     * 默认的job参数校验器
     */
    @Bean
    @ConditionalOnMissingBean
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

    @Bean(value = BatchConstant.DEFAULT_JOB_NAME)
    public Job defaultJob(Step defaultStep, JobExecutionListener defaultJobExecutionListener, JobParametersValidator defaultJobParametersValidator) {
        return jobBuilderFactory.get(getDefaultJobName())
                .incrementer(new RunIdIncrementer())
                .start(defaultStep)
                .listener(defaultJobExecutionListener)
                .validator(defaultJobParametersValidator)
                .build();
    }

    protected String getDefaultJobName() {
        return BatchConstant.DEFAULT_JOB_NAME;
    }

    @Bean
    public Job taskletJob() {
        return jobBuilderFactory.get("taskletJob")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean
    public Job taskletJob2() {
        return jobBuilderFactory.get("taskletJob2")
                .start(step1())
                .next(new MyDecider()).on("FAILED").to(step2())
                .next(new MyDecider()).on("COMPLETED").to(step3())
                .end()
                .build();
    }

    @Bean
    public Job taskletJob3() {
        return jobBuilderFactory.get("taskletJob3")
                .start(step1()).on("FAILED").to(step2()) //默认跳转到stepB
                .from(step1()).on("FAILED1").to(step3()) //当返回的ExitStatus为"FAILED"时，执行。
                .from(step1()).on("*").to(step4())
                .next(new MyDecider()).on("FAILED").to(step2())
                .next(new MyDecider()).on("COMPLETED").to(step3())//当返回的ExitStatus为"FAILED"时，执行。
                .end()
                .build();
    }

    @StepScope
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Step1())
                .listener(new CheckingListener())
                .build();
    }

    @StepScope
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Step2())
                .build();
    }

    @StepScope
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(new Step3())
                .build();
    }
    @StepScope
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(new Step4())
                .build();
    }

    @Slf4j
    public static class Step1 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("Step1 --------------------");
            return RepeatStatus.FINISHED;
        }


    }

    @Slf4j
    public static class Step2 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("Step2 --------------------");
            return RepeatStatus.FINISHED;
        }
    }

    @Slf4j
    public static class Step3 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("Step3 --------------------");
            return RepeatStatus.FINISHED;
        }
    }
    @Slf4j
    public static class Step4 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("Step4 --------------------");
            return RepeatStatus.FINISHED;
        }
    }

    @Slf4j
    public static class MyDecider implements JobExecutionDecider {
        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            String status;
            int random = RandomUtil.randomInt(10);
            log.info("random: " + random);
            if (random % 2 == 0) {
                status = "FAILED";
            } else {
                status = "COMPLETED";
            }
            return new FlowExecutionStatus(status);
        }
    }

    public class CheckingListener extends StepExecutionListenerSupport {
        @Override
        public ExitStatus afterStep(StepExecution stepExecution) {
            String exitCode = stepExecution.getExitStatus().getExitCode();
            String status;
            int random = RandomUtil.randomInt(10);
            ;
            log.info("random: " + random);
            if (random % 3 == 0) {
                status = "FAILED";
            } else if (random % 3 == 1) {
                status = "FAILED1";
            } else {
                status = "COMPLETED";
            }
            return new ExitStatus(status);
        }
    }
}