package com.sika.code.standard.footer.demo.common.type.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 比较类型
 *
 * @author daiqi
 * @create 2019-05-21 17:36
 */
@Getter
@AllArgsConstructor
public enum CompareType implements TypeEnumInf {
    /**
     * 比较类型 --- 小于 --- -2
     */
    LESS_THAN(-2, "小于"),

    /**
     * 比较类型 --- 小于等于 --- -1
     */
    LESS_THAN_EQUAL(-1, "小于等于"),

    /**
     * 比较类型 --- 等于 --- 0
     */
    EQUAL(0, "等于"),

    /**
     * 比较类型 --- 大于等于 --- 1
     */
    GREATER_THAN_EQUAL(1, "大于等于"),

    /**
     * 比较类型 --- 大于 --- 2
     */
    GREATER_THAN(2, "大于"),
    ;
    private Integer type;
    private String desc;
}
