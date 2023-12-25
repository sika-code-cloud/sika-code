package com.sika.code.db.sharding.core.constant;

/**
 * 算法配置属性的key
 *
 * @author daiqi
 * @date 2023/8/18
 */
public final class AlgorithmPropertiesConstants {
    /**
     * 表的模数
     */
    public static final String TABLE_MOD_NUMBER = "table-mod-number";
    /**
     * 表序号取模到表库映射的key的模数
     */
    public static final String DATASOURCE_MOD_NUMBER = "datasource-mod-number";
    /**
     * 所有表序号
     */
    public static final String ALL_TABLE_SEQUENCES = "all-table-sequences";
    /**
     * 表算法的热点商户配置
     */
    public static final String HOT_KEY_FOR_TABLE = "hot-key-table";
    /**
     * 库算法的热点商户配置
     */
    public static final String HOT_KEY_DATASOURCE = "hot-key-datasource";
    /**
     * 表库映射配置
     */
    public static final String TABLE_TO_DATASOURCE_MAPPING = "table-to-datasource-mapping";

    /**
     * 复合分片键特殊处理算法
     */
    public static final String SHARDING_VALUE_ALGORITHM = "sharding-value-algorithms";

    private AlgorithmPropertiesConstants() {
    }
}
