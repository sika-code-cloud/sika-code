package com.sika.code.batch.listener.step.base;

import org.springframework.batch.core.jsr.RetryListener;

import javax.batch.api.chunk.listener.RetryProcessListener;

/**
 * @author daiqi
 * @create 2019-10-04 22:40
 */
public interface StepRetryProcessListener extends RetryListener, RetryProcessListener {

}
