package com.sika.code.monitor.core.processor.configuration;

import com.sika.code.monitor.core.processor.metrics.CustProcessorMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TomcatAutoConfig
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
public class ProcessorMetricsAutoConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.monitor.processor.enabled", havingValue = "true", matchIfMissing = true)
    CustProcessorMetrics custProcessorMetrics() {
        return new CustProcessorMetrics();
    }
}
