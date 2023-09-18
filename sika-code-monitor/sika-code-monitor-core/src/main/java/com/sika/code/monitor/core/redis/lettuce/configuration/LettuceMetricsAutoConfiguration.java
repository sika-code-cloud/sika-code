package com.sika.code.monitor.core.redis.lettuce.configuration;

import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import com.sika.code.monitor.core.redis.lettuce.plugin.MicrometerCommandLatencyRecorder;
import io.lettuce.core.RedisClient;
import io.lettuce.core.metrics.MicrometerOptions;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@AutoConfiguration(after = {
    org.springframework.boot.actuate.autoconfigure.metrics.redis.LettuceMetricsAutoConfiguration.class, MetricsAutoConfiguration.class,
    CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnClass({RedisClient.class})
@ConditionalOnBean({MeterRegistry.class})
public class LettuceMetricsAutoConfiguration {

    @Bean
    @ConditionalOnBean(InvokeTimedMetrics.class)
    ClientResourcesBuilderCustomizer customerLettuceMetrics(MeterRegistry meterRegistry, MicrometerOptions options, InvokeTimedMetrics invokeTimedMetrics) {
        return (client) -> client.commandLatencyRecorder(
            new MicrometerCommandLatencyRecorder(meterRegistry, options, invokeTimedMetrics));
    }
}
