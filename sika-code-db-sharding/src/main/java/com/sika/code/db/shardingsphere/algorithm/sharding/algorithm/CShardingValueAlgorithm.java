package com.sika.code.db.shardingsphere.algorithm.sharding.algorithm;

import static com.sika.code.db.shardingsphere.algorithm.constant.AlgorithmNameConstants.C_SHARDING_VALUE_ALGORITHM_NAME;

/**
 * 商户号处理策略类
 *
 * @author daiqi
 */
public class CShardingValueAlgorithm implements ShardingValueAlgorithm {

    @Override
    public String getType() {
        return C_SHARDING_VALUE_ALGORITHM_NAME;
    }

    @Override
    public String parseValue(String shardingValue) {
        // 兼容老数据机构号/商户号为16位长度,截取后10位来进行路由
        return shardingValue.length() > 10 ? shardingValue.substring(shardingValue.length() - 10) : shardingValue;
    }
}
