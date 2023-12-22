package com.sika.sharding.sphere.plus.sql;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.sika.sharding.sphere.plus.GenerateSqlUtil;
import com.sika.sharding.sphere.plus.SqlUtils;

import java.time.LocalDate;

/**
 * BaseGenerateSqlHandler
 *
 * @author : daiqi
 * @date : 2023-12-13
 */
public abstract class BaseGenerateSqlHandler {
    protected static final String DB_INDEX_KEY = "dbidx";
    protected static final String TABLE_INDEX_KEY = "tableidx";

    public abstract void generate(GenerateSqlUtil.GenerateSQLParam generateSQLParam);
}
