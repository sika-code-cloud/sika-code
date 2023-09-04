package com.sika.code.monitor.core.db.connect.hikari.configuration;

import com.sika.code.monitor.core.db.connect.hikari.metrics.HikariConnectPoolMetrics;
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
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, HikariDataSource.class, MeterRegistry.class})
@ConditionalOnClass(HikariDataSource.class)
@ConditionalOnBean({MeterRegistry.class})
public class HikariConnectAutoConfiguration {

    @Bean
    public HikariConnectPoolMetrics hikariConnectPoolMetrics() {
        return new HikariConnectPoolMetrics();
    }

}
