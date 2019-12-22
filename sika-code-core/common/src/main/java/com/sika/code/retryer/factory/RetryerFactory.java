package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.sika.code.common.util.CollectionUtil;
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
    private static SimpleCache<String, RetryerBuilder> RETRYER_BUILDER_CACHE = new SimpleCache<>();

    /**
     * <p>
     * 根据retryerBuilderParam参数获取Retryer
     * </p>
     * @author daiqi
     * @date 2019/12/21 22:47
     * @param retryerBuilderParam
     * @return com.github.rholder.retry.RetryerBuilder<T>
     */
    public static <T> Retryer<T> getRetryer(RetryerBuilderParam retryerBuilderParam) {
        RetryerBuilder<T> retryerBuilder = RetryerBuilder.newBuilder();
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
        return retryerBuilder.build();
    }

    private static <T> RetryerBuilder<T> get(RetryerBuilderParam retryerBuilderParam) {
        return RETRYER_BUILDER_CACHE.get(retryerBuilderParam.toString());
    }

    private static <T> RetryerBuilder<T> put(RetryerBuilderParam retryerBuilderParam, RetryerBuilder retryerBuilder) {
        return RETRYER_BUILDER_CACHE.put(retryerBuilderParam.toString(), retryerBuilder);
    }
}
