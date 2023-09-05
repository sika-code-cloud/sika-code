package com.sika.code.monitor.core.db.connector.shardingsphere.configuration;

import com.sika.code.monitor.core.db.connector.druid.metrics.DruidConnectorPoolMetrics;
import com.sika.code.monitor.core.db.connector.hikari.metrics.HikariConnectorPoolMetrics;
import com.sika.code.monitor.core.db.connector.shardingsphere.metrics.ShardingSphereDruidConnectorPoolMetrics;
import com.sika.code.monitor.core.db.connector.shardingsphere.metrics.ShardingSphereHikariConnectorPoolMetrics;
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
 * ShardingSphereConnectAutoConfiguration
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
@ConditionalOnClass({ShardingSphereAutoConfiguration.class, ShardingSphereDataSource.class})
public class ShardingSphereConnectAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({ShardingSphereHikariConnectorPoolMetrics.class})
    @ConditionalOnBean({HikariConnectorPoolMetrics.class})
    public ShardingSphereHikariConnectorPoolMetrics shardingSphereHikariDataSourceConnectorPoolMetrics(
        HikariConnectorPoolMetrics hikariConnectorPoolMetrics,
        ShardingSphereAutoConfiguration shardingSphereAutoConfiguration) {
        ShardingSphereHikariConnectorPoolMetrics hikariPoolMetrics =
            new ShardingSphereHikariConnectorPoolMetrics();
        hikariPoolMetrics.setShardingSphereAutoConfiguration(shardingSphereAutoConfiguration);
        hikariPoolMetrics.setSourceConnectPoolMetrics(hikariConnectorPoolMetrics);
        return hikariPoolMetrics;
    }

    @Bean
    @ConditionalOnMissingBean({ShardingSphereDruidConnectorPoolMetrics.class})
    @ConditionalOnBean({DruidConnectorPoolMetrics.class})
    public ShardingSphereDruidConnectorPoolMetrics shardingSphereDruidDataSourceConnectorPoolMetrics(
        DruidConnectorPoolMetrics druidConnectorPoolMetrics,
        ShardingSphereAutoConfiguration shardingSphereAutoConfiguration) {
        ShardingSphereDruidConnectorPoolMetrics druidPoolMetrics =
            new ShardingSphereDruidConnectorPoolMetrics();
        druidPoolMetrics.setShardingSphereAutoConfiguration(shardingSphereAutoConfiguration);
        druidPoolMetrics.setSourceConnectPoolMetrics(druidConnectorPoolMetrics);
        return druidPoolMetrics;
    }

}
