package com.sika.code.demo.infrastructure.db.common.valuesharding;

import com.sika.code.db.sharding.algorithm.value.BaseShardingValueAlgorithm;

/**
 * 测试分片策略
 *
 * @author daiqi
 */
public class OShardingValueAlgorithm implements BaseShardingValueAlgorithm {

    private static final int MIN_LENGTH = 5;

    @Override
    public String parseValue(Comparable<?> shardingValue) {
        String shardingValueStr = null;
        if (shardingValue instanceof String) {
            shardingValueStr = (String) shardingValue;
        } else {
            return null;
        }
        // 截取单号后十位（商户号）进行路由
        if (shardingValueStr.length() < MIN_LENGTH) {
            throw new RuntimeException( "分片键值非法,单号长度不能小于10");
        }
        return shardingValueStr.substring(0, 10);
    }
}
