package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.util.CollectionUtil;
import com.sika.code.retryer.constant.RetryIfConditionEnum;
import com.sika.code.retryer.pojo.RetryerBuilderParam;

import java.util.Set;

/**
 * @author daiqi
 * @create 2019-12-21 20:50
 */
public class RetryerFactory {
    /**
     * 使用简单的缓存，自动维护缓存机制
     */
    private static SimpleCache<String, Retryer> RETRYER_BUILDER_CACHE = new SimpleCache<>();

    public static Retryer getRetryer(String retryerName) {
        return get(retryerName);
    }

    public static Retryer getDefaultRetry() {
        RetryIfConditionEnum defaultRetry = RetryIfConditionEnum.DEFAULT;
        Retryer retryer = getRetryer(defaultRetry.getRetryerName().name());
        if (retryer != null) {
            return retryer;
        }
        return put(defaultRetry.getRetryerName().name(), newDefaultRetryer());
    }

    // 该方法无任何缓存 --- 无特殊情况不建议直接使用
    public static Retryer newDefaultRetryer() {
        return newRetryer(new RetryerBuilderParam().build());
    }

    /**
     * <p>
     * 根据retryerBuilderParam参数获取Retryer
     * </p>
     *
     * @param retryerBuilderParam
     * @return com.github.rholder.retry.RetryerBuilder<T>
     * @author daiqi
     * @date 2019/12/21 22:47
     */
    public static Retryer getRetryer(RetryerBuilderParam retryerBuilderParam) {
        String retryerName = retryerBuilderParam.getRetryerName();
        Retryer retryerFromCache = getRetryer(retryerName);
        if (BaseUtil.isNotNull(retryerFromCache)) {
            return retryerFromCache;
        }
        return put(retryerName, newRetryer(retryerBuilderParam));
    }

    private static Retryer newRetryer(RetryerBuilderParam retryerBuilderParam) {
        return buildRetryerBuilder(retryerBuilderParam).build();
    }

    /**
     * <p>
     * 根据构建参数构建重试对象
     * </p>
     *
     * @param retryerBuilderParam
     * @return com.github.rholder.retry.Retryer<java.lang.Object>
     * @author sikadai
     * @date 2019/12/26 23:52
     */
    private static RetryerBuilder buildRetryerBuilder(RetryerBuilderParam retryerBuilderParam) {
        RetryerBuilder retryerBuilder = RetryerBuilder.newBuilder();
        retryerBuilder.withWaitStrategy(WaitStrategyFactory.getWaitStrategy(retryerBuilderParam.getWaitStrategyParam()));
        retryerBuilder.withStopStrategy(StopStrategyFactory.getStopStrategy(retryerBuilderParam.getStopStrategyParam()));
        // 循环设置需要重试的异常
        Set<Class<? extends Throwable>> retryIfExceptionOfTypes = retryerBuilderParam.getRetryIfExceptionOfTypes();
        if (CollectionUtil.isNotEmpty(retryIfExceptionOfTypes)) {
            for (Class<? extends Throwable> retryIfExceptionOfType : retryIfExceptionOfTypes) {
                retryerBuilder.retryIfExceptionOfType(retryIfExceptionOfType);
            }
        }
        // 循环设置withRetryListener
        Set<RetryListener> retryListeners = retryerBuilderParam.getRetryListeners();
        if (CollectionUtil.isNotEmpty(retryListeners)) {
            for (RetryListener retryListener : retryListeners) {
                retryerBuilder.withRetryListener(retryListener);
            }
        }
        return retryerBuilder;
    }

    private static <T> Retryer<T> get(String retryerName) {
        return RETRYER_BUILDER_CACHE.get(retryerName);
    }

    private static <T> Retryer<T> put(String retryerName, Retryer retryer) {
        return RETRYER_BUILDER_CACHE.put(retryerName, retryer);
    }
}
