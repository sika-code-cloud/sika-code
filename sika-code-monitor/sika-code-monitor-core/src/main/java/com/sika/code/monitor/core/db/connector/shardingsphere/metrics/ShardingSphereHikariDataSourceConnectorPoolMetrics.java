package com.sika.code.monitor.core.db.connector.shardingsphere.metrics;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

/**
 * DataSourceConnectPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Data
public class ShardingSphereHikariDataSourceConnectorPoolMetrics extends
    BaseShardingSphereDataSourceConnectorPoolMetrics<HikariDataSource> {

}
