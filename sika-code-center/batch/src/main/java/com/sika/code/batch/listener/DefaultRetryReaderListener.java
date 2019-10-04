package com.sika.code.batch.listener;

import com.sika.code.batch.listener.base.StepRetryReadListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daiqi
 * @create 2019-10-04 22:38
 */
@Slf4j
public class DefaultRetryReaderListener implements StepRetryReadListener {

    @Override
    public void onRetryReadException(Exception e) throws Exception {

    }
}
