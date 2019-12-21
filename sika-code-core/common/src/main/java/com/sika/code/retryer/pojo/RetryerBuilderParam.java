package com.sika.code.retryer.pojo;

import com.github.rholder.retry.RetryListener;
import com.google.common.collect.Sets;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.util.CollectionUtil;
import com.sika.code.retryer.factory.RetryListenerFactory;
import com.sika.code.retryer.listener.DefaultRetryListener;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 重试构建者参数
 *
 * @author daiqi
 * @create 2019-12-21 20:58
 */
@Data
@Accessors(chain = true)
public class RetryerBuilderParam {
    private StopStrategyParam stopStrategyParam;
    private WaitStrategyParam waitStrategyParam;
    private Set<Class<? extends Throwable>> retryIfExceptionOfTypes;
    private Set<RetryListener> retryListeners;
    private Set<Class<? extends RetryListener>> retryListenerOfTypes;

    public RetryerBuilderParam build() {
        if (BaseUtil.isNull(this.stopStrategyParam)) {
            this.stopStrategyParam = new StopStrategyParam().build();
        }
        if (BaseUtil.isNull(this.waitStrategyParam)) {
            this.waitStrategyParam = new WaitStrategyParam().build();
        }
        if (CollectionUtil.isEmpty(this.retryIfExceptionOfTypes)) {
            this.retryIfExceptionOfTypes = Sets.newLinkedHashSet();
            addRetryIfExceptionOfType(Exception.class);
        }
        // 构建监听器
        buildRetryListeners();
        return this;
    }

    /**
     * 构建重试监听器列表
     */
    public void buildRetryListeners() {
        // 优先对监听器class进行复制
        if (CollectionUtil.isEmpty(this.retryListenerOfTypes)) {
            this.retryListenerOfTypes = Sets.newLinkedHashSet();
            addRetryListenerClass(DefaultRetryListener.class);
        }
        if (CollectionUtil.isEmpty(this.retryListeners)) {
            this.retryListeners = Sets.newLinkedHashSet();
        }
        for (Class<? extends RetryListener> clazz : this.retryListenerOfTypes) {
            addRetryListener(RetryListenerFactory.getRetryListener(clazz));
        }
    }

    public void addRetryIfExceptionOfType(Class<? extends Throwable> retryIfExceptionOfType) {
        this.retryIfExceptionOfTypes.add(retryIfExceptionOfType);
    }

    public void addRetryListenerClass(Class<? extends RetryListener> retryListenerClass) {
        this.retryListenerOfTypes.add(retryListenerClass);
    }

    public void addRetryListener(RetryListener retryListener) {
        this.retryListeners.add(retryListener);
    }

    @Override
    public String toString() {
        return "RetryerBuilderParam{" +
                "stopStrategyParam=" + stopStrategyParam.toString() +
                ", waitStrategyParam=" + waitStrategyParam.toString() +
                ", retryIfExceptionOfTypes=" + retryIfExceptionOfTypes.toString() +
                ", retryListeners=" + retryListeners.toString() +
                ", retryListenerOfTypes=" + retryListenerOfTypes.toString() +
                '}';
    }
}
