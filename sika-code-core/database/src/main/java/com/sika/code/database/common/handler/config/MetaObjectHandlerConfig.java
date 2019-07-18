package com.sika.code.database.common.handler.config;

import com.sika.code.database.common.handler.DefaultMetaObjectHandler;
import com.sika.code.database.common.handler.BaseMetaObjectHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2019-05-13 9:42
 */
@Configuration
public class MetaObjectHandlerConfig {

    /**
     * <p>
     * 助手条件bean
     * </p>
     *
     * @return BaseMetaObjectHandler
     * @author daiqi
     * @date 2019/5/13 9:43
     */
    @ConditionalOnMissingBean
    @Bean
    public BaseMetaObjectHandler metaObjectHandler() {
        return new DefaultMetaObjectHandler();
    }
}
