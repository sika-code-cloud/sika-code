package com.sika.code.monitor.core.db.connectorpool.hikari.configuration;

import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.db.connectorpool.hikari.metrics.HikariConnectorPoolMetrics;
import com.zaxxer.hikari.HikariDataSource;
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
@ConditionalOnClass(HikariDataSource.class)
@AutoConfiguration(
    after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnBean({MeterRegistry.class})
public class HikariConnectorAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.DB_HIKARI_CONNECTOR_POOL, havingValue = "true",
        matchIfMissing = true)
    public HikariConnectorPoolMetrics hikariConnectorPoolMetrics() {
        return new HikariConnectorPoolMetrics();
    }

}
