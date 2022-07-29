package com.sika.code.db.sharding.config;

import com.sika.code.db.sharding.executor.DefaultShardingExecutor;
import com.sika.code.db.sharding.executor.ShardingExecutor;
import com.sika.code.db.sharding.manager.ShardingStrategyManager;
import com.sika.code.db.sharding.plugin.ShardingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  分片的配置类
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/7/9 15:23
 */
@ConditionalOnProperty(
        prefix = "spring.sharding",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = false
)
@Configuration
public class ShardingConfigure {

    @Bean
    public ShardingStrategyManager shardingConfiguration() {
        return new ShardingStrategyManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public ShardingExecutor shardingExecutor(ShardingStrategyManager shardingStrategyManager) {
        DefaultShardingExecutor shardingExecutor = new DefaultShardingExecutor();
        shardingExecutor.setShardingStrategyManager(shardingStrategyManager);
        return shardingExecutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public ShardingInterceptor shardingInterceptor(ShardingExecutor shardingExecutor) {
        ShardingInterceptor shardingInterceptor = new ShardingInterceptor();
        shardingInterceptor.setShardingExecutor(shardingExecutor);
        return shardingInterceptor;
    }
}
