package com.sika.code.result.config;

import com.sika.code.common.workspace.properties.WorkspaceProperties;
import com.sika.code.no.factory.NoFactory;
import com.sika.code.result.generator.ResultGenerator;
import com.sika.code.result.properties.ResultNoConfigProperties;
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
    @Autowired
    private ResultNoConfigProperties resultNoConfigProperties;

    @Bean
    @ConditionalOnMissingBean
    public ResultGenerator resultGenerator() {
        return new ResultGenerator()
                .setNoGenerator(NoFactory.createSecondShortOrder(workspaceProperties))
                .setExceptionNoGenerator(NoFactory.createSecondShortOrder(workspaceProperties))
                .setNoConfig(new ResultNoConfig(resultNoConfigProperties.getNoPrefix(), resultNoConfigProperties.getSuffixCount()))
                .setExceptionNoConfig(new ResultNoConfig(resultNoConfigProperties.getExceptionNoPrefix(), resultNoConfigProperties.getSuffixCount()))
                ;
    }
}
