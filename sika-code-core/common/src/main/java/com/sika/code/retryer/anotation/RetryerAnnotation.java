package com.sika.code.retryer.anotation;

import com.github.rholder.retry.RetryListener;
import com.sika.code.retryer.constant.RetryIfConditionEnum;
import com.sika.code.retryer.constant.RetryerNameEnum;
import com.sika.code.retryer.constant.StopStrategyEnum;
import com.sika.code.retryer.constant.WaitStrategyEnum;
import com.sika.code.retryer.listener.DefaultRetryListener;

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
     * Retryer对象的名称枚举
     */
    RetryerNameEnum retryerName() default RetryerNameEnum.DEFAULT;

    /**
     * 重试条件的枚举
     */
    RetryIfConditionEnum retryIfCondition() default RetryIfConditionEnum.DEFAULT;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

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
    long initTime() default 3;

    /**
     * 每次递增的时长 --- 对递增枚举类型有效
     */
    long increment() default 0;
}
