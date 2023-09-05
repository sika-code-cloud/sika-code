package com.sika.code.monitor.core.db.connector.druid.metrics;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.sika.code.monitor.core.db.common.metrics.BaseDataSourceConnectorPoolMetrics;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.function.ToDoubleFunction;

/**
 * DataSourceConnectPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
public class DruidConnectorPoolMetrics extends BaseDataSourceConnectorPoolMetrics<DruidDataSource> {
    private static final String LABEL_NAME = "pool";
    /**
     * 指标注册
     *
     * @param meterRegistry : 注册器
     * @param dataSource    : 数据源
     */
    @Override
    public void metricRegistry(MeterRegistry meterRegistry, DruidDataSource dataSource, String poolName) {
        if (StrUtil.isBlank(poolName)) {
            poolName = dataSource.getName();
        }
        try {
            LOGGER.info("数据源连接监控-注册数据源-名称【{}】，数据源【{}】", poolName, dataSource);
            // 当已经注册过则无需注册
            metricRegistry(meterRegistry, dataSource);
        } catch (Exception e) {
            LOGGER.error("监听" + poolName + "失败", e);
        }
    }

    private void metricRegistry(MeterRegistry meterRegistry, DruidDataSource druidDataSource) {
        // basic configurations
        createGauge(druidDataSource, meterRegistry,"druid_initial_size", "Initial size",
            (datasource) -> druidDataSource.getInitialSize());
        createGauge(druidDataSource, meterRegistry,"druid_min_idle", "Min idle", datasource -> druidDataSource.getMinIdle());
        createGauge(druidDataSource, meterRegistry,"druid_max_active", "Max active",
            datasource -> druidDataSource.getMaxActive());

        // connection pool core metrics
        createGauge(druidDataSource, meterRegistry,"druid_active_count", "Active count",
            datasource -> druidDataSource.getActiveCount());
        createGauge(druidDataSource, meterRegistry,"druid_active_peak", "Active peak",
            datasource -> druidDataSource.getActivePeak());
        createGauge(druidDataSource, meterRegistry,"druid_pooling_peak", "Pooling peak",
            datasource -> druidDataSource.getPoolingPeak());
        createGauge(druidDataSource, meterRegistry,"druid_pooling_count", "Pooling count",
            datasource -> druidDataSource.getPoolingCount());
        createGauge(druidDataSource, meterRegistry,"druid_wait_thread_count", "Wait thread count",
            datasource -> druidDataSource.getWaitThreadCount());

        // connection pool detail metrics
        createGauge(druidDataSource, meterRegistry,"druid_not_empty_wait_count", "Not empty wait count",
            datasource -> druidDataSource.getNotEmptyWaitCount());
        createGauge(druidDataSource, meterRegistry,"druid_not_empty_wait_millis", "Not empty wait millis",
            datasource -> druidDataSource.getNotEmptyWaitMillis());
        createGauge(druidDataSource, meterRegistry,"druid_not_empty_thread_count", "Not empty thread count",
            datasource -> druidDataSource.getNotEmptyWaitThreadCount());

        createGauge(druidDataSource, meterRegistry,"druid_logic_connect_count", "Logic connect count",
            datasource -> druidDataSource.getConnectCount());
        createGauge(druidDataSource, meterRegistry,"druid_logic_close_count", "Logic close count",
            datasource -> druidDataSource.getCloseCount());
        createGauge(druidDataSource, meterRegistry,"druid_logic_connect_error_count", "Logic connect error count",
            datasource -> druidDataSource.getConnectErrorCount());
        createGauge(druidDataSource, meterRegistry,"druid_physical_connect_count", "Physical connect count",
            datasource -> druidDataSource.getCreateCount());
        createGauge(druidDataSource, meterRegistry,"druid_physical_close_count", "Physical close count",
            datasource -> druidDataSource.getDestroyCount());
        createGauge(druidDataSource, meterRegistry,"druid_physical_connect_error_count", "Physical connect error count",
            datasource -> druidDataSource.getCreateErrorCount());

        // sql execution core metrics
        createGauge(druidDataSource, meterRegistry,"druid_error_count", "Error count",
            datasource -> druidDataSource.getErrorCount());
        createGauge(druidDataSource, meterRegistry,"druid_execute_count", "Execute count",
            datasource -> druidDataSource.getExecuteCount());
        // transaction metrics
        createGauge(druidDataSource, meterRegistry,"druid_start_transaction_count", "Start transaction count",
            datasource -> druidDataSource.getStartTransactionCount());
        createGauge(druidDataSource, meterRegistry,"druid_commit_count", "Commit count",
            datasource -> druidDataSource.getCommitCount());
        createGauge(druidDataSource, meterRegistry,"druid_rollback_count", "Rollback count",
            datasource -> druidDataSource.getRollbackCount());

        // sql execution detail
        createGauge(druidDataSource, meterRegistry,"druid_prepared_statement_open_count", "Prepared statement open count",
            datasource -> druidDataSource.getPreparedStatementCount());
        createGauge(druidDataSource, meterRegistry,"druid_prepared_statement_closed_count", "Prepared statement closed count",
            datasource -> druidDataSource.getClosedPreparedStatementCount());
        createGauge(druidDataSource, meterRegistry,"druid_ps_cache_access_count", "PS cache access count",
            datasource -> druidDataSource.getCachedPreparedStatementAccessCount());
        createGauge(druidDataSource, meterRegistry,"druid_ps_cache_hit_count", "PS cache hit count",
            datasource -> druidDataSource.getCachedPreparedStatementHitCount());
        createGauge(druidDataSource, meterRegistry,"druid_ps_cache_miss_count", "PS cache miss count",
            datasource -> druidDataSource.getCachedPreparedStatementMissCount());
        createGauge(druidDataSource, meterRegistry,"druid_execute_query_count", "Execute query count",
            datasource -> druidDataSource.getExecuteQueryCount());
        createGauge(druidDataSource, meterRegistry,"druid_execute_update_count", "Execute update count",
            datasource -> druidDataSource.getExecuteUpdateCount());
        createGauge(druidDataSource, meterRegistry,"druid_execute_batch_count", "Execute batch count",
            datasource -> druidDataSource.getExecuteBatchCount());

        // none core metrics, some are static configurations
        createGauge(druidDataSource, meterRegistry,"druid_max_wait", "Max wait", datasource -> druidDataSource.getMaxWait());
        createGauge(druidDataSource, meterRegistry,"druid_max_wait_thread_count", "Max wait thread count",
            datasource -> druidDataSource.getMaxWaitThreadCount());
        createGauge(druidDataSource, meterRegistry,"druid_login_timeout", "Login timeout",
            datasource -> druidDataSource.getLoginTimeout());
        createGauge(druidDataSource, meterRegistry,"druid_query_timeout", "Query timeout",
            datasource -> druidDataSource.getQueryTimeout());
        createGauge(druidDataSource, meterRegistry,"druid_transaction_query_timeout", "Transaction query timeout",
            datasource -> druidDataSource.getTransactionQueryTimeout());
    }

    private void createGauge(DruidDataSource weakRef, MeterRegistry meterRegistry, String metric, String help,
        ToDoubleFunction<DruidDataSource> measure) {
        Gauge.builder(metric, weakRef, measure)
            .description(help)
            .tag(LABEL_NAME, weakRef.getName())
            .register(meterRegistry);
    }
}
