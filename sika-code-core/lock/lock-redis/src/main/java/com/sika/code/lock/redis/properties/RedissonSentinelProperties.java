package com.sika.code.lock.redis.properties;

import com.sika.code.lock.redis.util.RedissonUtil;
import lombok.Data;

/**
 * <p>
 * 哨兵Properties
 * </p>
 *
 * @author daiqi
 * @date 2019/7/28 12:55
 */
@Data
public class RedissonSentinelProperties {

    private static final int FAIL_MAX_DEFAULT = 2;

    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     * 失败最大的次数
     */
    private int failMax = FAIL_MAX_DEFAULT;


    /**
     * <p>
     * 获取地址数组
     * </p>
     *
     * @return java.lang.String[]
     * @author daiqi
     * @date 2019/7/28 13:37
     */
    public String[] getSentinelAddress() {
        return RedissonUtil.convertToAddressArr(this.nodes);
    }
}