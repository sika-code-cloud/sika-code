package com.sika.code.retryer.constant;

import com.github.rholder.retry.RetryListener;
import com.sika.code.retryer.listener.DefaultRetryListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 重试条件枚举
 *
 * @author daiqi
 * @create 2019-12-27 1:00
 */
@AllArgsConstructor
@Getter
public enum RetryIfConditionEnum {
    /**
     * 重试条件
     */
    DEFAULT(new Class[]{Exception.class}, new Class[]{DefaultRetryListener.class}, null, "默认的重试条件");

    /**
     * 需要重试的异常类型列表
     */
    private Class<? extends Throwable>[] retryIfExceptionOfTypes;

    /**
     * 重试的监听器列表
     */
    private Class<? extends RetryListener>[] retryListenerOfTypes;
    /**
     * 重试的结果列表
     */
    private Object[] retryIfResults;

    private String desc;

}
