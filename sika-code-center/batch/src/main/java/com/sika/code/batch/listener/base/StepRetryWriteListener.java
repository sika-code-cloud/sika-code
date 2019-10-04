package com.sika.code.batch.listener.base;

import org.springframework.batch.core.jsr.RetryListener;

import javax.batch.api.chunk.listener.RetryWriteListener;

/**
 * @author daiqi
 * @create 2019-10-04 23:45
 */
public interface StepRetryWriteListener extends RetryListener, RetryWriteListener {
    
}
