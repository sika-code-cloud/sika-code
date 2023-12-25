package com.sika.sharding.sphere.plus.sql;

import java.time.LocalDate;

/**
 * YearMonthGenerateSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public class GenerateYearDaySqlHandler extends BaseGenerateDateSqlHandler {
    @Override
    protected LocalDate nextLocalDate(LocalDate currentLocalDate) {
        return currentLocalDate.plusDays(1);
    }
}
