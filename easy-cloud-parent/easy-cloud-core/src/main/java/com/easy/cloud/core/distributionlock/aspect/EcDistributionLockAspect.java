package com.easy.cloud.core.distributionlock.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.distributionlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributionlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributionlock.template.EcDistributedLockTemplate;

/**
 * 
 * <p>
 * 分布式锁切面类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年4月12日 下午1:51:49
 */
@Aspect
@Component
public class EcDistributionLockAspect {

    @Autowired
    private EcDistributedLockTemplate lockTemplate;

    @Pointcut("@annotation(com.easy.cloud.core.distributionlock.annotation.EcDistributedLock)")
    public void distributedLockAspect() {}

    @Around(value = "distributedLockAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        //切点所在的类
        Class targetClass = pjp.getTarget().getClass();
        //使用了注解的方法
        String methodName = pjp.getSignature().getName();

        Class[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();

        Method method = targetClass.getMethod(methodName, parameterTypes);

        Object[] arguments = pjp.getArgs();

        final String lockName = getLockName(method, arguments);

        return lock(pjp, method, lockName);
    }

    @AfterThrowing(value = "distributedLockAspect()", throwing="ex")
    public void afterThrowing(Throwable ex) {
        throw new RuntimeException(ex);
    }

    public String getLockName(Method method, Object[] args) {
        Objects.requireNonNull(method);
        EcDistributedLock annotation = method.getAnnotation(EcDistributedLock.class);

        String lockName = annotation.lockName(),
                param = annotation.param();

        if (EcStringUtils.isEmpty(lockName)) {
            if (args.length > 0) {
                if (EcStringUtils.isNotEmpty(param)) {
                    Object arg;
                    if (annotation.argNum() > 0) {
                        arg = args[annotation.argNum() - 1];
                    } else {
                        arg = args[0];
                    }
                    lockName = String.valueOf(getParam(arg, param));
                } else if (annotation.argNum() > 0) {
                    lockName = args[annotation.argNum() - 1].toString();
                }
            }
        }

        if (EcStringUtils.isNotEmpty(lockName)) {
            String preLockName = annotation.lockNamePre(),
                    postLockName = annotation.lockNameSuffix(),
                    separator = annotation.separator();

            StringBuilder lName = new StringBuilder();
            if (EcStringUtils.isNotEmpty(preLockName)) {
                lName.append(preLockName).append(separator);
            }
            lName.append(lockName);
            if (EcStringUtils.isNotEmpty(postLockName)) {
                lName.append(separator).append(postLockName);
            }

            lockName = lName.toString();

            return lockName;
        }

        throw new IllegalArgumentException("Can't get or generate lockName accurately!");
    }

    /**
     * 从方法参数获取数据
     *
     * @param param
     * @param arg 方法的参数数组
     * @return
     */
    public Object getParam(Object arg, String param) {
        if (EcStringUtils.isNotEmpty(param) && arg != null) {
        	Map<String, Object> argMap = EcJSONUtils.parseObject(arg, HashMap.class) ;
            try {
                Object result = EcMapUtils.getObject(argMap, param);
                return result;
            } catch (Exception e) {
                throw new RuntimeException("", e);
            }
        }
        return null;
    }

    public Object lock(final ProceedingJoinPoint pjp, Method method, final String lockName) {

        EcDistributedLock annotation = method.getAnnotation(EcDistributedLock.class);

        boolean fairLock = annotation.fairLock();

        boolean tryLock = annotation.tryLock();

        if (tryLock) {
            return tryLock(pjp, annotation, lockName, fairLock);
        } else {
            return lock(pjp,lockName, fairLock);
        }
    }

    public Object lock(final ProceedingJoinPoint pjp, final String lockName, boolean fairLock) {
        return lockTemplate.lock(new EcDistributedLockCallback<Object>() {
            @Override
            public Object process() {
                return proceed(pjp);
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        }, fairLock);
    }

    public Object tryLock(final ProceedingJoinPoint pjp, EcDistributedLock annotation, final String lockName, boolean fairLock) {

        long waitTime = annotation.waitTime(),
                leaseTime = annotation.leaseTime();
        TimeUnit timeUnit = annotation.timeUnit();

        return lockTemplate.tryLock(new EcDistributedLockCallback<Object>() {
            @Override
            public Object process() {
                return proceed(pjp);
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        }, waitTime, leaseTime, timeUnit, fairLock);
    }

    public Object proceed(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

}
