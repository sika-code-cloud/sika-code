package com.sika.code.monitor.core.thread.hippo4j.manager;

import cn.hippo4j.common.executor.support.CustomRejectedExecutionHandler;
import cn.hippo4j.core.executor.DynamicThreadPoolExecutor;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Hippo4jThreadPoolManager
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@Slf4j
@AllArgsConstructor
public class Hippo4jThreadPoolMetricsManager implements BaseMetricsManager {

    private final MeterRegistry meterRegistry;

    @Override
    public void registerMetrics() {
        Map<String, DynamicThreadPoolExecutor> dynamicThreadPoolExecutorMap = SpringUtil.getBeansOfType(DynamicThreadPoolExecutor.class);
        if (CollUtil.isEmpty(dynamicThreadPoolExecutorMap)) {
            return;
        }
        for (DynamicThreadPoolExecutor threadPoolExecutor : dynamicThreadPoolExecutorMap.values()) {
            ThreadPoolMetrics.monitor(meterRegistry, threadPoolExecutor, ThreadPoolTypeEnum.BUSINESS_HIPPO4J, threadPoolExecutor.getThreadPoolId());
        }
    }

    public static void monitorRejected(Runnable r, ThreadPoolExecutor e, Class<? extends CustomRejectedExecutionHandler> rClass) {
        String threadPrefix = "default-prefix";
        if (e instanceof DynamicThreadPoolExecutor) {
            threadPrefix = ((DynamicThreadPoolExecutor) e).getThreadPoolId();
        }
        log.warn("触发拒绝策略!" +
                        "executing {} reject policy" +
                        " threadPrefix: {}, Pool Size: {} (active: {}, core: {}, max: {}, largest: {}), Task: {} (completed: ",
                rClass.getClass(),
                threadPrefix, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(),
                e.getLargestPoolSize(),
                e.getTaskCount());
        ThreadPoolMetrics.incrementRejectTaskCount(e);
    }
}
