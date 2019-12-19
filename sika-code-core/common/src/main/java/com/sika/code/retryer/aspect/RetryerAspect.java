package com.sika.code.retryer.aspect;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.retryer.anotation.RetryerAnnotation;
import com.sika.code.retryer.factory.RetryListenerFactory;
import com.sika.code.retryer.factory.StopStrategyFactory;
import com.sika.code.retryer.factory.WaitStrategyFactory;
import com.sika.code.retryer.pojo.StopStrategyParam;
import com.sika.code.retryer.pojo.WaitStrategyParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 重试切面
 *
 * @author daiqi
 * @create 2019-12-06 0:11
 */
@Slf4j
public class RetryerAspect {
    @Pointcut("@annotation(com.sika.code.retryer.anotation.RetryerAnnotation)")
    public void retryerAspect() {

    }

    @Around(value = "retryerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        RetryerAnnotation retryerAnnotation = targetMethod.getAnnotation(RetryerAnnotation.class);
        if (retryerAnnotation == null) {
            Class<?> targetClass = joinPoint.getTarget().getClass();
            retryerAnnotation = targetClass.getAnnotation(RetryerAnnotation.class);
        }
        if (retryerAnnotation == null) {
            return joinPoint.proceed();
        }
        WaitStrategyParam waitStrategyParam = buildWaitStrategyParam(retryerAnnotation);
        StopStrategyParam stopStrategyParam = buildStopStrategyParam(retryerAnnotation);

        RetryerBuilder<Object> retryerBuilder = RetryerBuilder.newBuilder();
        retryerBuilder.withWaitStrategy(WaitStrategyFactory.getWaitStrategy(waitStrategyParam));
        retryerBuilder.withStopStrategy(StopStrategyFactory.getStopStrategy(stopStrategyParam));

        // 循环设置retryIfExceptionOfType
        Class<? extends Throwable>[] retryIfExceptionOfTypes = retryerAnnotation.retryIfExceptionOfTypes();
        if (ArrayUtil.isNotEmpty(retryIfExceptionOfTypes)) {
            for (Class<? extends Throwable> retryIfExceptionOfType : retryIfExceptionOfTypes) {
                retryerBuilder.retryIfExceptionOfType(retryIfExceptionOfType);
            }
        }
        // 循环设置withRetryListener
        Class<? extends RetryListener>[] retryListeners = retryerAnnotation.retryListeners();
        if (ArrayUtil.isNotEmpty(retryListeners)) {
            for (Class<? extends RetryListener> retryListener : retryListeners) {
                retryerBuilder.withRetryListener(RetryListenerFactory.getRetryListener(retryListener));
            }
        }
        Retryer<Object> retryer = retryerBuilder.build();

        //定义请求实现
        Callable<Object> callable = () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new Exception(throwable);
            }
        };
        //利用重试器调用请求
        return retryer.call(callable);
    }

    private StopStrategyParam buildStopStrategyParam(RetryerAnnotation retryerAnnotation) {
        return new StopStrategyParam()
                .setStopStrategyEnum(retryerAnnotation.stopStrategyEnum())
                .setAttemptNumber(retryerAnnotation.attemptNumber())
                .setMaxTime(retryerAnnotation.maxTime())
                .setTimeUnit(retryerAnnotation.timeUnit())
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
}
