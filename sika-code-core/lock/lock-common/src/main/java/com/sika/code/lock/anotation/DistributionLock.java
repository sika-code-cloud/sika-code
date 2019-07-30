package com.sika.code.lock.anotation;

import com.sika.code.common.string.util.StringUtil;
import com.sika.code.lock.constant.LockType;
import com.sika.code.lock.constant.LockTryType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁注解
 *
 * @author daiqi
 * @create 2019-07-29 11:02
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributionLock {
    int TIME_DEFAULT = 3;

    /**
     * 模块名称
     */
    String module();

    /**
     * 指定的属性名称 --- 默认使用index对应的参数作为key
     */
    String fieldName() default StringUtil.EMPTY;

    /**
     * 作为key的参数索引 从0开始
     */
    int index() default 0;

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
     * 最多等待时间  try锁有效
     */
    int waitTime() default TIME_DEFAULT;

    /**
     * 上锁后自动释放锁时间
     */
    int leaseTime() default TIME_DEFAULT;
}
