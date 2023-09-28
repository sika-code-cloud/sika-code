package com.sika.code.monitor.core.common.manager;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.config.BaseMetricsItemConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseMetriManager
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@AllArgsConstructor
public abstract class BaseMetricsManager<C extends BaseMetricsConfig<?>, M extends BaseMetricsTypeEnum> {
    protected final LoadMetricsConfigManager loadMetricsConfigManager;

    protected final MeterRegistry meterRegistry;

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void registerMetrics() {
        BaseMetricsItemConfig config = getMetricsConfig();
        if (config == null) {
            return;
        }
        if (BooleanUtils.isFalse(config.getEnabled())) {
            return;
        }
        doRegisterMetrics();
    }

    public abstract void doRegisterMetrics();

    protected BaseMetricsItemConfig<?> getMetricsConfig() {
        M metricsTypeEnum = getMetricsTypeEnum();
        if (metricsTypeEnum == null) {
            return null;
        }
        Class<C> cClass = getConfigClass();
        if (cClass == null) {
            return null;
        }
        return loadMetricsConfigManager.getMetricsItemConfigInstance(metricsTypeEnum.getType(), cClass);
    }

    protected abstract Class<C> getConfigClass();

    protected abstract M getMetricsTypeEnum();
}
