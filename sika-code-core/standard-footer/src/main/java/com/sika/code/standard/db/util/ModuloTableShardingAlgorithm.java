package com.sika.code.standard.db.util;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * TODO 尚未完整
 * <p>
 *  分表策略的基本实现
 * </p>
 *
 * @author daiqi
 * @date 2018/11/29 18:02
 */
public class ModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }


    //    private static Long timeNode1 = 13L;
//
//    /**
//     * select * from t_order where user_id = 11；类似这个意思
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        for (String each : tableNames) {
//            Long currentTime = shardingValue.getValue()>>23;
//            if (currentTime<=timeNode1){
//                if (each.endsWith(shardingValue.getValue() % 2 + "")) {
//                    return each;
//                }
//            }else {
//                if (each.endsWith(shardingValue.getValue() % 2 + "_1")) {
//                    return each;
//                }
//            }
//        }
//        throw new IllegalArgumentException();
//
//    }
//
//    /**
//     * where user_id in (1,23,7)
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        for (Long value : shardingValue.getValues()) {
//            for (String tableName : tableNames) {
//                if (tableName.endsWith(value % 2 + "")) {
//                    result.add(tableName);
//                }
//            }
//        }
//        return result;
//
//    }
//
//    /**
//     * where user_id between(1, 6)
//     *
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
//        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : tableNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//
//    }
}