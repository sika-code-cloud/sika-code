package com.sika.code.monitor.core.db.common.configuration;

import com.sika.code.monitor.core.db.common.manager.DataSourceConnectPoolMetricsManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
public class DataSourceConnectPoolMetricsMangerAutoConfiguration {


    @Bean
    public DataSourceConnectPoolMetricsManager dataSourceConnectPoolMetricsManager(MeterRegistry meterRegistry) {
        return new DataSourceConnectPoolMetricsManager(meterRegistry);
    }

}
