package com.easy.cloud.common.log.config;

import com.easy.cloud.common.log.aspect.ControllerLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 日志配置类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:50:39
 */
@Configuration
public class LogConfig {

    @Bean
    @ConditionalOnProperty(value = "easy.cloud.log.controller.fire", havingValue = "true")
    public ControllerLogAspect controllerLogAspect() {
        return new ControllerLogAspect();
    }
}
