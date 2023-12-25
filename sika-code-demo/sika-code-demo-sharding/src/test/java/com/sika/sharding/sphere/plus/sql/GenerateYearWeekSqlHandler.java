package com.sika.sharding.sphere.plus.sql;

/**
 * YearMonthGenerateSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public class GenerateYearWeekSqlHandler extends BaseGenerateXxYySqlHandler {
    @Override
    protected int getDbTotal(int dbStart, int dbTotal) {
        return dbStart + dbTotal;
    }

    @Override
    protected int getTableStartIndex(int tableNumPerDb, int dbIndex) {
        return 1;
    }

    @Override
    protected int getTableEndIndex(int tableNumPerDb, int dbIndex) {
        return tableNumPerDb;
    }
}
