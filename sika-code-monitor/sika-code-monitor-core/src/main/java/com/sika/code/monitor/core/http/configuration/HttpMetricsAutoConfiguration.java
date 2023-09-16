package com.sika.code.monitor.core.http.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@AutoConfiguration(after = {CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnClass({OncePerRequestFilter.class})
@ConditionalOnBean({MeterRegistry.class})
public class HttpMetricsAutoConfiguration {

}
