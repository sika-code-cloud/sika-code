package com.sika.code.informer.config;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.informer.BaseInformer;
import com.sika.code.informer.impl.InformerForLog;
import com.sika.code.informer.properties.InformerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 通知器配置类
 * @author daiqi
 * @create 2019-04-12 11:47
 */
@Configuration
public class InformerConfig {

    @Bean
    @ConditionalOnMissingBean
    public InformerProperties informerProperties() {
        List<BaseInformer> exceptionInformers = CollUtil.newArrayList(new InformerForLog());
        return InformerProperties.builder()
                .exceptionInformers(exceptionInformers)
                .build();
    }
}
