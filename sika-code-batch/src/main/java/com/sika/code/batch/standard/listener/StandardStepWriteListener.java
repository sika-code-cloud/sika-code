package com.sika.code.batch.standard.listener;

import com.sika.code.batch.core.listener.BaseItemWriteListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class StandardStepWriteListener implements BaseItemWriteListener<Map<String, Object>> {

    @Override
    public void beforeWrite(List<? extends Map<String, Object>> list) {
    }

    @Override
    public void afterWrite(List<? extends Map<String, Object>> list) {
    }

    @Override
    public void onWriteError(Exception e, List<? extends Map<String, Object>> list) {

    }
}
