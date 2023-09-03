package com.sika.code.db.sharding.algorithm.sharding.algorithm;

import static com.sika.code.db.sharding.algorithm.constant.AlgorithmNameConstants.O_SHARDING_VALUE_ALGORITHM_NAME;

/**
 * 单号处理策略类
 *
 * @author daiqi
 */
public class OShardingValueAlgorithm implements ShardingValueAlgorithm {

    private static final int ORDER_NO_MIN_LENGTH = 10;

    @Override
    public String getType() {
        return O_SHARDING_VALUE_ALGORITHM_NAME;
    }

    @Override
    public String parseValue(String shardingValue) {
        // 截取单号后十位（商户号）进行路由
        if (shardingValue.length() < ORDER_NO_MIN_LENGTH) {
            throw new RuntimeException( "分片键值非法,单号长度不能小于10");
        }
        return shardingValue.substring(0, 10);
    }
}
