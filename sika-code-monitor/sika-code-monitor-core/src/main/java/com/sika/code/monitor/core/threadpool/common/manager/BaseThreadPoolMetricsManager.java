package com.sika.code.monitor.core.threadpool.common.manager;

import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.common.config.ThreadPoolMetricsConfig;
import com.sika.code.monitor.core.threadpool.common.enums.ThreadPoolTypeEnum;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * BaseThreadPoolMetricsManager
 *
 * @author : daiqi
 * @date : 2023-09-22
 */
public abstract class BaseThreadPoolMetricsManager extends BaseMetricsManager<ThreadPoolMetricsConfig, ThreadPoolTypeEnum> {
    public BaseThreadPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry) {
        super(loadMetricsConfigManager, meterRegistry);
    }
    protected String getThreadPoolPrefix() {
        return getMetricsConfig().getMetricsName();
    }

    @Override
    protected Class<ThreadPoolMetricsConfig> getConfigClass() {
        return ThreadPoolMetricsConfig.class;
    }

}
