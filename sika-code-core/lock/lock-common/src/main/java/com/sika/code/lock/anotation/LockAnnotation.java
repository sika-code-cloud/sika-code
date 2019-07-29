package com.sika.code.lock.anotation;

import com.sika.code.lock.constant.LockType;
import com.sika.code.lock.constant.LockTryType;

import java.util.concurrent.TimeUnit;

/**
 * 锁注解
 *
 * @author daiqi
 * @create 2019-07-29 11:02
 */
public @interface LockAnnotation {
    int TIME_DEFAULT = 10;

    /**
     * 获取锁类型
     */
    LockType lockType() default LockType.UN_FAIR;

    /**
     * 获取锁的尝试类型
     */
    LockTryType lockTryType() default LockTryType.UN_TRY;

    /**
     * 获取时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 最多等待时间
     */
    int waitTime() default TIME_DEFAULT;

    /**
     * 上锁后自动释放锁时间
     */
    int leaseTime() default TIME_DEFAULT;
}
