package com.sika.code.batch.listener.job;

import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 默认的job监听器
 *
 * @author daiqi
 * @create 2019-10-06 13:29
 */
@Slf4j
public class DefaultJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        JobParametersBuilderExp.removeDataDTO();
    }
}
