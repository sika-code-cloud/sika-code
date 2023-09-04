package com.sika.code.monitor.core.db.common.manager;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.sika.code.monitor.core.db.common.metrics.BaseDataSourceConnectPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author daiqi
 * @create 2023-09-03 23:50
 */
@AllArgsConstructor
@Data
public class DataSourceConnectPoolMetricsManager implements ApplicationListener<ApplicationReadyEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private MeterRegistry meterRegistry;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, DataSource> dataSources = SpringUtil.getBeansOfType(DataSource.class);
        LOGGER.info("数据源连接监控-加载的数据源【{}】", dataSources);
        if (CollectionUtils.isEmpty(dataSources)) {
            return;
        }
        for (DataSource dataSource : dataSources.values()) {
            LOGGER.info("数据源连接监控-当前数据源类为【{}】", dataSource.getClass());
            boolean match = false;
            Map<String, BaseDataSourceConnectPoolMetrics> metricsMap = SpringUtil.getBeansOfType(BaseDataSourceConnectPoolMetrics.class);
            for (BaseDataSourceConnectPoolMetrics metrics : metricsMap.values()) {
                Class<?> metricsClass = ReflectionKit.getSuperClassGenericType(metrics.getClass(), BaseDataSourceConnectPoolMetrics.class, 0);
                if (metricsClass.equals(dataSource.getClass())) {
                    metrics.metricRegistry(meterRegistry, dataSource, null);
                    match = true;
                }
            }
            if (!match) {
                LOGGER.info("【{}】未匹配到数据-跳过监控", dataSource.getClass());
            }
        }

    }
}
