package com.sika.code.monitor.core.thread.dynamictp.configuration;

import com.sika.code.monitor.core.thread.dynamictp.manager.DynamicTpThreadPoolMetricsManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.dromara.dynamictp.core.thread.DtpExecutor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DynamicTpThreadPoolAutoConfiguration
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnClass(DtpExecutor.class)
@ConditionalOnBean({MeterRegistry.class})
public class DynamicTpThreadPoolMetricsAutoConfiguration {

    @Bean
    public DynamicTpThreadPoolMetricsManager dynamicTpThreadPoolMetricsManager(MeterRegistry meterRegistry) {
        return new DynamicTpThreadPoolMetricsManager(meterRegistry);
    }

}
