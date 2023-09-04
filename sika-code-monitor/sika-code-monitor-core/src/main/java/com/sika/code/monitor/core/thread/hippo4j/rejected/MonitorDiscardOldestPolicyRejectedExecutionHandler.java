package com.sika.code.monitor.core.thread.hippo4j.rejected;

import cn.hippo4j.common.executor.support.CustomRejectedExecutionHandler;
import com.sika.code.monitor.core.thread.hippo4j.manager.Hippo4jThreadPoolMetricsManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class MonitorDiscardOldestPolicyRejectedExecutionHandler implements CustomRejectedExecutionHandler {


    @Override
    public Integer getType() {
        return 15;
    }

    @Override
    public RejectedExecutionHandler generateRejected() {
        return new CustomMonitorCallerRunsPolicy();
    }

    @Override
    public String getName() {
        return "monitorDiscardOldestPolicy";
    }

    public static class CustomMonitorCallerRunsPolicy extends ThreadPoolExecutor.DiscardOldestPolicy {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            Hippo4jThreadPoolMetricsManager.monitorRejected(r, e, MonitorDiscardOldestPolicyRejectedExecutionHandler.class);
            super.rejectedExecution(r, e);
        }
    }
}