package com.sika.code.monitor.client.config;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2023-10-21 0:03
 */
@Configuration
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean({MeterRegistry.class})
public class ConfigMonitorRefreshAutoConfiguration {

    @ConditionalOnMissingBean
    @ConditionalOnClass(NacosConfigProperties.class)
    @Bean
    public ConfigService configService() {
        return new NacosConfigManager(SpringUtil.getBean(NacosConfigProperties.class)).getConfigService();
    }

    @Bean
    public NacosCloudMonitorRefresher nacosCloudMonitorRefresher(MeterRegistry meterRegistry,
                                                                 InvokeTimedMetrics invokeTimedMetrics,
                                                                 ConfigService configService) {
        return new NacosCloudMonitorRefresher(configService, meterRegistry, invokeTimedMetrics);
    }
}
