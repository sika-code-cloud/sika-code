package com.sika.code.lock.aspect;

import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.lock.anotation.DistributionLock;
import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.pojo.result.LockResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 锁的切面
 *
 * @author daiqi
 * @create 2019-07-30 10:38
 */
@Aspect
@Order
@Data
@AllArgsConstructor
public class LockAspect {

    protected DistributionLockHandler distributionLockHandler;

    @Pointcut("@annotation(com.sika.code.lock.anotation.DistributionLock)")
    public void lockAspect() {

    }

    @Around(value = "lockAspect()")
    public Object annotationAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return doAround(joinPoint);
    }


    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LockResult lockResult = null;
        try {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            Class<?> targetClass = joinPoint.getTarget().getClass();
            DistributionLock distributionLock = targetMethod.getAnnotation(DistributionLock.class);
            String fieldName = distributionLock.fieldName();
            lockResult = distributionLockHandler.lock("");
            return joinPoint.proceed();
        } finally {
            distributionLockHandler.unlock(lockResult.getLock());
        }
    }

    @AfterThrowing(value = "lockAspect()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        throw new RuntimeException(ex);
    }
}
