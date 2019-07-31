package com.sika.code.lock.properties;

import com.sika.code.basic.constant.PropertiesConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 分布式锁属性类
 *
 * @author daiqi
 * @create 2019-07-30 9:21
 */
@Data
@ConfigurationProperties(prefix = PropertiesConstant.LOCK)
public class DistributionLockProperties {
    /**
     * 默认的lock前缀
     */
    protected static final String LOCK_PREFIX_DEFAULT = "SIKA:CODE:LOCK";
    /**
     * 默认的时间值
     */
    protected static final int TIME_DEFAULT = 5;
    /**
     * 锁前缀
     */
    protected String prefix = LOCK_PREFIX_DEFAULT;
    /**
     * 等待时间
     */
    protected int waitTime = TIME_DEFAULT;

    /**
     * 锁的有效时间
     */
    protected int leaseTime = TIME_DEFAULT;

}
