package com.sika.code.retryer.aspect;

import com.github.rholder.retry.Retryer;
import com.sika.code.basic.util.BaseUtil;
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
    public void retryerAspectForClass() {

    }

    @Pointcut("@annotation(com.sika.code.retryer.anotation.RetryerAnnotation)")
    public void retryerAspectForMethod() {

    }

    @Around(value = "retryerAspectForClass() || retryerAspectForMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return doCall(joinPoint);
    }

    /**
     * 执行调用
     */
    private Object doCall(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1: 获取RetryerAnnotation
        RetryerAnnotation retryerAnnotation = getRetryerAnnotation(joinPoint);
        if (retryerAnnotation == null || retryerAnnotation.close()) {
            return joinPoint.proceed();
        }
        Retryer retryer = getRetryer(retryerAnnotation);
        // 4: 创建Callable默认实现对象
        Callable<Object> callable = () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                if (throwable instanceof Exception) {
                    throw (Exception) throwable;
                } else {
                    throw new Exception(throwable);
                }
            }
        };
        // 5: 利用重试器调用请求
        return retryer.call(callable);
    }

    private Retryer getRetryer(RetryerAnnotation retryerAnnotation) {
        Retryer retryer = RetryerFactory.getRetryer(retryerAnnotation.retryerName());
        if (BaseUtil.isNull(retryer)) {
            // 2: 构建重试构建者参数
            RetryerBuilderParam retryerBuilderParam = buildRetryerBuilderParam(retryerAnnotation);
            // 3: 获取Retryer对象
            retryer = RetryerFactory.getRetryer(retryerBuilderParam);
        }
        return retryer;
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
        return new RetryerBuilderParam()
                .setWaitStrategyParam(waitStrategyParam)
                .setStopStrategyParam(stopStrategyParam)
                .buildRetryCondition(retryerAnnotation)
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
