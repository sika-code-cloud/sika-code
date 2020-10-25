package com.sika.code.common.log.config;

import com.sika.code.basic.constant.PropertyConstant;
import com.sika.code.common.log.aspect.ControllerLogAspect;
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
    @ConditionalOnProperty(value = PropertyConstant.LOG_CONTROLLER_FIRE, havingValue = "true")
    public ControllerLogAspect controllerLogAspect() {
        return new ControllerLogAspect();
    }
}
