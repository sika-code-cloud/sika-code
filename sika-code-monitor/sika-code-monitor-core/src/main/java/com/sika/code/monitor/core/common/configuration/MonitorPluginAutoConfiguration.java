package com.sika.code.monitor.core.common.configuration;

import com.sika.code.monitor.core.db.DataSourceConnectPoolMetrics;
import com.sika.code.monitor.core.db.DbClientInvokedTimedPlugin;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
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
@ConditionalOnBean(MeterRegistry.class)
public class MonitorPluginAutoConfiguration {

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
