package com.sika.code.monitor.core.db.connectorpool.druid.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.sika.code.monitor.core.db.connectorpool.druid.metrics.DruidConnectorPoolMetrics;
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
@ConditionalOnClass(DruidDataSource.class)
@AutoConfiguration(
    after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnBean({MeterRegistry.class})
public class DruidConnectorAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "spring.monitor.db.druid.connectorPool.enabled", havingValue = "true", matchIfMissing = true)
    public DruidConnectorPoolMetrics druidConnectorPoolMetrics() {
        return new DruidConnectorPoolMetrics();
    }

}
