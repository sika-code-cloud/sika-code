package com.sika.code.batch.standard.listener;

import com.sika.code.batch.core.listener.BaseItemWriteListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class StandardStepWriteListener implements BaseItemWriteListener<Map<String, Object>> {
    private final AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public void beforeWrite(List<? extends Map<String, Object>> list) {
        log.info("执行写之前----");
    }

    @Override
    public void afterWrite(List<? extends Map<String, Object>> list) {
        log.info("总共写入的条数为：{}", atomicLong.addAndGet(list.size()));
    }

    @Override
    public void onWriteError(Exception e, List<? extends Map<String, Object>> list) {

    }
}
