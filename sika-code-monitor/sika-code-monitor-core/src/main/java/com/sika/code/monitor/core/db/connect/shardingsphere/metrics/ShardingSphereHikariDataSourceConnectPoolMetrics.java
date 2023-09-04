package com.sika.code.monitor.core.db.connect.shardingsphere.metrics;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

/**
 * DataSourceConnectPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Data
public class ShardingSphereHikariDataSourceConnectPoolMetrics extends BaseShardingSphereDataSourceConnectPoolMetrics<HikariDataSource> {

}
