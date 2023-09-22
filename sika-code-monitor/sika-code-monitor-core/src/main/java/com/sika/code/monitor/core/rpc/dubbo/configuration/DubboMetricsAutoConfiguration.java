package com.sika.code.monitor.core.rpc.dubbo.configuration;

import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import com.sika.code.monitor.core.rpc.dubbo.invoke.DubboInvokeMetricsBinder;
import com.sika.code.monitor.core.rpc.dubbo.threadpool.DubboThreadPoolMetricsBinder;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.dubbo.monitor.dubbo.DubboMonitor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnClass({DubboMonitor.class})
@ConditionalOnBean({MeterRegistry.class})
public class DubboMetricsAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.RPC_DUBBO_THREAD_POOL, havingValue = "true",
        matchIfMissing = true)
    DubboThreadPoolMetricsBinder dubboThreadPoolMetrics(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry) {
        return new DubboThreadPoolMetricsBinder(loadMetricsConfigManager, meterRegistry);
    }

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.RPC_DUBBO_INVOKE, havingValue = "true", matchIfMissing = true)
    @ConditionalOnBean(InvokeTimedMetrics.class)
    DubboInvokeMetricsBinder dubboInvokeMetrics(MeterRegistry meterRegistry, InvokeTimedMetrics invokeTimedMetrics) {
        return new DubboInvokeMetricsBinder(meterRegistry, invokeTimedMetrics);
    }
}
