package com.sika.code.monitor.client.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.core.configcenter.parser.ConfigParserHandler;
import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;

import java.util.Map;
import java.util.Optional;

/**
 * @author daiqi
 * @create 2023-10-20 22:50
 */
public class AbstractConfigMonitorRefresh implements InitializingBean, ApplicationRunner {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final MeterRegistry meterRegistry;
    protected final InvokeTimedMetrics invokeTimedMetrics;
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public AbstractConfigMonitorRefresh(MeterRegistry meterRegistry, InvokeTimedMetrics invokeTimedMetrics) {
        this.meterRegistry = meterRegistry;
        this.invokeTimedMetrics = invokeTimedMetrics;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    protected void dynamicRefreshMonitorConfig(String configContent, Map<String, Object> newValueChangeMap, String fileType) {
        try {
            Map<Object, Object> configInfo = ConfigParserHandler.getInstance().parseConfig(configContent, fileType);
            if (CollectionUtil.isNotEmpty(newValueChangeMap)) {
                Optional.ofNullable(configInfo).ifPresent(each -> each.putAll(newValueChangeMap));
            }

            ConfigurationPropertySource sources = new MapConfigurationPropertySource(configInfo);
            MetricsProperties oldPro = SpringUtil.getBean(MetricsProperties.class);
            Binder binder = new Binder(sources);
            MetricsProperties newMetricsProperties = binder.bind(MonitorEnableConstant.METRICS_COMMON_PREFIX, Bindable.ofInstance(oldPro)).get();
            invokeTimedMetrics.refreshRegistryAlert(newMetricsProperties, meterRegistry);
        } catch (Exception e) {
            logger.error("Sika monitor config dynamic refresh failed.", e);
        }
    }
}
