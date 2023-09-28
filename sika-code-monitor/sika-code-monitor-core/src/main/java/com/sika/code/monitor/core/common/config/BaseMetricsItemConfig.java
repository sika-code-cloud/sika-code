package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MetricsCommonConfig
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
@Setter
@Getter
public abstract class BaseMetricsItemConfig<T extends BaseMetricsConfig<?>> {
    protected String metricsName;
    protected String metricsDesc;

    protected Boolean enabled = true;
    protected String metricsType;

    /**
     * 保留执行时间指标配置的引用
     */
    private T metricsConfig;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Logger getLogger() {
        return logger;
    }

    public void buildMetricsType(String metricsType) {
        if (this.metricsType != null) {
            return;
        }
        setMetricsType(metricsType);
    }

    public void buildMetricsConfig(T metricsConfig) {
        if (this.metricsConfig != null) {
            return;
        }
        setMetricsConfig(metricsConfig);
    }

}
