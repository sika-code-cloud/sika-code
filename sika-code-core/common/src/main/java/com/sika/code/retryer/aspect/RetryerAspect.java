package com.sika.code.retryer.aspect;

import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.google.common.collect.Sets;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.retryer.anotation.RetryerAnnotation;
import com.sika.code.retryer.factory.RetryerFactory;
import com.sika.code.retryer.pojo.RetryerBuilderParam;
import com.sika.code.retryer.pojo.StopStrategyParam;
import com.sika.code.retryer.pojo.WaitStrategyParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 重试切面
 *
 * @author daiqi
 * @create 2019-12-06 0:11
 */
@Slf4j
@Component
@Aspect
public class RetryerAspect {
    @Pointcut("@within(com.sika.code.retryer.anotation.RetryerAnnotation)")
    public void retryerAspect() {

    }

    @Around(value = "retryerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return doCall(joinPoint);
    }

    /**
     * 执行调用
     */
    public Object doCall(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1: 获取RetryerAnnotation
        RetryerAnnotation retryerAnnotation = getRetryerAnnotation(joinPoint);
        if (retryerAnnotation == null) {
            return joinPoint.proceed();
        }
        // 2: 构建重试构建者参数
        RetryerBuilderParam retryerBuilderParam = buildRetryerBuilderParam(retryerAnnotation);
        // 3: 获取Retryer对象
        Retryer<Object> retryer = RetryerFactory.getRetryer(retryerBuilderParam);
        // 4: 创建Callable默认实现对象
        Callable<Object> callable = () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new Exception(throwable);
            }
        };
        // 5: 利用重试器调用请求
        return retryer.call(callable);
    }

    /**
     * 从ProceedingJoinPoint中获取重试注解
     */
    private RetryerAnnotation getRetryerAnnotation(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        RetryerAnnotation retryerAnnotation = targetMethod.getAnnotation(RetryerAnnotation.class);
        if (retryerAnnotation == null) {
            Class<?> targetClass = joinPoint.getTarget().getClass();
            retryerAnnotation = targetClass.getAnnotation(RetryerAnnotation.class);
        }
        return retryerAnnotation;

    }

    /**
     * 根据注解构建重试构建参数
     */
    private RetryerBuilderParam buildRetryerBuilderParam(RetryerAnnotation retryerAnnotation) {
        WaitStrategyParam waitStrategyParam = buildWaitStrategyParam(retryerAnnotation);
        StopStrategyParam stopStrategyParam = buildStopStrategyParam(retryerAnnotation);

        // 循环设置retryIfExceptionOfType
        Set<Class<? extends Throwable>> retryIfExceptionOfTypeSets = Sets.newLinkedHashSet();
        Class<? extends Throwable>[] retryIfExceptionOfTypes = retryerAnnotation.retryIfExceptionOfTypes();
        if (ArrayUtil.isNotEmpty(retryIfExceptionOfTypes)) {
            for (Class<? extends Throwable> retryIfExceptionOfType : retryIfExceptionOfTypes) {
                retryIfExceptionOfTypeSets.add(retryIfExceptionOfType);
            }
        }
        // 循环设置withRetryListener
        Set<Class<? extends RetryListener>> retryListenersTypes = Sets.newLinkedHashSet();
        Class<? extends RetryListener>[] retryListenerOfTypes = retryerAnnotation.retryListenerOfTypes();
        if (ArrayUtil.isNotEmpty(retryListenerOfTypes)) {
            for (Class<? extends RetryListener> retryListenerOfType : retryListenerOfTypes) {
                retryListenersTypes.add(retryListenerOfType);
            }
        }
        return new RetryerBuilderParam()
                .setWaitStrategyParam(waitStrategyParam)
                .setStopStrategyParam(stopStrategyParam)
                .setRetryIfExceptionOfTypes(retryIfExceptionOfTypeSets)
                .setRetryListenerOfTypes(retryListenersTypes)
                .build();
    }

    private WaitStrategyParam buildWaitStrategyParam(RetryerAnnotation retryerAnnotation) {
        return new WaitStrategyParam()
                .setWaitStrategyEnum(retryerAnnotation.waitStrategyEnum())
                .setInitTime(retryerAnnotation.initTime())
                .setIncrement(retryerAnnotation.increment())
                .setMaxTime(retryerAnnotation.maxTime())
                .setTimeUnit(retryerAnnotation.timeUnit())
                .build();
    }
    
    private StopStrategyParam buildStopStrategyParam(RetryerAnnotation retryerAnnotation) {
        return new StopStrategyParam()
                .setStopStrategyEnum(retryerAnnotation.stopStrategyEnum())
                .setAttemptNumber(retryerAnnotation.attemptNumber())
                .setMaxTime(retryerAnnotation.maxTime())
                .setTimeUnit(retryerAnnotation.timeUnit())
                .build();
    }

}
