package com.sika.code.monitor.core.db.connector.shardingsphere.metrics;

import com.sika.code.monitor.core.db.common.metrics.BaseDataSourceConnectorPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * BaseShardingSphereConnectorPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Data
public abstract class BaseShardingSphereConnectorPoolMetrics<T extends DataSource> extends
    BaseDataSourceConnectorPoolMetrics<ShardingSphereDataSource> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseShardingSphereConnectorPoolMetrics.class);
    private ShardingSphereAutoConfiguration shardingSphereAutoConfiguration;
    private BaseDataSourceConnectorPoolMetrics<T> sourceConnectPoolMetrics;

    /**
     * 注册shardingSphere数据源
     */
    protected void registryShardingSphereDataSource(MeterRegistry meterRegistry) {
        LOGGER.info("数据源连接监控-注册SharedingSphere数据源");
        Field declaredField;
        try {
            declaredField = ShardingSphereAutoConfiguration.class.getDeclaredField("dataSourceMap");
            // 需要获取私有字段需要 设置accessible属性为true 不然会出现 IllegalAccessException 异常
            declaredField.setAccessible(Boolean.TRUE);
            // 使用当前拿到的字段对象 获取person对象中该字段的值 getDeclaredField这里获取的是私有属性name的值
            Map<String, T> dataSourceMap =
                    (Map<String, T>) declaredField.get(shardingSphereAutoConfiguration);

            for (Map.Entry<String, T> entry : dataSourceMap.entrySet()) {
                sourceConnectPoolMetrics.metricRegistry(meterRegistry, entry.getValue(), entry.getKey());
            }
        } catch (Exception e) {
            LOGGER.error("监听shardingSphereDataSource失败", e);
        }
    }

    @Override
    public void metricRegistry(MeterRegistry meterRegistry, ShardingSphereDataSource dataSource, String poolName) {
        registryShardingSphereDataSource(meterRegistry);
    }
}
