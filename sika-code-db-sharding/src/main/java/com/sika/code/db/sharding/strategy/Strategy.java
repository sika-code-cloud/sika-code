package com.sika.code.db.sharding.strategy;

import com.sika.code.db.sharding.util.ShardingUtil;

/**
 * 分表策略服务接口
 *
 * @author sikadai
 */
public interface Strategy {

    /**
     * <p>
     * 返回完整的表名-数据库.表名
     * </p >
     *
     * @param dbName             : 数据库名称
     * @param shardingDbValue    : 分片库的值
     * @param tableName          : 表名
     * @param shardingTableValue : 分片表的值
     * @return java.lang.String
     * @author sikadai
     * @since 2022/7/9 15:07
     */
    default String returnDbTableName(String dbName, Object shardingDbValue, String tableName, Object shardingTableValue) {
        String shardDbName = returnDbName(dbName, shardingDbValue);
        String shardTableName = returnTableName(tableName, shardingTableValue);
        return ShardingUtil.buildShardingDbTableName(dbName, shardDbName, tableName, shardTableName);
    }

    /**
     * <p>
     * 返回分片的库名
     * </p >
     *
     * @param dbName          : 数据库名称
     * @param shardingDbValue : 分片库的值
     * @return java.lang.String
     * @author sikadai
     * @since 2022/7/9 15:07
     */
    String returnDbName(String dbName, Object shardingDbValue);

    /**
     * <p>
     * 返回分片的表名
     * </p >
     *
     * @param tableName          : 表名
     * @param shardingTableValue : 分片表的值
     * @return java.lang.String
     * @author sikadai
     * @since 2022/7/9 15:07
     */
    String returnTableName(String tableName, Object shardingTableValue);

}