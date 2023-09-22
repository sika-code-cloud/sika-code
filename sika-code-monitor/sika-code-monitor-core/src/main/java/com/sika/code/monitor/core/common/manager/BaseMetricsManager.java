package com.sika.code.monitor.core.common.manager;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.threadpool.config.ThreadPoolMetricsConfig;
import com.sika.code.monitor.core.threadpool.enums.ThreadPoolTypeEnum;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseMetriManager
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@AllArgsConstructor
public abstract class BaseMetricsManager<T extends BaseMetricsTypeEnum> {
    protected final LoadMetricsConfigManager loadMetricsConfigManager;

    protected final MeterRegistry meterRegistry;

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public abstract void registerMetrics();

    protected String getThreadPoolPrefix() {
        return getThreadPoolMetricsConfig().getMetricsName();
    }

    protected ThreadPoolMetricsConfig getThreadPoolMetricsConfig() {
        return loadMetricsConfigManager.getMetricsConfigInstance(getMetricsTypeEnum().getType(),
            ThreadPoolMetricsConfig.class);
    }

    protected abstract T getMetricsTypeEnum();
}
