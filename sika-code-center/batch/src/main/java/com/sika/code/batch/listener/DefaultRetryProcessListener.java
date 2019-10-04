package com.sika.code.batch.listener;

import com.sika.code.batch.listener.base.StepRetryProcessListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daiqi
 * @create 2019-10-04 22
 */
@Slf4j
public class DefaultRetryProcessListener implements StepRetryProcessListener {

    @Override
    public void onRetryProcessException(Object o, Exception e) throws Exception {

    }
}
