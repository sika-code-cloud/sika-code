package com.easy.cloud.core.worker.job.callback.impl;

import com.easy.cloud.core.worker.job.callback.EcJobCallBackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author daiqi
 * @create 2018-06-22 14:38
 */
public class EcDefaultJobCallBackHandler implements EcJobCallBackHandler{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Object execute(String callBackResult) {
        logger.info("异步回调的执行结果为：" + callBackResult);
        return callBackResult;
    }
}
