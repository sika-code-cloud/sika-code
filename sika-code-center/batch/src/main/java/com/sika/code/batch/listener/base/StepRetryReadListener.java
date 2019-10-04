package com.sika.code.batch.listener.base;

import org.springframework.batch.core.jsr.RetryListener;

import javax.batch.api.chunk.listener.RetryReadListener;

/**
 * @author daiqi
 * @create 2019-10-04 23:45
 */
public interface StepRetryReadListener extends RetryListener, RetryReadListener {

}
