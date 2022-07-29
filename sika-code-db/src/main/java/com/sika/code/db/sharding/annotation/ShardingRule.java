package com.sika.code.db.sharding.annotation;



import com.sika.code.db.sharding.config.ShardingRuleConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分表规则注解
 *
 * @author sikadai
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ShardingRule {

    /**
     * 规则配置
     */
    Class<? extends ShardingRuleConfig> shardingRuleConfigClass();

    /**
     * 规则名称
     */
    String ruleName();
}