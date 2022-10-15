package com.sika.code.db.sharding.strategy.xxyy;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 16库*16表分库分表策略
 * </p >
 *
 * @author sikadai
 * @return
 * @since 2022/9/16 16:18
 */
@Component
public class SixteenDbSixteenTableStrategy extends BaseXxYyStrategy {
    private static final Integer SIXTEEN = 16;
    @Override
    protected Integer getDbNum() {
        return SIXTEEN;
    }

    @Override
    protected Integer getTableNum() {
        return SIXTEEN;
    }
}
