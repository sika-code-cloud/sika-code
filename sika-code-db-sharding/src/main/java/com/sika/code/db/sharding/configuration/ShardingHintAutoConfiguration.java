package com.sika.code.db.sharding.configuration;

import com.sika.code.db.sharding.plugin.ShardingHintPlusPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@ConditionalOnClass(Interceptor.class)
public class ShardingHintAutoConfiguration {

    @Bean
    public ShardingHintPlusPlugin shardingHintPlusPlugin() {
        return new ShardingHintPlusPlugin();
    }

}
