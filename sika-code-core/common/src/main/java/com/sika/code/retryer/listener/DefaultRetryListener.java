package com.sika.code.retryer.listener;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

/**
 * 默认实现
 * @author daiqi
 * @create 2019-12-20 2:33
 */
public class DefaultRetryListener implements RetryListener {
    @Override
    public <V> void onRetry(Attempt<V> attempt) {

    }
}
