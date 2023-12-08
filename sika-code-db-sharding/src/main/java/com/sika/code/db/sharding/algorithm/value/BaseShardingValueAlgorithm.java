package com.sika.code.db.sharding.algorithm.value;

/**
 * <p>
 * 基础值算法
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/3 11:59
 */
public interface BaseShardingValueAlgorithm {
    /**
     * 自定义分片键值处理shardingValue-Spi
     *
     * @param shardingValue 分片键值
     * @return 解析后的值
     */
    String parseValue(Comparable<?> shardingValue);
}
