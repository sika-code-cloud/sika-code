package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
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
public abstract class BaseMetricsConfig {
    protected String metricsName;
    protected String metricsDesc;

    protected Boolean enabled = true;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Logger getLogger() {
        return logger;
    }

    public abstract Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass();
}
