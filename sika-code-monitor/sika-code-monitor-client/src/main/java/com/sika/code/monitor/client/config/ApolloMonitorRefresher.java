package com.sika.code.monitor.client.config;

import cn.hutool.core.util.StrUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.google.common.collect.Maps;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * ApolloMonitorRefresher
 *
 * @author : daiqi
 * @date : 2023-11-15
 */
public class ApolloMonitorRefresher extends AbstractConfigMonitorRefresh {

    @Value("${management.metrics.sika.apollo.namespace:application.yaml}")
    private String namespace;

    public ApolloMonitorRefresher(MeterRegistry meterRegistry, InvokeTimedMetrics invokeTimedMetrics) {
        super(meterRegistry, invokeTimedMetrics);
    }

    public void initRegisterListener() {
        Config config = ConfigService.getConfig(namespace);
        String suffix = StrUtil.subAfter(namespace, ".", true);
        String prefix = StrUtil.subPre(namespace, namespace.lastIndexOf("."));
        logger.info("Apollo配置中心监刷新的namespace为：{}", namespace);
        config.addChangeListener(changeEvent -> {
            Map<String, Object> changeMap = Maps.newHashMap();
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                changeMap.put(change.getPropertyName(), change.getNewValue());
            }
            ConfigFileFormat configFileFormat = ConfigFileFormat.fromString(suffix);
            ConfigFile configFile = ConfigService.getConfigFile(prefix, configFileFormat);

            dynamicRefreshMonitorConfig(configFile.getContent(), changeMap, suffix);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterListener();
    }

}
