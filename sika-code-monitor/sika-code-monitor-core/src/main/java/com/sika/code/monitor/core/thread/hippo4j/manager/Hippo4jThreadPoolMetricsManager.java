package com.sika.code.monitor.core.thread.hippo4j.manager;

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
}
