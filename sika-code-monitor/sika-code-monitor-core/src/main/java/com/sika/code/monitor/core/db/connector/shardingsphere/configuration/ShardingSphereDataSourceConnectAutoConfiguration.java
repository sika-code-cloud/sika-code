package com.sika.code.monitor.core.db.connector.shardingsphere.configuration;

import com.sika.code.monitor.core.db.connector.hikari.metrics.HikariConnectPoolMetrics;
import com.sika.code.monitor.core.db.connector.shardingsphere.metrics.ShardingSphereHikariDataSourceConnectPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@ConditionalOnClass({ShardingSphereAutoConfiguration.class, ShardingSphereDataSource.class})
public class ShardingSphereDataSourceConnectAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({ShardingSphereHikariDataSourceConnectPoolMetrics.class})
    @ConditionalOnBean({HikariConnectPoolMetrics.class})
    public ShardingSphereHikariDataSourceConnectPoolMetrics shardingSphereHikariDataSourceConnectPoolMetrics(HikariConnectPoolMetrics hikariConnectPoolMetrics,
                                                                 ShardingSphereAutoConfiguration shardingSphereAutoConfiguration) {
        ShardingSphereHikariDataSourceConnectPoolMetrics hikariPoolMetrics = new ShardingSphereHikariDataSourceConnectPoolMetrics();
        hikariPoolMetrics.setShardingSphereAutoConfiguration(shardingSphereAutoConfiguration);
        hikariPoolMetrics.setSourceConnectPoolMetrics(hikariConnectPoolMetrics);
        return hikariPoolMetrics;
    }

}
