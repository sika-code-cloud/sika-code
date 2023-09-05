package com.sika.code.monitor.core.thread.tomcat.configuration;

import com.sika.code.monitor.core.thread.tomcat.metrics.TomcatThreadPoolMetricsManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * TomcatAutoConfig
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Configuration
@EnableWebMvc
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnClass(value = {WebServerApplicationContext.class, ThreadPoolExecutor.class})
@ConditionalOnBean({MeterRegistry.class})
public class TomcatThreadPoolMetricsAutoConfig {
    @Bean
    TomcatThreadPoolMetricsManager tomcatThreadPoolMetricsManager(MeterRegistry meterRegistry,
        WebServerApplicationContext webServerApplicationContext) {
        return new TomcatThreadPoolMetricsManager(meterRegistry, webServerApplicationContext);
    }
}
