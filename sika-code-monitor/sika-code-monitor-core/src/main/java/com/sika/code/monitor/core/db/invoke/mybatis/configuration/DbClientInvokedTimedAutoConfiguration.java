package com.sika.code.monitor.core.db.invoke.mybatis.configuration;

import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.db.invoke.mybatis.plugin.DbClientInvokedTimedPlugin;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
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
@AutoConfiguration(after = {MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
@ConditionalOnBean(MeterRegistry.class)
@ConditionalOnClass(Interceptor.class)
public class DbClientInvokedTimedAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = MonitorEnableConstant.DB_MYBATIS_INVOKE, havingValue = "true", matchIfMissing = true)
    public DbClientInvokedTimedPlugin dbClientInvokedTimedPlugin(MeterRegistry meterRegistry) {
        DbClientInvokedTimedPlugin invokedTimedPlugin = new DbClientInvokedTimedPlugin();
        invokedTimedPlugin.setMeterRegistry(meterRegistry);
        return invokedTimedPlugin;
    }

}
