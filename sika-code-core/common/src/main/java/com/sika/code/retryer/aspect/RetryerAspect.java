package com.sika.code.retryer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 重试切面
 *
 * @author daiqi
 * @create 2019-12-06 0:11
 */
public class RetryerAspect {
    @Pointcut("@annotation(com.sika.code.retryer.anotation.RetryerAnnotation)")
    public void retryerAspect() {

    }

    @Around(value = "retryerAspect()")
    public Object annotationAround(ProceedingJoinPoint joinPoint) {
        return doAround(joinPoint);
    }


    public Object doAround(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Class<?>[] parameterTypes = targetMethod.getParameterTypes();
        Object [] ags = joinPoint.getArgs();
        Class<?> returnType = targetMethod.getReturnType();
    }
}
