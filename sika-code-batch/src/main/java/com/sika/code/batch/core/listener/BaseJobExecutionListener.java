package com.sika.code.batch.core.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * <p>
 * 基础Job执行器监听器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 10:07
 */
public interface BaseJobExecutionListener extends JobExecutionListener {
    @Override
    default void beforeJob(JobExecution jobExecution) {

    }

    @Override
    default void afterJob(JobExecution jobExecution) {

    }
}
