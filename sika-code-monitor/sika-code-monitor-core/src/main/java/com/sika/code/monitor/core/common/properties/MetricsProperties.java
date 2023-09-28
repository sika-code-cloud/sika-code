package com.sika.code.monitor.core.common.properties;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.threadpool.common.config.ThreadPoolMetricsConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * InvokeTimedProperties
 *
 * @author : sikadai
 * @date : 2023-09-18
 */
@ConfigurationProperties("management.metrics.sika")
@Getter
public class MetricsProperties {

    private final InvokeTimedMetricsConfig invoke = new InvokeTimedMetricsConfig();
    private final ThreadPoolMetricsConfig threadPool = new ThreadPoolMetricsConfig();

    public BaseMetricsConfig getConfigByType(Class<? extends BaseMetricsConfig> mClass) {
        if (InvokeTimedMetricsConfig.class.equals(mClass)) {
            return invoke;
        } else if (ThreadPoolMetricsConfig.class.equals(mClass)) {
            return threadPool;
        }
        return null;
    }
}
