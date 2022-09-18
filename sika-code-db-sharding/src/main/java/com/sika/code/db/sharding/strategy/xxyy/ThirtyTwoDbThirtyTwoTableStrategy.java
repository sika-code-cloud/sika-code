package com.sika.code.db.sharding.strategy.xxyy;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 32库32表分库分表策略
 * </p >
 *
 * @author sikadai
 * @return
 * @since 2022/9/16 16:18
 */
@Component
public class ThirtyTwoDbThirtyTwoTableStrategy extends BaseXxYyStrategy {
    protected static final Integer THIRD_TWR = 32;
    @Override
    protected Integer getDbNum() {
        return THIRD_TWR;
    }

    @Override
    protected Integer getTableNum() {
        return THIRD_TWR;
    }
}
