package com.easy.cloud.mq.common.config;

import com.easy.cloud.common.workspace.properties.WorkspaceProperties;
import com.easy.cloud.mq.common.generator.MqMsgGenerator;
import com.easy.cloud.no.factory.NoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-07-05 20:57
 */
@Configuration
public class MqConfig {

    @Autowired
    private WorkspaceProperties workspaceProperties;

    @Bean
    @ConditionalOnMissingBean
    public MqMsgGenerator mqMsgGenerator() {
        return new MqMsgGenerator()
                .setNoGenerator(NoFactory.createSecondShortOrder(workspaceProperties))
                ;
    }
}
