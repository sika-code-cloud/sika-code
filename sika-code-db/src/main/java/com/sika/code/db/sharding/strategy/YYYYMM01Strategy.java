package com.sika.code.db.sharding.strategy;


import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ：sikadai
 * @description：按月分表策略
 * @date ：2019/6/13 10:29
 */
@Component
public class YYYYMM01Strategy implements Strategy {

    @Override
    public String returnDbName(String dbName, Object shardingDbValue) {
        return StrUtil.join(StrPool.UNDERLINE, dbName, ((LocalDate) shardingDbValue).format(DateTimeFormatter.ofPattern("yyyy")));
    }

    @Override
    public String returnTableName(String tableName, Object shardingTableValue) {
        return StrUtil.join(StrPool.UNDERLINE, tableName, ((LocalDate) shardingTableValue).format(DateTimeFormatter.ofPattern("MMdd")));
    }
}