package com.easy.cloud.result.config;

import com.easy.cloud.common.workspace.properties.WorkspaceProperties;
import com.easy.cloud.no.factory.NoFactory;
import com.easy.cloud.result.generator.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-07-03 21:29
 */
@Configuration
public class ResultConfig {
    @Autowired
    private WorkspaceProperties workspaceProperties;

    @Bean
    @ConditionalOnMissingBean
    public ResultGenerator resultGenerator() {
        return new ResultGenerator()
                .setNoGenerator(NoFactory.createSecondShortOrder(workspaceProperties))
                .setExceptionNoGenerator(NoFactory.createSecondShortOrder(workspaceProperties))
                ;
    }
}
