package com.sika.code.monitor.core.db.hikari.metrics;

import cn.hutool.extra.spring.SpringUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * DataSourceConnectPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
public class DataSourceConnectPoolMetrics implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConnectPoolMetrics.class);

    private MeterRegistry meterRegistry;

    private ShardingSphereAutoConfiguration shardingSphereAutoConfiguration;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, DataSource> dataSources = SpringUtil.getBeansOfType(DataSource.class);
        LOGGER.info("数据源连接监控-加载的数据源【{}】", dataSources);
        if (CollectionUtils.isEmpty(dataSources)) {
            return;
        }
        for (DataSource dataSource : dataSources.values()) {
            if (dataSource instanceof ShardingSphereDataSource) {
                registryShardingSphereDataSource();
            } else if (dataSource instanceof HikariDataSource) {
                metricRegistry(((HikariDataSource)dataSource).getPoolName(), dataSource);
            } else {
                LOGGER.warn("暂不支持该数据源【{}】", dataSource);
            }
        }

    }

    /**
     * 注册shardingSphere数据源
     */
    private void registryShardingSphereDataSource() {
        LOGGER.info("数据源连接监控-注册SharedingSphere数据源");
        Field declaredField;
        try {
            declaredField = ShardingSphereAutoConfiguration.class.getDeclaredField("dataSourceMap");
            // 需要获取私有字段需要 设置accessible属性为true 不然会出现 IllegalAccessException 异常
            declaredField.setAccessible(Boolean.TRUE);
            // 使用当前拿到的字段对象 获取person对象中该字段的值 getDeclaredField这里获取的是私有属性name的值
            Map<String, DataSource> dataSourceMap =
                (Map<String, DataSource>)declaredField.get(shardingSphereAutoConfiguration);

            for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
                metricRegistry(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            LOGGER.error("监听shardingSphereDataSource失败", e);
        }
    }

    /**
     * 指标注册
     *
     * @param dataSource : 数据源
     */
    private void metricRegistry(String poolName, DataSource dataSource) {
        try {
            LOGGER.info("数据源连接监控-注册数据源-名称【{}】，数据源【{}】", poolName, dataSource);
            if (dataSource instanceof HikariDataSource) {
                // 当已经注册过则无需注册
                if (((HikariDataSource)dataSource).getMetricsTrackerFactory() != null) {
                    return;
                }
                Field pollNameField = HikariConfig.class.getDeclaredField("poolName");
                pollNameField.setAccessible(true);
                pollNameField.set(dataSource, poolName);
                ((HikariDataSource)dataSource).setMetricRegistry(meterRegistry);
            } else {
                LOGGER.warn("不支持的数据源格式【{}】", dataSource);
            }
        } catch (Exception e) {
            LOGGER.error("监听" + poolName + "失败", e);
        }
    }

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void setShardingSphereAutoConfiguration(ShardingSphereAutoConfiguration shardingSphereAutoConfiguration) {
        this.shardingSphereAutoConfiguration = shardingSphereAutoConfiguration;
    }
}
