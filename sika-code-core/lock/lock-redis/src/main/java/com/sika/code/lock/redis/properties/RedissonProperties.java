package com.sika.code.lock.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * redis属性类
 * </p>
 *
 * @author daiqi
 * @date 2019/7/28 12:54
 */
@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonProperties {

    protected static final int DATABASE_DEFAULT = 0;
    protected static final int TIMEOUT_DEFAULT = 3000;

    private int database = DATABASE_DEFAULT;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout = TIMEOUT_DEFAULT;

    private String password;

    private String mode;

    /**
     * 池配置
     */
    private RedissonPoolProperties pool = new RedissonPoolProperties();

    /**
     * 单机信息配置
     */
    private RedissonSingleProperties single;

    /**
     * 集群 信息配置
     */
    private RedissonClusterProperties cluster;

    /**
     * 哨兵配置
     */
    private RedissonSentinelProperties sentinel;
}