package com.sika.code.demo.sharding.algorithm;


import com.sika.code.db.sharding.core.algorithm.value.BaseShardingValueAlgorithm;

/**
 * @author daiqi
 * @create 2023-12-12 12:49
 */
public class TwiceHashCmpxRNoAlgorithm implements BaseShardingValueAlgorithm {
    @Override
    public String parseValue(Comparable<?> shardingValue) {
        return shardingValue.toString().substring(0,10);
    }
}
