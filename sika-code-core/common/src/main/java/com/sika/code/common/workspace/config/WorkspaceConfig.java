package com.sika.code.common.workspace.config;

import com.sika.code.common.workspace.properties.WorkspaceProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 工作空间配置
 * </p>
 *
 * @author daiqi
 * @date 2018/11/28 19:08
 */
@Configuration
public class WorkspaceConfig {
    /**
     * 工作空间配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public WorkspaceProperties workspaceProperties() {
        return new WorkspaceProperties();
    }

}
