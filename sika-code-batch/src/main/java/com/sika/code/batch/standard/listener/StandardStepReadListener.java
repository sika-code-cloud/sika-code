package com.sika.code.batch.standard.listener;

import com.sika.code.batch.core.listener.BaseItemReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
public class StandardStepReadListener implements BaseItemReadListener<Map<String, Object>> {
    @Override
    public void beforeRead() {
//        log.info("开始执行读------");
    }

    @Override
    public void afterRead(Map<String, Object> t) {
    }

    @Override
    public void onReadError(Exception e) {

    }
}
