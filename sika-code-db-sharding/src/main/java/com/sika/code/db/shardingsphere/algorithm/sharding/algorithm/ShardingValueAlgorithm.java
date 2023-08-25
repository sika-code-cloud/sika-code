package com.sika.code.db.shardingsphere.algorithm.sharding.algorithm;

import org.apache.shardingsphere.infra.util.spi.type.typed.TypedSPI;

/**
 * 复合分片键值处理策略接口
 *
 * @author daiqi
 */
public interface ShardingValueAlgorithm extends TypedSPI {

    /**
     * 自定义分片键值处理shardingValue-Spi
     *
     * @param shardingValue 分片键值
     * @return 解析后的值
     */
    String parseValue(String shardingValue);
}
