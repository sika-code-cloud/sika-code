package com.sika.code.monitor.client.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

public class NacosCloudMonitorRefresher extends AbstractConfigMonitorRefresh {
    private static final Logger log = LoggerFactory.getLogger(NacosCloudMonitorRefresher.class);

    private final ConfigService configService;

    public NacosCloudMonitorRefresher(MeterRegistry meterRegistry, InvokeTimedMetrics invokeTimedMetrics, ConfigService configService) {
        super(meterRegistry, invokeTimedMetrics);
        this.configService = configService;
    }

    public void initRegisterListener() throws NacosException {
        MetricsProperties propertiesBeforeUpdate = SpringUtil.getBean(MetricsProperties.class);
        String dataId = propertiesBeforeUpdate.getNacos().getDataId();
        String group = propertiesBeforeUpdate.getNacos().getGroup();
        String suffix = StrUtil.subAfter(dataId, ".", true);
        if (StrUtil.isBlank(dataId) || StrUtil.isBlank(group)) {
            log.error("注册的data或group为空");
            return;
        }
        logger.info("Nacos配置中心监刷新的group[{}]，dataId[{}]", group, dataId);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                try {
                    dynamicRefreshMonitorConfig(configInfo, null, suffix);
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