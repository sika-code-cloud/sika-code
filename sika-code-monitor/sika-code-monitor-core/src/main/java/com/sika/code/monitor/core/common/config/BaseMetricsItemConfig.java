package com.sika.code.monitor.core.common.config;

import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
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

    public void buildMetricsNameAndDesc() {
        BaseMetricsTypeEnum metricsConfigTypeEnum =
            BaseTypeEnum.find(getMetricsType(), getMetricsConfig().getMetricsTypeEnumClass());
        if (this.metricsName == null && metricsConfigTypeEnum != null) {
            setMetricsName(metricsConfigTypeEnum.getName());
        }
        if (this.metricsDesc == null && metricsConfigTypeEnum != null) {
            setMetricsDesc(metricsConfigTypeEnum.getDesc());
        }
    }

}
