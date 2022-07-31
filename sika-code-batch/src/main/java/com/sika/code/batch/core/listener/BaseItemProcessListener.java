package com.sika.code.batch.core.listener;

import org.springframework.batch.core.ItemProcessListener;

public interface BaseItemProcessListener<T, S> extends ItemProcessListener<T, S> {
    @Override
    default void beforeProcess(T t) {

    }

    @Override
    default void afterProcess(T t, S s) {

    }

    @Override
    default void onProcessError(T t, Exception e) {

    }
}
