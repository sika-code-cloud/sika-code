package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.google.common.base.Predicates;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.util.CollectionUtil;
import com.sika.code.retryer.constant.RetryerNameEnum;
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
    private static SimpleCache<RetryerNameEnum, Retryer> RETRYER_BUILDER_CACHE = new SimpleCache<>();

    public static Retryer<Object> getRetryer(RetryerNameEnum retryerName) {
        return get(retryerName);
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
    public static Retryer<Object> getRetryer(RetryerBuilderParam retryerBuilderParam) {
        RetryerNameEnum retryerName = retryerBuilderParam.getRetryerName();
        Retryer<Object> retryerFromCache = getRetryer(retryerName);
        if (BaseUtil.isNotNull(retryerFromCache)) {
            return retryerFromCache;
        }
        return put(retryerName, buildRetryer(retryerBuilderParam));
    }

    private static Retryer<Object> buildRetryer(RetryerBuilderParam retryerBuilderParam) {
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
    private static RetryerBuilder<Object> buildRetryerBuilder(RetryerBuilderParam retryerBuilderParam) {
        RetryerBuilder<Object> retryerBuilder = RetryerBuilder.newBuilder();
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
        // 循环设置返回的结果
        Set<Object> retryIfResults = retryerBuilderParam.getRetryIfResults();
        if (CollectionUtil.isEmpty(retryIfResults)) {
            for (Object retryIfResult : retryIfResults) {
                retryerBuilder.retryIfResult(Predicates.equalTo(retryIfResult));
            }
        }
        return retryerBuilder;
    }

    private static <T> Retryer<T> get(RetryerNameEnum retryerName) {
        return RETRYER_BUILDER_CACHE.get(retryerName);
    }

    private static <T> Retryer<T> put(RetryerNameEnum retryerName, Retryer retryer) {
        return RETRYER_BUILDER_CACHE.put(retryerName, retryer);
    }
}
