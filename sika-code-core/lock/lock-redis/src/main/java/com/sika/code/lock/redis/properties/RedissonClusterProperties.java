package com.sika.code.lock.redis.properties;

import com.sika.code.lock.redis.util.RedissonUtil;
import lombok.Data;

/**
 * <p>
 * 集群属性
 * </p>
 *
 * @author daiqi
 * @date 2019/7/28 12:56
 */
@Data
public class RedissonClusterProperties {
    /**
     * 集群状态扫描间隔时间
     */
    private static final int SCAN_INTERVAL_DEFAULT = 1000;
    /**
     * 从节点连接池大小
     */
    private static final int SLAVE_CONNECTION_POOL_SIZE_DEFAULT = 64;
    /**
     * 主节点连接池大小
     */
    private static final int MASTER_CONNECTION_POOL_SIZE_DEFAULT = 64;
    /**
     * 命令失败重试次数
     */
    private static final int RETRY_ATTEMPTS_DEFAULT = 3;
    /**
     * 命令重试发送时间间隔
     */
    private static final int RETRY_INTERVAL_DEFAULT = 1500;
    /**
     * 执行失败最大次数默认值
     */
    private static final int FAILED_ATTEMPTS_DEFAULT = 3;
    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    private int scanInterval = SCAN_INTERVAL_DEFAULT;
    /**
     * 集群节点
     */
    private String nodes;
    /**
     * 默认值： SLAVE（只在从服务节点里读取）设置读取操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里读取。
     * MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取
     */
    private String readMode;
    /**
     * （从节点连接池大小） 默认值：64
     */
    private int slaveConnectionPoolSize = SLAVE_CONNECTION_POOL_SIZE_DEFAULT;
    /**
     * 主节点连接池大小）默认值：64
     */
    private int masterConnectionPoolSize = MASTER_CONNECTION_POOL_SIZE_DEFAULT;
    /**
     * （命令失败重试次数） 默认值：3
     */
    private int retryAttempts = RETRY_ATTEMPTS_DEFAULT;
    /**
     * 命令重试发送时间间隔，单位：毫秒 默认值：1500
     */
    private int retryInterval = RETRY_INTERVAL_DEFAULT;
    /**
     * 执行失败最大次数默认值：3
     */
    private int failedAttempts = FAILED_ATTEMPTS_DEFAULT;

    /**
     * <p>
     * 获取地址数组
     * </p>
     *
     * @return java.lang.String[]
     * @author daiqi
     * @date 2019/7/28 13:37
     */
    public String[] getNodeAddress() {
        return RedissonUtil.convertToAddressArr(this.nodes);
    }
}