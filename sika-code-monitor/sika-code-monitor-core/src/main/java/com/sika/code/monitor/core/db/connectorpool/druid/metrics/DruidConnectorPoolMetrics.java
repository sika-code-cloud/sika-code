package com.sika.code.monitor.core.db.connectorpool.druid.metrics;

import cn.hutool.core.text.StrPool;
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
    private static final String POLL_LABEL_NAME = "pool";
    private static final String METRICS_PREFIX_NAME = "druid";

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
            // 重新设置连接池名称
            bindMetric(meterRegistry, dataSource, poolName);
        } catch (Exception e) {
            LOGGER.error("监听" + poolName + "失败", e);
        }
    }

    /**
     * 度量指标注册
     *
     * @param meterRegistry
     * @param druidDataSource
     * @param poolName
     */
    private void bindMetric(MeterRegistry meterRegistry, DruidDataSource druidDataSource, String poolName) {
        // basic configurations
        createGauge(druidDataSource, meterRegistry, "initial.size", poolName, "Initial size",
            datasource -> druidDataSource.getInitialSize());
        createGauge(druidDataSource, meterRegistry, "min.idle", poolName, "Min idle",
            datasource -> druidDataSource.getMinIdle());
        createGauge(druidDataSource, meterRegistry, "max.active", poolName, "Max active",
            datasource -> druidDataSource.getMaxActive());

        // connection pool core metrics
        createGauge(druidDataSource, meterRegistry, "active.count", poolName, "Active count",
            datasource -> druidDataSource.getActiveCount());
        createGauge(druidDataSource, meterRegistry, "active.peak", poolName, "Active peak",
            datasource -> druidDataSource.getActivePeak());
        createGauge(druidDataSource, meterRegistry, "pooling.peak", poolName, "Pooling peak",
            datasource -> druidDataSource.getPoolingPeak());
        createGauge(druidDataSource, meterRegistry, "pooling.count", poolName, "Pooling count",
            datasource -> druidDataSource.getPoolingCount());
        createGauge(druidDataSource, meterRegistry, "wait.thread.count", poolName, "Wait thread count",
            datasource -> druidDataSource.getWaitThreadCount());

        // connection pool detail metrics
        createGauge(druidDataSource, meterRegistry, "not.empty.wait.count", poolName, "Not empty wait count",
            datasource -> druidDataSource.getNotEmptyWaitCount());
        createGauge(druidDataSource, meterRegistry, "not.empty.wait.millis", poolName, "Not empty wait millis",
            datasource -> druidDataSource.getNotEmptyWaitMillis());
        createGauge(druidDataSource, meterRegistry, "not.empty.thread.count", poolName, "Not empty thread count",
            datasource -> druidDataSource.getNotEmptyWaitThreadCount());

        createGauge(druidDataSource, meterRegistry, "logic.connect.count", poolName, "Logic connect count",
            datasource -> druidDataSource.getConnectCount());
        createGauge(druidDataSource, meterRegistry, "logic.close.count", poolName, "Logic close count",
            datasource -> druidDataSource.getCloseCount());
        createGauge(druidDataSource, meterRegistry, "logic.connect.error.count", poolName, "Logic connect error count",
            datasource -> druidDataSource.getConnectErrorCount());
        createGauge(druidDataSource, meterRegistry, "physical.connect.count", poolName, "Physical connect count",
            datasource -> druidDataSource.getCreateCount());
        createGauge(druidDataSource, meterRegistry, "physical.close.count", poolName, "Physical close count",
            datasource -> druidDataSource.getDestroyCount());
        createGauge(druidDataSource, meterRegistry, "physical.connect.error.count", poolName,
            "Physical connect error count", datasource -> druidDataSource.getCreateErrorCount());

        // sql execution core metrics
        createGauge(druidDataSource, meterRegistry, "error.count", poolName, "Error count",
            datasource -> druidDataSource.getErrorCount());
        createGauge(druidDataSource, meterRegistry, "execute.count", poolName, "Execute count",
            datasource -> druidDataSource.getExecuteCount());
        // transaction metrics
        createGauge(druidDataSource, meterRegistry, "start.transaction.count", poolName, "Start transaction count",
            datasource -> druidDataSource.getStartTransactionCount());
        createGauge(druidDataSource, meterRegistry, "commit.count", poolName, "Commit count",
            datasource -> druidDataSource.getCommitCount());
        createGauge(druidDataSource, meterRegistry, "rollback.count", poolName, "Rollback count",
            datasource -> druidDataSource.getRollbackCount());

        // sql execution detail
        createGauge(druidDataSource, meterRegistry, "prepared.statement.open.count", poolName,
            "Prepared statement open count", datasource -> druidDataSource.getPreparedStatementCount());
        createGauge(druidDataSource, meterRegistry, "prepared.statement.closed.count", poolName,
            "Prepared statement closed count", datasource -> druidDataSource.getClosedPreparedStatementCount());
        createGauge(druidDataSource, meterRegistry, "ps.cache.access.count", poolName, "PS cache access count",
            datasource -> druidDataSource.getCachedPreparedStatementAccessCount());
        createGauge(druidDataSource, meterRegistry, "ps.cache.hit.count", poolName, "PS cache hit count",
            datasource -> druidDataSource.getCachedPreparedStatementHitCount());
        createGauge(druidDataSource, meterRegistry, "ps.cache.miss.count", poolName, "PS cache miss count",
            datasource -> druidDataSource.getCachedPreparedStatementMissCount());
        createGauge(druidDataSource, meterRegistry, "execute.query.count", poolName, "Execute query count",
            datasource -> druidDataSource.getExecuteQueryCount());
        createGauge(druidDataSource, meterRegistry, "execute.update.count", poolName, "Execute update count",
            datasource -> druidDataSource.getExecuteUpdateCount());
        createGauge(druidDataSource, meterRegistry, "execute.batch.count", poolName, "Execute batch count",
            datasource -> druidDataSource.getExecuteBatchCount());

        // none core metrics, some are static configurations
        createGauge(druidDataSource, meterRegistry, "max.wait", "Max wait", poolName,
            datasource -> druidDataSource.getMaxWait());
        createGauge(druidDataSource, meterRegistry, "max.wait.thread.count", poolName, "Max wait thread count",
            datasource -> druidDataSource.getMaxWaitThreadCount());
        createGauge(druidDataSource, meterRegistry, "login.timeout", poolName, "Login timeout",
            datasource -> druidDataSource.getLoginTimeout());
        createGauge(druidDataSource, meterRegistry, "query.timeout", poolName, "Query timeout",
            datasource -> druidDataSource.getQueryTimeout());
        createGauge(druidDataSource, meterRegistry, "transaction.query.timeout", poolName, "Transaction query timeout",
            datasource -> druidDataSource.getTransactionQueryTimeout());
    }

    private void createGauge(DruidDataSource weakRef, MeterRegistry meterRegistry, String metric, String poolName,
        String help, ToDoubleFunction<DruidDataSource> measure) {
        String fullMetric = StrUtil.join(StrPool.DOT, METRICS_PREFIX_NAME, metric);
        Gauge.builder(fullMetric, weakRef, measure).description(help).tag(POLL_LABEL_NAME, poolName)
            .register(meterRegistry);
    }
}
