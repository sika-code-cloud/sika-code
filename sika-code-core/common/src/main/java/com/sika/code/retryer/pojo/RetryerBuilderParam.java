package com.sika.code.retryer.pojo;

import com.github.rholder.retry.RetryListener;
import com.google.common.collect.Sets;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.util.CollectionUtil;
import com.sika.code.retryer.anotation.RetryerAnnotation;
import com.sika.code.retryer.constant.RetryerNameEnum;
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
    /**
     * 用于缓存retryer对象的名称
     */
    private String retryerName;
    /**
     * 停止策略参数
     */
    private StopStrategyParam stopStrategyParam;
    /**
     * 等待策略参数
     */
    private WaitStrategyParam waitStrategyParam;
    /**
     * 重试异常类型列表
     */
    private Set<Class<? extends Throwable>> retryIfExceptionOfTypes = Sets.newLinkedHashSet();
    /**
     * 重试监听器对象列表 --- 当发生重试时会触发
     */
    private Set<RetryListener> retryListeners = Sets.newLinkedHashSet();
    /**
     * 重试监听器类型列表 --- 根据类型自动构建重试对象
     */
    private Set<Class<? extends RetryListener>> retryListenerOfTypes = Sets.newLinkedHashSet();

    public RetryerBuilderParam build() {
        if (BaseUtil.isNull(this.stopStrategyParam)) {
            this.stopStrategyParam = new StopStrategyParam().build();
        }
        if (BaseUtil.isNull(this.waitStrategyParam)) {
            this.waitStrategyParam = new WaitStrategyParam().build();
        }
        if (StringUtil.isBlank(retryerName)) {
            this.retryerName = RetryerNameEnum.DEFAULT.name();
        }
        if (CollectionUtil.isEmpty(retryIfExceptionOfTypes)) {
            addRetryIfExceptionOfTypes(Exception.class);
        }
        // 构建监听器
        buildRetryListeners();
        return this;
    }

    /**
     * 构建重试监听器列表
     */
    private void buildRetryListeners() {
        // 优先对监听器class进行复制
        if (CollectionUtil.isEmpty(this.retryListenerOfTypes)) {
            addRetryListenerOfTypes(DefaultRetryListener.class);
        }
        for (Class<? extends RetryListener> clazz : this.retryListenerOfTypes) {
            addRetryListener(RetryListenerFactory.getRetryListener(clazz));
        }
    }

    public RetryerBuilderParam addRetryIfExceptionOfTypes(Class<? extends Throwable> retryIfExceptionOfType) {
        this.retryIfExceptionOfTypes.add(retryIfExceptionOfType);
        return this;
    }

    public RetryerBuilderParam addRetryListenerOfTypes(Class<? extends RetryListener> retryListenerOfTypes) {
        this.retryListenerOfTypes.add(retryListenerOfTypes);
        return this;
    }

    public RetryerBuilderParam addRetryListener(RetryListener retryListener) {
        this.retryListeners.add(retryListener);
        return this;
    }

    public RetryerBuilderParam buildRetryCondition(RetryerAnnotation retryerAnnotation) {
        this.retryerName = retryerAnnotation.retryerName();
        // 循环设置retryIfExceptionOfType
        Class<? extends Throwable>[] retryIfExceptionOfTypes = retryerAnnotation.retryIfExceptionOfTypes();
        if (ArrayUtil.isNotEmpty(retryIfExceptionOfTypes)) {
            for (Class<? extends Throwable> retryIfExceptionOfType : retryIfExceptionOfTypes) {
                addRetryIfExceptionOfTypes(retryIfExceptionOfType);
            }
        }
        // 循环设置withRetryListener
        Class<? extends RetryListener>[] retryListenerOfTypes = retryerAnnotation.retryListenerOfTypes();
        if (ArrayUtil.isNotEmpty(retryListenerOfTypes)) {
            for (Class<? extends RetryListener> retryListenerOfType : retryListenerOfTypes) {
                addRetryListenerOfTypes(retryListenerOfType);
            }
        }
        return this;
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
