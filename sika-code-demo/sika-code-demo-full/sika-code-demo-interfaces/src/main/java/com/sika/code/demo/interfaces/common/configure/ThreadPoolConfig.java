package com.sika.code.demo.interfaces.common.configure;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exemplars.ExemplarSampler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {
    @Bean
    SimpleMeterRegistry simpleMeterRegistry() {
        return new SimpleMeterRegistry();
    }
//    @Bean
    CustomerPromethuesMeterRegistry customerPromethuesMeterRegistry(PrometheusConfig prometheusConfig,
        CollectorRegistry collectorRegistry, Clock clock, ObjectProvider<ExemplarSampler> exemplarSamplerProvider) {
        return new CustomerPromethuesMeterRegistry(prometheusConfig, collectorRegistry, clock, exemplarSamplerProvider.getIfAvailable());
    }
//    @Bean
    CustomerLoggingMeterRegistry customerLoggingMeterRegistry(SimpleMeterRegistry simpleMeterRegistry, CustomerPromethuesMeterRegistry customerPromethuesMeterRegistry) {
        CustomerLoggingMeterRegistry registry = new CustomerLoggingMeterRegistry();
        registry.setSimpleMeterRegistry(simpleMeterRegistry);
        registry.setCustomerPromethuesMeterRegistry(customerPromethuesMeterRegistry);
        return registry;
    }

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageConsumeDynamicExecutor() {
        String threadPoolId = "message-consume";
        ThreadPoolExecutor messageConsumeDynamicExecutor =
            ThreadPoolBuilder.builder().threadFactory(threadPoolId).threadPoolId(threadPoolId).dynamicPool().build();
        return messageConsumeDynamicExecutor;
    }

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageProduceDynamicExecutor() {
        String threadPoolId = "message-produce";
        ThreadPoolExecutor messageProduceDynamicExecutor =
            ThreadPoolBuilder.builder().threadFactory(threadPoolId).threadPoolId(threadPoolId).dynamicPool().build();
        return messageProduceDynamicExecutor;
    }

}