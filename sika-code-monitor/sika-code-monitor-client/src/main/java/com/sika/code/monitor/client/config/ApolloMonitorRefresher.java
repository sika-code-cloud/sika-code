package com.sika.code.monitor.client.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;

import java.util.Map;

/**
 * ApolloMonitorRefresher
 *
 * @author : daiqi
 * @date : 2023-11-15
 */
public class ApolloMonitorRefresher extends AbstractConfigMonitorRefresh {
    public void initRegisterListener() {
        logger.info("-------------------初始化apollo");
        Config config = ConfigService.getConfig("application1.yaml");
        config.addChangeListener(changeEvent -> {
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                logger.info(
                        "Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                        change.getPropertyName(), change.getOldValue(),
                        change.getNewValue(), change.getChangeType());
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterListener();
    }

    public MetricsProperties refresh(Map<String, Object> map) {
        ConfigurationPropertySource sources = new MapConfigurationPropertySource(map);
        Binder binder = new Binder(sources);
        return binder.bind(MonitorEnableConstant.METRICS_COMMON_PREFIX, MetricsProperties.class).get();
    }

}
