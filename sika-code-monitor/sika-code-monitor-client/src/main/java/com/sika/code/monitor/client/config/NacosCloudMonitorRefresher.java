package com.sika.code.monitor.client.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;

@AllArgsConstructor
public class NacosCloudMonitorRefresher extends AbstractConfigMonitorRefresh {
    private static final Logger log = LoggerFactory.getLogger(NacosCloudMonitorRefresher.class);

    private final ConfigService configService;
    private final MeterRegistry meterRegistry;
    private final InvokeTimedMetrics invokeTimedMetrics;

    public void initRegisterListener() throws NacosException {
        MetricsProperties propertiesBeforeUpdate = SpringUtil.getBean(MetricsProperties.class);
        String dataId = propertiesBeforeUpdate.getNacos().getDataId();
        String group = propertiesBeforeUpdate.getNacos().getGroup();
        if (StrUtil.isBlank(dataId) || StrUtil.isBlank(group)) {
            log.error("注册的data或group为空");
            return;
        }
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                try {
                    MetricsProperties propertiesYetUpdate = getNewMetricsProperties(configInfo, null);
                    invokeTimedMetrics.refreshRegistryAlert(propertiesYetUpdate, meterRegistry);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterListener();
    }

}