package com.sika.code.batch.listener.step;

import com.sika.code.batch.listener.step.base.StepRetryWriteListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-10-04 22:38
 */
@Slf4j
public class DefaultRetryWriteListener implements StepRetryWriteListener {

    @Override
    public void onRetryWriteException(List<Object> list, Exception e) throws Exception {

    }
}
