package com.sika.code.monitor.core.db.common.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * 基础数据源连接池指标类
 *
 * @author daiqi
 * @create 2023-09-04 0:04
 */
public abstract class BaseDataSourceConnectorPoolMetrics<T extends DataSource> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 指标注册
     *
     * @param dataSource : 数据源
     */
    public abstract void metricRegistry(MeterRegistry meterRegistry, T dataSource, String poolName);

}
