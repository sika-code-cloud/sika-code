package com.easy.cloud.core.task.job.callback;

/**
 * @author daiqi
 * @create 2018-06-22 13:54
 */
public interface EcJobCallBackHandler {
    Object execute(String callBackResult);
}
