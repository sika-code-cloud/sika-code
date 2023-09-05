package com.sika.code.monitor.core.db.connector.hikari.configuration;

import com.sika.code.monitor.core.db.connector.hikari.metrics.HikariConnectorPoolMetrics;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@ConditionalOnClass(HikariDataSource.class)
@AutoConfiguration(
    after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnBean({MeterRegistry.class})
public class HikariConnectorAutoConfiguration {

    @Bean
    public HikariConnectorPoolMetrics hikariConnectorPoolMetrics() {
        return new HikariConnectorPoolMetrics();
    }

}
