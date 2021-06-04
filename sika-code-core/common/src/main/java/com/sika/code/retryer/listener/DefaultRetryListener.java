package com.sika.code.retryer.listener;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认实现 --- 缓存非线程安全
 * @author daiqi
 * @create 2019-12-20 2:33
 */
@Slf4j
public class DefaultRetryListener implements RetryListener {
    @Override
    public <V> void onRetry(Attempt<V> attempt) {

    }
}
