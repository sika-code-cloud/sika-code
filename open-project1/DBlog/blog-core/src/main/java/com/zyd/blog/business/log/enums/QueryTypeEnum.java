package com.zyd.blog.business.log.enums;

import cn.hutool.core.date.DateField;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询类型枚举
 *
 * @author daiqi
 * @create 2020-09-22 23:29
 */
@AllArgsConstructor
@Getter
public enum QueryTypeEnum {
    /**
     * 查询类型枚举
     */
    DAY(1, DateField.HOUR_OF_DAY, "按天查询"),
    WEEK(2, DateField.DAY_OF_YEAR, "按周查询"),
    MONTH(3, DateField.DAY_OF_YEAR, "按月查询"),
    YEAR(4, DateField.MONTH, "按年查询");
    private final Integer type;
    private final DateField dateField;
    private final String desc;

    public static boolean isDay(QueryTypeEnum queryTypeEnum) {
        return DAY.equals(queryTypeEnum);
    }

    public static boolean isWeek(QueryTypeEnum queryTypeEnum) {
        return WEEK.equals(queryTypeEnum);
    }

    public static boolean isMonth(QueryTypeEnum queryTypeEnum) {
        return MONTH.equals(queryTypeEnum);
    }

    public static boolean isYear(QueryTypeEnum queryTypeEnum) {
        return YEAR.equals(queryTypeEnum);
    }
}
