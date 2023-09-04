package com.sika.code.monitor.core.thread.hippo4j.rejected;

import cn.hippo4j.common.executor.support.CustomRejectedExecutionHandler;
import cn.hippo4j.core.executor.DynamicThreadPoolExecutor;
import com.sika.code.monitor.core.common.metrics.ThreadPoolMetrics;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MonitorRejectedExecutionHandler implements CustomRejectedExecutionHandler {

    @Override
    public Integer getType() {
        return 12;
    }

    @Override
    public RejectedExecutionHandler generateRejected() {
        return new CustomErrorLogRejectedExecutionHandler();
    }

    @Override
    public String getName() {
        return "monitor";
    }

    public static class CustomErrorLogRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            String threadPrefix = "default-prefix";
            if (e instanceof DynamicThreadPoolExecutor) {
                threadPrefix = ((DynamicThreadPoolExecutor)e).getThreadPoolId();
            }
            log.warn("触发拒绝策略!" +
                    "executing {} reject policy" +
                    " threadPrefix: {}, Pool Size: {} (active: {}, core: {}, max: {}, largest: {}), Task: {} (completed: ",
                MonitorRejectedExecutionHandler.class.getName(),
                threadPrefix, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(),
                e.getLargestPoolSize(),
                e.getTaskCount());
            ThreadPoolMetrics.incrementRejectTaskCount(e);
        }
    }
}