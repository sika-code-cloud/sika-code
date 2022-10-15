package com.sika.code.db.sharding.executor;

import com.sika.code.core.util.EnumUtil;
import com.sika.code.db.sharding.annotation.ShardingRule;
import com.sika.code.db.sharding.config.ShardingRuleConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  默认-分片执行器
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/7/9 15:42
 */
@Slf4j
public class DefaultShardingExecutor extends ShardingExecutor {

    @Override
    protected ShardingRuleConfig getShardingRuleConfig(ShardingRule shardingRule) {
        return EnumUtil.find(shardingRule.shardingRuleConfigClass(), RULE_CONFIG_ENUM_NAME, shardingRule.ruleName());
    }
}
