package com.sika.code.db.sharding.algorithm.value.temp;

import static com.sika.code.db.sharding.constant.AlgorithmNameConstants.C_SHARDING_VALUE_ALGORITHM_NAME;

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
    public String parseValue(Comparable<?> shardingValue) {
        String shardingValueStr = null;
        if (shardingValue instanceof String) {
            shardingValueStr = (String) shardingValue;
        } else {
            return null;
        }
        // 兼容老数据机构号/商户号为16位长度,截取后10位来进行路由
        return shardingValueStr.length() > 10 ? shardingValueStr.substring(shardingValueStr.length() - 10) : shardingValueStr;
    }
}
