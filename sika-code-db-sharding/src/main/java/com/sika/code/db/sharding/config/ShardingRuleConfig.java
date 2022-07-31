package com.sika.code.db.sharding.config;

import com.sika.code.db.sharding.strategy.Strategy;

/**
 * <p>
 * 分片规则配置-可以基于枚举+配置
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/7/3 11:38
 */
public interface ShardingRuleConfig {

    /**
     * 指定数据库的格式
     */
    String getDbName();

    /**
     * 指定表名的格式
     */
    String getTableName();

    /**
     * 获取数据库分片的KEY
     */
    String getDbShardingKey();

    /**
     * 获取表分片的KEY
     */
    String getTableShardingKey();

    /**  
     * <p>
     * 分片策略的Class
     * </p >
     * @author sikadai
     * @since 2022/7/9 15:10
     * @return java.lang.Class<? extends com.ak.cloud.standard.frame.db.sharing.strategy.Strategy>
     */  
    Class<? extends Strategy> getStrategyClass();

    /**  
     * <p>
     * 分片标志 - 配置为false则代表不分片
     * </p >
     * @author sikadai
     * @since 2022/7/9 15:10
     * @return java.lang.Boolean
     */  
    Boolean getSharingFlag();
}
