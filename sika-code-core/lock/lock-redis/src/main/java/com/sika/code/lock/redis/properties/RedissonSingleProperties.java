package com.sika.code.lock.redis.properties;

import com.sika.code.lock.redis.util.RedissonUtil;
import lombok.Data;

/**
 * <p>
 * 单节点属性
 * </p>
 *
 * @author daiqi
 * @date 2019/7/28 12:56
 */
@Data
public class RedissonSingleProperties {
    private String address;

    public String getAddress() {
        return RedissonUtil.buildAddress(this.address);
    }
}