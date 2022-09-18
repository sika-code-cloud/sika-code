package com.sika.code.db.sharding.strategy.xxyy;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 8库*8表分库分表策略
 * </p >
 *
 * @author sikadai
 * @return
 * @since 2022/9/16 16:18
 */
@Component
public class EightDbEightTableStrategy extends BaseXxYyStrategy {
    private static final Integer EIGHT = 8;
    @Override
    protected Integer getDbNum() {
        return EIGHT;
    }

    @Override
    protected Integer getTableNum() {
        return EIGHT;
    }
}
