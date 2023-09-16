package com.sika.code.monitor.core.rpc.dubbo.configuration;

import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.rpc.dubbo.invoke.DubboInvokeMetrics;
import com.sika.code.monitor.core.rpc.dubbo.threadpool.DubboThreadPoolMetrics;
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
    @ConditionalOnProperty(name = MonitorEnableConstant.RPC_DUBBO_THREAD_POOL, havingValue = "true", matchIfMissing = true)
    DubboThreadPoolMetrics dubboThreadPoolMetrics(MeterRegistry meterRegistry) {
        return new DubboThreadPoolMetrics(meterRegistry);
    }

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.RPC_DUBBO_INVOKE, havingValue = "true", matchIfMissing = true)
    DubboInvokeMetrics dubboInvokeMetrics(MeterRegistry meterRegistry) {
        return new DubboInvokeMetrics(meterRegistry);
    }
}
