package com.sika.code.monitor.core.threadpool.hippo4j.configuration;

import cn.hippo4j.core.executor.DynamicThreadPoolExecutor;
import com.sika.code.monitor.core.threadpool.hippo4j.manager.Hippo4jThreadPoolMetricsManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnClass(DynamicThreadPoolExecutor.class)
@ConditionalOnBean({MeterRegistry.class})
public class Hippo4jThreadPoolMetricsAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "spring.monitor.hippo4j.threadPool.enabled", havingValue = "true", matchIfMissing = true)
    public Hippo4jThreadPoolMetricsManager hippo4jThreadPoolMetricsManager(MeterRegistry meterRegistry) {
        return new Hippo4jThreadPoolMetricsManager(meterRegistry);
    }

}
