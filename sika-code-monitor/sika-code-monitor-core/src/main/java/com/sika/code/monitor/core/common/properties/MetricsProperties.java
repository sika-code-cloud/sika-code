package com.sika.code.monitor.core.common.properties;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedConfig;
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
public class MetricsProperties {
    private final Map<String, InvokeTimedConfig> invoke = new LinkedHashMap<>();

    public Map<String, InvokeTimedConfig> getInvoke() {
        return invoke;
    }

    public BaseMetricsConfig getConfigByType(String type, Class<? extends BaseMetricsConfig> mClass) {
        if (InvokeTimedConfig.class.equals(mClass)) {
            return invoke.get(type);
        }
        return null;
    }
}
