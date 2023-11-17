package com.sika.code.monitor.client.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.constant.MonitorEnableConstant;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.io.ByteArrayResource;

import java.util.Map;
import java.util.Optional;

/**
 * @author daiqi
 * @create 2023-10-20 22:50
 */
public class AbstractConfigMonitorRefresh implements InitializingBean, ApplicationRunner {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    protected MetricsProperties getNewMetricsProperties(String configInfo, Map<String, Object> newValueHashMap) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ByteArrayResource(configInfo.getBytes()));
        Map<Object, Object> map = yamlPropertiesFactoryBean.getObject();
        if (CollectionUtil.isNotEmpty(newValueHashMap)) {
            Optional.ofNullable(map).ifPresent(each -> each.putAll(newValueHashMap));
        }
        ConfigurationPropertySource sources = new MapConfigurationPropertySource(map);
        Binder binder = new Binder(sources);
        MetricsProperties oldPro = SpringUtil.getBean(MetricsProperties.class);
        return binder.bind(MonitorEnableConstant.METRICS_COMMON_PREFIX,  Bindable.ofInstance(oldPro)).get();
    }
}
