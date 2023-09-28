package com.sika.code.monitor.core.db.common.manager;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.db.common.metrics.BaseDataSourceConnectorPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author daiqi
 * @create 2023-09-03 23:50
 */
public class DataSourceConnectorPoolMetricsManager extends BaseMetricsManager<BaseMetricsConfig<?>, BaseMetricsTypeEnum> {

    public DataSourceConnectorPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
                                                 MeterRegistry meterRegistry) {
        super(loadMetricsConfigManager, meterRegistry);
    }

    @Override
    public void doRegisterMetrics() {
        Map<String, DataSource> dataSources = SpringUtil.getBeansOfType(DataSource.class);
        LOGGER.info("数据源连接监控-加载的数据源【{}】", dataSources);
        if (CollectionUtils.isEmpty(dataSources)) {
            return;
        }
        for (DataSource dataSource : dataSources.values()) {
            LOGGER.info("数据源连接监控-当前数据源类为【{}】", dataSource.getClass());
            boolean match = false;
            Map<String, BaseDataSourceConnectorPoolMetrics> metricsMap =
                    SpringUtil.getBeansOfType(BaseDataSourceConnectorPoolMetrics.class);
            for (BaseDataSourceConnectorPoolMetrics metrics : metricsMap.values()) {
                Class<?> metricsClass =
                        ReflectionKit.getSuperClassGenericType(metrics.getClass(), BaseDataSourceConnectorPoolMetrics.class,
                                0);
                if (metricsClass.isAssignableFrom(dataSource.getClass())) {
                    metrics.metricRegistry(meterRegistry, dataSource, null);
                    match = true;
                }
            }
            if (!match) {
                LOGGER.info("【{}】未匹配到数据-跳过监控", dataSource.getClass());
            }
        }
    }

    @Override
    protected Class<BaseMetricsConfig<?>> getConfigClass() {
        return null;
    }

    @Override
    protected BaseMetricsTypeEnum getMetricsTypeEnum() {
        return null;
    }
}
