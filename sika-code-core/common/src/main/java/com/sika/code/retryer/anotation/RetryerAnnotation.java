package com.sika.code.retryer.anotation;

import com.github.rholder.retry.RetryListener;
import com.sika.code.retryer.constant.StopStrategyEnum;
import com.sika.code.retryer.constant.WaitStrategyEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 重试注解
 *
 * @author daiqi
 * @create 2019-12-06 0:03
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RetryerAnnotation {
    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 最大的时长
     */
    long maxTime() default Long.MAX_VALUE;

    StopStrategyEnum stopStrategyEnum() default StopStrategyEnum.STOP_AFTER_ATTEMPT;

    /**
     * 停止重试的次数
     */
    int attemptNumber() default 3;

    WaitStrategyEnum waitStrategyEnum() default WaitStrategyEnum.FIXED;

    /**
     * 初始化的时长
     */
    long initTime() default 0;

    /**
     * 每次递增的时长 --- 对递增枚举类型有效
     */
    long increment() default 0;

    /**
     * 需要重试的异常类型列表
     */
    Class<? extends Throwable> [] retryIfExceptionOfTypes() default Exception.class;

    Class<? extends RetryListener> [] retryListeners() ;
}
