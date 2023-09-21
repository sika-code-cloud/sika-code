package com.sika.code.monitor.core.threadpool.dynamictp.configuration;

import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.dynamictp.manager.DynamicTpThreadPoolMetricsManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.dromara.dynamictp.core.thread.DtpExecutor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DynamicTpThreadPoolAutoConfiguration
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(
    after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class, MeterRegistry.class})
@ConditionalOnClass(DtpExecutor.class)
@ConditionalOnBean({MeterRegistry.class})
public class DynamicTpThreadPoolMetricsAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.THREAD_POOL_DYNAMIC_TP, havingValue = "true",
        matchIfMissing = true)
    public DynamicTpThreadPoolMetricsManager dynamicTpThreadPoolMetricsManager(
        LoadMetricsConfigManager loadMetricsConfigManager, MeterRegistry meterRegistry) {
        return new DynamicTpThreadPoolMetricsManager(loadMetricsConfigManager, meterRegistry);
    }

}
