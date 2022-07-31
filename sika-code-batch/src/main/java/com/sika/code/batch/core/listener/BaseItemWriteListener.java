package com.sika.code.batch.core.listener;


import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public interface BaseItemWriteListener<I> extends ItemWriteListener<I> {

    @Override
    default void beforeWrite(List<? extends I> list) {

    }

    @Override
    default void afterWrite(List<? extends I> list) {
    }

    @Override
    default void onWriteError(Exception e, List<? extends I> list) {

    }
}
