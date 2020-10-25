package com.sika.code.standard.footer.demo.common.type.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 单位类型
 *
 * @author daiqi
 * @create 2019-05-27 17:28
 */
@Getter
@AllArgsConstructor
public enum UnitType implements TypeEnumInf {
    /**
     * 单位类型 --- 忽略 --- 0
     */
    IGNORE(0, "忽略"),
    /**
     * 单位类型 --- 项 --- 1
     */
    ITEM(1, "项"),
    /**
     * 单位类型 --- 月 --- 2
     */
    MONTH(2, "月"),
    /**
     * 单位类型 --- 人 --- 3
     */
    PEOPLE(3, "人"),
    /**
     * 单位类型 --- 万 --- 4
     */
    TEN_THOUSAND(4, "万"),
    /**
     * 单位类型 --- 岁 --- 5
     */
    YEAR(5, "岁"),
    ;
    private Integer type;
    private String desc;
}
