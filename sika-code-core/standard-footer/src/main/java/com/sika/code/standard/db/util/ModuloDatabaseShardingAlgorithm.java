package com.sika.code.standard.db.util;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * <p>
 *     TODO 尚未完整
 * </p>
 * <pre>
 *     分库策略的简单实现
 * </pre>
 * @author daiqi
 * @date 2018/12/4 9:40
 */
public class ModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }


    //public class DemoDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
//
//    @Override
//    public String doEqualSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//
//        for (String each : databaseNames) {
//            if (each.endsWith(Long.parseLong(shardingValue.getValue().toString()) % 2 + "")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Collection<String> doInSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
//        for (Long value : shardingValue.getValues()) {
//            for (String tableName : databaseNames) {
//                if (tableName.endsWith(value % 2 + "")) {
//                    result.add(tableName);
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
//        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
//        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : databaseNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//    }
} 