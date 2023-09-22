package com.sika.code.monitor.core.threadpool.hippo4j.manager;

import cn.hippo4j.core.executor.DynamicThreadPoolExecutor;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.common.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.threadpool.common.manager.BaseThreadPoolMetricsManager;
import com.sika.code.monitor.core.threadpool.common.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Hippo4jThreadPoolManager
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@Slf4j
public class Hippo4jThreadPoolMetricsManager extends BaseThreadPoolMetricsManager {

    public Hippo4jThreadPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry) {
        super(loadMetricsConfigManager, meterRegistry);
    }

    @Override
    public void doRegisterMetrics() {
        Map<String, DynamicThreadPoolExecutor> dynamicThreadPoolExecutorMap =
            SpringUtil.getBeansOfType(DynamicThreadPoolExecutor.class);
        if (CollUtil.isEmpty(dynamicThreadPoolExecutorMap)) {
            return;
        }
        for (DynamicThreadPoolExecutor threadPoolExecutor : dynamicThreadPoolExecutorMap.values()) {
            String poolName = StrUtil.join(StrPool.DOT, getThreadPoolPrefix(), threadPoolExecutor.getThreadPoolId());
            ThreadPoolMetrics.monitor(meterRegistry, threadPoolExecutor, getMetricsTypeEnum(), poolName, value -> threadPoolExecutor.getRejectCount().get());
        }
    }

    @Override
    protected ThreadPoolTypeEnum getMetricsTypeEnum() {
        return ThreadPoolTypeEnum.BUSINESS_HIPPO4J;
    }

}
