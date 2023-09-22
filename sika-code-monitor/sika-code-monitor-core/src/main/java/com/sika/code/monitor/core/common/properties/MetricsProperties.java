package com.sika.code.monitor.core.common.properties;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.threadpool.config.ThreadPoolMetricsConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * InvokeTimedProperties
 *
 * @author : sikadai
 * @date : 2023-09-18
 */
@ConfigurationProperties("management.metrics.sika")
@Getter
public class MetricsProperties {
    private final Map<String, InvokeTimedMetricsConfig> invoke = new LinkedHashMap<>();
    private final Map<String, ThreadPoolMetricsConfig> threadPool = new LinkedHashMap<>();

    public BaseMetricsConfig getConfigByType(String type, Class<? extends BaseMetricsConfig> mClass) {
        if (InvokeTimedMetricsConfig.class.equals(mClass)) {
            return invoke.get(type);
        } else if (ThreadPoolMetricsConfig.class.equals(mClass)) {
            return threadPool.get(type);
        }
        return null;
    }
}
