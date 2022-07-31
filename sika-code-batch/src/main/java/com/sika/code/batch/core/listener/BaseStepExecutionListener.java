package com.sika.code.batch.core.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * <p>
 *  基础Step执行器监听器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 10:07
 */
public interface BaseStepExecutionListener extends StepExecutionListener {
    @Override
    default void beforeStep(StepExecution stepExecution) {

    }

    @Override
    default ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
