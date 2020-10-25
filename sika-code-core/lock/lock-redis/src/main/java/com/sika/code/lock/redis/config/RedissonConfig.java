package com.sika.code.lock.redis.config;

import com.sika.code.common.string.util.StringUtil;
import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.redis.distribution.DistributionLockHandlerRedis;
import com.sika.code.lock.redis.properties.RedissonClusterProperties;
import com.sika.code.lock.redis.properties.RedissonProperties;
import com.sika.code.lock.redis.properties.RedissonSentinelProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置类
 *
 * @author daiqi
 * @create 2019-07-28 12:11
 */
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedissonProperties redissonProperties() {
        return new RedissonProperties();
    }

    @Configuration
    @Slf4j
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redisson.mode}'=='single' or '${spring.redisson.mode}'=='cluster' or '${spring.redisson.mode}'=='sentinel'")
    public static class RedissonSingleClientConfiguration {
        @Autowired
        private RedissonProperties redissonProperties;

        /**
         * 单机模式 redisson 客户端
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "single")
        @ConditionalOnMissingBean
        public RedissonClient redissonSingle() {
            Config config = new Config();

            SingleServerConfig serverConfig = config.useSingleServer()
                    .setAddress(redissonProperties.getSingle().getAddress())
                    .setTimeout(redissonProperties.getPool().getConnTimeout())
                    .setConnectionPoolSize(redissonProperties.getPool().getSize())
                    .setConnectionMinimumIdleSize(redissonProperties.getPool().getMinIdle());
            if (StringUtil.isNotBlank(redissonProperties.getPassword())) {
                serverConfig.setPassword(redissonProperties.getPassword());
            }
            return Redisson.create(config);
        }


        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "cluster")
        @ConditionalOnMissingBean
        public RedissonClient redissonCluster() {
            log.info("cluster redissonProperties:{}", redissonProperties.getCluster());

            Config config = new Config();
            RedissonClusterProperties clusterProperties = redissonProperties.getCluster();
            ClusterServersConfig serverConfig = config.useClusterServers()
                    .addNodeAddress(clusterProperties.getNodeAddress())
                    .setScanInterval(clusterProperties.getScanInterval())
                    .setIdleConnectionTimeout(redissonProperties.getPool().getSoTimeout())
                    .setConnectTimeout(redissonProperties.getPool().getConnTimeout())
                    .setFailedSlaveCheckInterval(clusterProperties.getFailedAttempts())
                    .setRetryAttempts(clusterProperties.getRetryAttempts())
                    .setRetryInterval(clusterProperties.getRetryInterval())
                    .setMasterConnectionPoolSize(clusterProperties.getMasterConnectionPoolSize())
                    .setSlaveConnectionPoolSize(clusterProperties.getSlaveConnectionPoolSize())
                    .setTimeout(redissonProperties.getTimeout());
            if (StringUtil.isNotBlank(redissonProperties.getPassword())) {
                serverConfig.setPassword(redissonProperties.getPassword());
            }
            return Redisson.create(config);
        }

        /**
         * 哨兵模式 redisson 客户端
         *
         * @return
         */

        @Bean
        @ConditionalOnProperty(name = "spring.redisson.mode", havingValue = "sentinel")
        @ConditionalOnMissingBean
        public RedissonClient redissonSentinel() {
            log.info("sentinel redissonProperties:{}", redissonProperties.getSentinel());
            Config config = new Config();
            RedissonSentinelProperties sentinelProperties = redissonProperties.getSentinel();
            SentinelServersConfig serverConfig = config.useSentinelServers()
                    .addSentinelAddress(sentinelProperties.getSentinelAddress())
                    .setMasterName(sentinelProperties.getMaster())
                    .setReadMode(ReadMode.SLAVE)
                    .setFailedSlaveCheckInterval(sentinelProperties.getFailMax())
                    .setTimeout(redissonProperties.getTimeout())
                    .setMasterConnectionPoolSize(redissonProperties.getPool().getSize())
                    .setSlaveConnectionPoolSize(redissonProperties.getPool().getSize());

            if (StringUtil.isNotBlank(redissonProperties.getPassword())) {
                serverConfig.setPassword(redissonProperties.getPassword());
            }

            return Redisson.create(config);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public DistributionLockHandler distributionLock(RedissonClient redissonClient) {
        return new DistributionLockHandlerRedis(redissonClient);
    }

}
