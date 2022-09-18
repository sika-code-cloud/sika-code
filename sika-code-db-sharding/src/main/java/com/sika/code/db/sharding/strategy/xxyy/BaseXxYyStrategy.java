package com.sika.code.db.sharding.strategy.xxyy;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import com.sika.code.db.sharding.strategy.Strategy;
import com.sika.code.db.sharding.util.ShardingUtil;

/**
 * <p>
 * 继承该类的分库分表策略需要为2的倍数
 * </p >
 *
 * @author sikadai
 * @since 2022/9/16 16:29
 */
public abstract class BaseXxYyStrategy implements Strategy {

    @Override
    public String returnDbName(String dbName, Object shardingDbValue) {
        return getDbOrTableName(dbName, shardingDbValue, getDbNum(), false);
    }


    @Override
    public String returnTableName(String tableName, Object shardingTableValue) {
        return getDbOrTableName(tableName, shardingTableValue, getTableNum(), true);
    }

    /**
     * 获取数据库分库的数量
     */
    protected abstract Integer getDbNum();

    /**
     * 获取数据库表分表的数量
     */
    protected abstract Integer getTableNum();

    protected String getDbOrTableName(String name, Object shardingValue, Integer dbOrTableNum, boolean table) {
        /** 如果分库或分表的数量为空或者小于等于0则直接返回 */
        if (dbOrTableNum == null || dbOrTableNum <= 0) {
            return name;
        }
        int dbTemValue = ShardingUtil.getXxYyValue(shardingValue, dbOrTableNum, table);
        return CharSequenceUtil.join(StrPool.UNDERLINE, name, dbTemValue);
    }
}
