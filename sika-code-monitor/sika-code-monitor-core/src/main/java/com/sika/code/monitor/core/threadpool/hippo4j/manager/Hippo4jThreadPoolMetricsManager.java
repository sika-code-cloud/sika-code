package com.sika.code.monitor.core.threadpool.hippo4j.manager;

import cn.hippo4j.core.executor.DynamicThreadPoolExecutor;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.threadpool.metrics.ThreadPoolMetrics;
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
public class Hippo4jThreadPoolMetricsManager extends BaseMetricsManager<ThreadPoolTypeEnum> {

    public Hippo4jThreadPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry) {
        super(loadMetricsConfigManager, meterRegistry);
    }

    @Override
    public void registerMetrics() {
        Map<String, DynamicThreadPoolExecutor> dynamicThreadPoolExecutorMap =
            SpringUtil.getBeansOfType(DynamicThreadPoolExecutor.class);
        if (CollUtil.isEmpty(dynamicThreadPoolExecutorMap)) {
            return;
        }
        for (DynamicThreadPoolExecutor threadPoolExecutor : dynamicThreadPoolExecutorMap.values()) {
            ThreadPoolMetrics.monitor(meterRegistry, threadPoolExecutor, getMetricsTypeEnum(),
                threadPoolExecutor.getThreadPoolId(),
                value -> threadPoolExecutor.getRejectCount().get());
        }
    }

    @Override
    protected ThreadPoolTypeEnum getMetricsTypeEnum() {
        return ThreadPoolTypeEnum.BUSINESS_HIPPO4J;
    }

}
