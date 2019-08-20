package com.sika.code.lock.aspect;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.common.string.constant.StringConstant;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.util.ReflectionUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.lock.anotation.DistributionLock;
import com.sika.code.lock.constant.LockConstant;
import com.sika.code.lock.constant.LockTryType;
import com.sika.code.lock.constant.LockType;
import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.pojo.result.LockResult;
import com.sika.code.lock.properties.DistributionLockProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
@Slf4j
public class LockAspect {

    protected DistributionLockHandler lockHandler;
    protected DistributionLockProperties distributionLockProperties;

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

            // 获取分布式锁注解
            DistributionLock lock = targetMethod.getAnnotation(DistributionLock.class);
            Object keyValue = getKeyValue(lock, joinPoint);
            lockResult = doLock(lock, keyValue);
            return joinPoint.proceed();
        } finally {
            if (BaseUtil.isNotNull(lockResult) && BaseUtil.isNotNull(lockResult.getLock())) {
                lockHandler.unlock(lockResult.getLock());
            }
        }
    }

    /**
     * <p>
     * 获取属性名称对应的keyValue
     * </p>
     *
     * @param lock
     * @param joinPoint
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/7/30 17:45
     */
    public Object getKeyValue(DistributionLock lock, ProceedingJoinPoint joinPoint) {
        int index = lock.index();
        Object[] args = joinPoint.getArgs();
        // 校验参数
        verifyArgs(index, args);
        // 分解属性名称
        String fieldName = lock.fieldName();
        Object keyValue = args[index];
        if (StringUtil.isNotBlank(fieldName) && BaseUtil.isNotNull(keyValue)) {
            String[] fieldNames = StringUtil.split(fieldName, StringConstant.Symbol.STOP);
            for (String fieldNameItem : fieldNames) {
                keyValue = ReflectionUtil.getFieldValue(keyValue, fieldNameItem);
                if (BaseUtil.isNull(keyValue)) {
                    break;
                }
            }
        }
        Assert.verifyObjNull(keyValue, "keyValue");
        return keyValue;
    }

    /**
     * <p>
     * 校验参数
     * </p>
     *
     * @param index
     * @param args
     * @return void
     * @author daiqi
     * @date 2019/7/30 17:46
     */
    public void verifyArgs(int index, Object[] args) {
        if (ArrayUtil.isEmpty(args)) {
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, "target method args is null");
        }
        if (index < 0) {
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR).buildFormatValues("index:" + index);
        }
        int length = args.length;
        if (index >= length) {
            String errorMsg = "index:" + index + "大于等于 args.length:" + length;
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, errorMsg);
        }
    }

    /**
     * <p>
     * 执行加锁业务逻辑
     * </p>
     *
     * @param lock
     * @param keyValue
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/30 17:46
     */
    public LockResult doLock(DistributionLock lock, Object keyValue) {
        if (BaseUtil.isNull(lock) || BaseUtil.isNull(keyValue)) {
            return null;
        }
        LockType lockType = lock.lockType();
        switch (lockType) {
            case FAIR:
                return fairLock(lock, keyValue);
            case MULTI_LOCK:
                return multiLock(lock, keyValue);
            default:
                return lock(lock, keyValue);
        }
    }

    /**
     * <p>
     * 公平锁
     * </p>
     *
     * @param lock
     * @param keyValue
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/31 9:42
     */
    protected LockResult fairLock(DistributionLock lock, Object keyValue) {
        String fullKey = buildFullKey(lock.modules(), keyValue);
        int waitTime = buildWaitTime(lock.waitTime());
        int leaseTime = buildWaitTime(lock.leaseTime());
        if (LockTryType.TRY.equals(lock.lockTryType())) {
            return lockHandler.tryFairLock(fullKey, waitTime, leaseTime, lock.timeUnit());
        } else {
            return lockHandler.fairLock(fullKey, leaseTime, lock.timeUnit());
        }
    }

    /**
     * <p>
     * 非公平锁
     * </p>
     *
     * @param lock
     * @param keyValue
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/31 9:42
     */
    protected LockResult lock(DistributionLock lock, Object keyValue) {
        String fullKey = buildFullKey(lock.modules(), keyValue);
        int waitTime = buildWaitTime(lock.waitTime());
        int leaseTime = buildWaitTime(lock.leaseTime());
        if (LockTryType.TRY.equals(lock.lockTryType())) {
            return lockHandler.tryLock(fullKey, waitTime, leaseTime, lock.timeUnit());
        } else {
            return lockHandler.lock(fullKey, leaseTime, lock.timeUnit());
        }
    }

    /**
     * <p>
     * 级联锁
     * </p>
     *
     * @param lock
     * @param keyValue
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/31 9:42
     */
    protected LockResult multiLock(DistributionLock lock, Object keyValue) {
        Collection keyValues = null;
        if (keyValue instanceof Collection) {
            keyValues = (Collection) keyValue;
        }
        if (CollUtil.isEmpty(keyValues)) {
            return null;
        }
        List<String> keys = Lists.newArrayList();
        for (Object key : keyValues) {
            keys.add(buildFullKey(lock.modules(), key));
        }
        int waitTime = buildWaitTime(lock.waitTime());
        int leaseTime = buildWaitTime(lock.leaseTime());
        if (LockTryType.TRY.equals(lock.lockTryType())) {
            return lockHandler.tryLock(keys, waitTime, leaseTime, lock.timeUnit());
        } else {
            return lockHandler.lock(keys, leaseTime, lock.timeUnit());
        }
    }

    protected int buildWaitTime(final int waitTimeAnnotation) {
        int waitTime = waitTimeAnnotation;
        if (LockConstant.TIME_DEFAULT == waitTime) {
            waitTime = distributionLockProperties.getWaitTime();
        }
        return waitTime;
    }

    protected int buildLeaseTime(int leaseTimeAnnotation) {
        int leaseTime = leaseTimeAnnotation;
        if (LockConstant.TIME_DEFAULT == leaseTime) {
            leaseTime = distributionLockProperties.getLeaseTime();
        }
        return leaseTime;
    }

    /**
     * <p>
     * 构建完整的Key
     * </p>
     *
     * @param modules : 模块名称列表
     * @param key     : key
     * @return java.lang.String
     * @author daiqi
     * @date 2019/7/30 15:54
     */
    private String buildFullKey(String[] modules, Object key) {
        String prefix = distributionLockProperties.getPrefix();
        List<String> fullKeyItems = Lists.newArrayList();
        fullKeyItems.add(prefix);
        fullKeyItems.addAll(Arrays.asList(modules));
        StringBuilder stringBuilder = StringUtil.newStringBuilder();
        fullKeyItems.stream()
                .filter(item -> StringUtil.isNotBlank(item))
                .forEach(item -> stringBuilder.append(item).append(StringConstant.Symbol.COLON));
        stringBuilder.append(key);
        return stringBuilder.toString();
    }

}
