package com.sika.code.db.sharding.strategy;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

/**
 * @author ：sikadai
 * @description：按月分表策略
 * @date ：2019/6/13 10:29
 */
@Component
public class ThirdStrategy implements Strategy {

    @Override
    public String returnDbName(String dbName, Object shardingDbValue) {
        String splitParamValueStr = shardingDbValue.toString();
        String dbTem = CharSequenceUtil.sub(splitParamValueStr, splitParamValueStr.length() - 4, splitParamValueStr.length() - 2);
        int dbTemValue = Integer.parseInt(dbTem) & 31;
        return CharSequenceUtil.join(StrPool.UNDERLINE, dbName, StrUtil.fillBefore(String.valueOf(dbTemValue), '0', 2));
    }

    @Override
    public String returnTableName(String tableName, Object shardingTableValue) {
        String splitParamValueStr = shardingTableValue.toString();
        String tableTem = StrUtil.sub(splitParamValueStr, splitParamValueStr.length() - 2, splitParamValueStr.length());
        int tableTemValue = Integer.parseInt(tableTem) & 31;
        return CharSequenceUtil.join(StrPool.UNDERLINE, tableName, StrUtil.fillBefore(String.valueOf(tableTemValue), '0', 2));
    }
}