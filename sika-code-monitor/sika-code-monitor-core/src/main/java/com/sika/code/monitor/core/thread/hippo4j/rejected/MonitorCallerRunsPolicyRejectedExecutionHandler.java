package com.sika.code.monitor.core.thread.hippo4j.rejected;

import cn.hippo4j.common.executor.support.CustomRejectedExecutionHandler;
import com.sika.code.monitor.core.thread.hippo4j.manager.Hippo4jThreadPoolMetricsManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class MonitorCallerRunsPolicyRejectedExecutionHandler implements CustomRejectedExecutionHandler {


    @Override
    public Integer getType() {
        return 10;
    }

    @Override
    public RejectedExecutionHandler generateRejected() {
        return new CustomMonitorCallerRunsPolicy();
    }

    @Override
    public String getName() {
        return "monitorCallerRunsPolicy";
    }

    public static class CustomMonitorCallerRunsPolicy extends ThreadPoolExecutor.CallerRunsPolicy {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            Hippo4jThreadPoolMetricsManager.monitorRejected(r, e, MonitorCallerRunsPolicyRejectedExecutionHandler.class);
            super.rejectedExecution(r, e);
        }
    }
}