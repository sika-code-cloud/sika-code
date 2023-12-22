package com.sika.sharding.sphere.plus.sql;

/**
 * YearMonthGenerateSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public class GenerateTableLoopSqlHandler extends BaseGenerateXxYySqlHandler {
    @Override
    protected int getTableStartIndex(int tableNumPerDb, int dbIndex) {
        return 0;
    }

    @Override
    protected int getTableEndIndex(int tableNumPerDb, int dbIndex) {
        return tableNumPerDb - 1;
    }
}
