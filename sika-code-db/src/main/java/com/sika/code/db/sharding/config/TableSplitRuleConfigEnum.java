package com.sika.code.db.sharding.config;

import com.sika.code.db.sharding.strategy.Strategy;
import com.sika.code.db.sharding.strategy.ThirdStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/7/3 11:43
 */
@Getter
@AllArgsConstructor
public enum TableSplitRuleConfigEnum implements ShardingRuleConfig {
    LF_TEST("lf_test", "test_db", "testName", "testName", ThirdStrategy.class, true),
    LF_TEST_TEMP("lf_test_temp", "test_temp_db", "testNo", "testNo", ThirdStrategy.class, true),
    ;
    private final String tableName;
    private final String dbName;
    private final String dbShardingKey;
    private final String tableShardingKey;
    private final Class<? extends Strategy> StrategyClass;
    private final Boolean sharingFlag;

}