package com.sika.code.monitor.core.dubbo.configuration;

import com.sika.code.monitor.core.dubbo.invoke.DubboInvokeMetrics;
import com.sika.code.monitor.core.dubbo.threadpool.DubboThreadPoolMetrics;
import com.sika.code.monitor.core.redis.lettuce.plugin.MicrometerCommandLatencyRecorder;
import io.lettuce.core.RedisClient;
import io.lettuce.core.metrics.MicrometerOptions;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.dubbo.monitor.dubbo.DubboMonitor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnClass({DubboMonitor.class})
@ConditionalOnBean({MeterRegistry.class})
public class DubboMetricsAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "spring.monitor.dubbo.threadPool.enabled", havingValue = "true", matchIfMissing = true)
    DubboThreadPoolMetrics dubboThreadPoolMetrics(MeterRegistry meterRegistry) {
        return new DubboThreadPoolMetrics(meterRegistry);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.monitor.dubbo.invoke.enabled", havingValue = "true", matchIfMissing = true)
    DubboInvokeMetrics dubboInvokeMetrics(MeterRegistry meterRegistry) {
        return new DubboInvokeMetrics(meterRegistry);
    }
}
