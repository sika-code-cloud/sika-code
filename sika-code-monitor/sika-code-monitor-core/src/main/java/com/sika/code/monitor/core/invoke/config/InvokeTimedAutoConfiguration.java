package com.sika.code.monitor.core.invoke.config;

import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import com.sika.code.monitor.core.invoke.properties.InvokeTimedProperties;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InvokeTimedAutoConfiguration
 *
 * @author : sikadai
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
@EnableConfigurationProperties(InvokeTimedProperties.class)
public class InvokeTimedAutoConfiguration {
    private final InvokeTimedProperties invokeTimedProperties;

    public InvokeTimedAutoConfiguration(InvokeTimedProperties invokeTimedProperties) {
        this.invokeTimedProperties = invokeTimedProperties;
    }

    @Bean
    public InvokeTimedMetrics invokeTimedMetrics() {
        return new InvokeTimedMetrics(invokeTimedProperties);
    }
}
