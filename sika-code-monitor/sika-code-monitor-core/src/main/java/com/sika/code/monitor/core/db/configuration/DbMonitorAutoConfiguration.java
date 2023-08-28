package com.sika.code.monitor.core.db.configuration;

import com.sika.code.monitor.core.db.hikari.metrics.DataSourceConnectPoolMetrics;
import com.sika.code.monitor.core.db.plugin.DbClientInvokedTimedPlugin;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
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
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean(MeterRegistry.class)
public class DbMonitorAutoConfiguration {

    @Bean
    @ConditionalOnClass(Interceptor.class)
    public DbClientInvokedTimedPlugin dbClientInvokedTimedPlugin(MeterRegistry meterRegistry) {
        DbClientInvokedTimedPlugin invokedTimedPlugin = new DbClientInvokedTimedPlugin();
        invokedTimedPlugin.setMeterRegistry(meterRegistry);
        return invokedTimedPlugin;
    }

    @Bean
    @ConditionalOnBean({ShardingSphereAutoConfiguration.class, HikariDataSource.class})
    public DataSourceConnectPoolMetrics dataSourceConnectPoolMetrics(MeterRegistry meterRegistry,
        ShardingSphereAutoConfiguration shardingSphereAutoConfiguration) {
        DataSourceConnectPoolMetrics dataSourceConnectPoolMetrics = new DataSourceConnectPoolMetrics();
        dataSourceConnectPoolMetrics.setMeterRegistry(meterRegistry);
        dataSourceConnectPoolMetrics.setShardingSphereAutoConfiguration(shardingSphereAutoConfiguration);
        return dataSourceConnectPoolMetrics;
    }

}
