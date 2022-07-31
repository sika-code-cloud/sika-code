package com.sika.code.batch.standard.listener;

import com.sika.code.batch.core.listener.BaseItemProcessListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class StandardStepProcessListener implements BaseItemProcessListener<Map<String, Object>, Map<String, Object>> {
    @Override
    public void beforeProcess(Map<String, Object> t) {
//        log.info("执行处理之前----");
    }

    @Override
    public void afterProcess(Map<String, Object> t, Map<String, Object> s) {
//        log.info("执行处理之后----");
    }

    @Override
    public void onProcessError(Map<String, Object> t, Exception e) {

    }
}
