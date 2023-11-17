package com.sika.code.monitor.client.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.google.common.collect.Maps;
import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.io.ByteArrayResource;

import java.util.Map;
import java.util.Optional;

/**
 * ApolloMonitorRefresher
 *
 * @author : daiqi
 * @date : 2023-11-15
 */
@AllArgsConstructor
public class ApolloMonitorRefresher extends AbstractConfigMonitorRefresh {

    private final MeterRegistry meterRegistry;
    private final InvokeTimedMetrics invokeTimedMetrics;

    public void initRegisterListener() {
        logger.info("-------------------初始化apollo");
        Config config = ConfigService.getConfig("application1.yaml");
        config.addChangeListener(changeEvent -> {
            Map<String, Object> changeMap = Maps.newHashMap();
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                changeMap.put(change.getPropertyName(), change.getNewValue());
                logger.info(
                        "Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                        change.getPropertyName(), change.getOldValue(),
                        change.getNewValue(), change.getChangeType());
            }
            String namespace = "application1";
            ConfigFileFormat configFileFormat = ConfigFileFormat.fromString("yaml");
            ConfigFile configFile = ConfigService.getConfigFile(namespace, configFileFormat);
            MetricsProperties metricsProperties = getNewMetricsProperties(configFile.getContent(), changeMap);
            invokeTimedMetrics.refreshRegistryAlert(metricsProperties, meterRegistry);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterListener();
    }

}
