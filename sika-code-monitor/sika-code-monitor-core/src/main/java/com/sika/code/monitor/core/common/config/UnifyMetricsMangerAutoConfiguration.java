package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.manager.UnifyMetricsManagerHandler;
import com.sika.code.monitor.core.db.common.manager.DataSourceConnectPoolMetricsManager;
import com.sika.code.monitor.core.tomcat.configuration.TomcatMetricsAutoConfig;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
public class UnifyMetricsMangerAutoConfiguration {
    @Bean
    public UnifyMetricsManagerHandler unifyMetricsManagerHandler() {
        return new UnifyMetricsManagerHandler();
    }

}
