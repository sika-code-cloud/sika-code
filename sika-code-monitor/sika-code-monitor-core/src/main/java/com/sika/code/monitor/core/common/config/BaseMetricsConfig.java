package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MetricsCommonConfig
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
public abstract class BaseMetricsConfig {
    protected String metricsName;
    protected String metricsDesc;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Logger getLogger() {
        return logger;
    }

    public BaseMetricsConfig buildLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public void setMetricsName(String metricsName) {
        this.metricsName = metricsName;
    }

    public void setMetricsDesc(String metricsDesc) {
        this.metricsDesc = metricsDesc;
    }

    public String getMetricsName() {
        return metricsName;
    }

    public BaseMetricsConfig buildMetricsName(String metricsName) {
        this.metricsName = metricsName;
        return this;
    }

    public String getMetricsDesc() {
        return metricsDesc;
    }

    public BaseMetricsConfig buildMetricsDescription(String metricsDescription) {
        this.metricsDesc = metricsDescription;
        return this;
    }

    public abstract Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass();
}
