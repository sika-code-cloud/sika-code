package com.sika.code.db.sharding.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * LowerModeEnum
 *
 * @author : daiqi
 * @date : 2023-12-07
 */
@Getter
@AllArgsConstructor
public enum LowerModeEnum {
    ORG(10, "从定义的起始时间计算差值"),
    CURRENT_YEAR_START(20, "从当前日期所在年的其实日期计算差值"),
    ;
    private final Integer type;
    private final String desc;
}
