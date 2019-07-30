package com.sika.code.lock.config;

import com.sika.code.lock.aspect.LockAspect;
import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.properties.DistributionProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式锁配置
 *
 * @author daiqi
 * @create 2019-07-30 10:39
 */

@Configuration
public class LockConfig {


    @Bean
    @ConditionalOnMissingBean
    public DistributionProperties distributionProperties() {
        return new DistributionProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public LockAspect lockAspect(DistributionLockHandler distributionLockHandler, DistributionProperties distributionProperties) {
        return new LockAspect(distributionLockHandler, distributionProperties);
    }
}
