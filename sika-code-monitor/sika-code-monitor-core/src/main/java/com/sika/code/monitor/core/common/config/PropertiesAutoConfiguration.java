package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
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
@EnableConfigurationProperties(MetricsProperties.class)
public class PropertiesAutoConfiguration {
    @Bean
    public LoadMetricsConfigManager loadMetricsConfigManager(MetricsProperties metricsProperties) {
        return new LoadMetricsConfigManager(metricsProperties);
    }
}
