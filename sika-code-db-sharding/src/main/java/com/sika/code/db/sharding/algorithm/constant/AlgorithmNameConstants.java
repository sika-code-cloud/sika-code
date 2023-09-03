package com.sika.code.db.sharding.algorithm.constant;

/**
 * 算法名常量(type)
 *
 * @author daiqi
 * @date 2023/8/18
 */
public final class AlgorithmNameConstants {
    /**
     * 表库映射
     */
    public static final String TABLE_TO_DATASOURCE_MAPPING = "TABLE_TO_DATASOURCE_MAPPING";
    /**
     * 两次hash半数取模
     */
    public static final String TWICE_HASH_MOD = "TWICE_HASH_MOD";


    /**
     * 复合分片键表路由算法
     */
    public static final String TWICE_HASH_COMPLEX_MULTI = "TWICE_HASH_COMPLEX_MULTI";

    /**
     * 复合分片键库路由算法
     */
    public static final String TWICE_HASH_COMPLEX_TO_DATASOURCE_MAPPING = "TWICE_HASH_COMPLEX_TO_DATASOURCE_MAPPING";

    /**
     * 数据处理算法名称
     */
    public static final String C_SHARDING_VALUE_ALGORITHM_NAME = "CUST_ID_SPECIAL_HASH_ALGORITHM";

    /**
     * 数据处理算法名称
     */
    public static final String O_SHARDING_VALUE_ALGORITHM_NAME = "ORDER_NO_SPECIAL_HASH_ALGORITHM";

    private AlgorithmNameConstants() {
    }
}
