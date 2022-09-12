package com.sika.code.batch.standard.listener;

import com.sika.code.batch.core.listener.BaseItemWriteListener;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Setter
public class StandardStepWriteListener implements BaseItemWriteListener<Map<String, Object>> {
    protected Map<String, Object> contextMap;
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
