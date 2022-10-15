package com.sika.code.db.sharding.strategy.year;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import com.sika.code.db.sharding.strategy.Strategy;
import com.sika.code.db.sharding.util.ShardingUtil;

/**
 * <p>
 * 基础按照年xx分库分表策略
 * </p >
 *
 * @author sikadai
 * @since 2022/9/16 17:35
 */
public abstract class BaseYearXxStrategy implements Strategy {
    protected static final String YYYY = "yyyy";
    protected static final String YYYY_MM = "yyyyMM";
    protected static final String YYYY_MM_DD = "yyyyMMdd";

    @Override
    public String returnDbName(String dbName, Object shardingDbValue) {
        String dbTemValue = ShardingUtil.formatDate(shardingDbValue, YYYY);
        return CharSequenceUtil.join(StrPool.UNDERLINE, dbName, dbTemValue);
    }

}
