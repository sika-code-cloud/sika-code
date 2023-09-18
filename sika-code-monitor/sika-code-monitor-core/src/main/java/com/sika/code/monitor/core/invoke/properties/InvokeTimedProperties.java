package com.sika.code.monitor.core.invoke.properties;

import com.sika.code.monitor.core.invoke.config.InvokeTimedConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * InvokeTimedProperties
 *
 * @author : sikadai
 * @date : 2023-09-18
 */
@ConfigurationProperties("management.metrics")
public class InvokeTimedProperties {
    private String name1;
    private final Map<String, InvokeTimedConfig> invoke = new LinkedHashMap<>();

    public Map<String, InvokeTimedConfig> getInvoke() {
        return invoke;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
