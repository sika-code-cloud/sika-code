package com.sika.code.monitor.core.db.connect.hikari.metrics;

import cn.hutool.core.util.StrUtil;
import com.sika.code.monitor.core.db.common.metrics.BaseDataSourceConnectPoolMetrics;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;

import javax.sql.DataSource;
import java.lang.reflect.Field;

/**
 * DataSourceConnectPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
public class HikariConnectPoolMetrics extends BaseDataSourceConnectPoolMetrics<HikariDataSource> {
    /**
     * 指标注册
     *
     * @param meterRegistry : 注册器
     * @param dataSource    : 数据源
     */
    @Override
    public void metricRegistry(MeterRegistry meterRegistry, HikariDataSource dataSource, String poolName) {
        if (StrUtil.isBlank(poolName)) {
            poolName = dataSource.getPoolName();
        }
        try {
            LOGGER.info("数据源连接监控-注册数据源-名称【{}】，数据源【{}】", poolName, dataSource);
            // 当已经注册过则无需注册
            if (dataSource.getMetricsTrackerFactory() != null) {
                return;
            }
            Field pollNameField = HikariConfig.class.getDeclaredField("poolName");
            pollNameField.setAccessible(true);
            pollNameField.set(dataSource, poolName);
            dataSource.setMetricRegistry(meterRegistry);
        } catch (Exception e) {
            LOGGER.error("监听" + poolName + "失败", e);
        }
    }
}
