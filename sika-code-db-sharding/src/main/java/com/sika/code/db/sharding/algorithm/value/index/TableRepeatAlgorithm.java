package com.sika.code.db.sharding.algorithm.value.index;

import com.sika.code.db.sharding.algorithm.value.bo.DataBaseValueAlgorithmBO;
import com.sika.code.db.sharding.utils.ShardingUtils;

/**
 * <p>
 * 表锁因重复算法【如10库10表】
 * 库0-表【0-10】
 * 库1-表【0-10】
 * 。。。
 * 库9-表【0-10】
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/2 17:28
 */
public class TableRepeatAlgorithm extends BaseIndexAlgorithm {
    @Override
    public String getDataBaseName(DataBaseValueAlgorithmBO valueAlgorithmBO) {
        int tableSequence = ShardingUtils.twiceHashMod(valueAlgorithmBO.getPreciseShardingValue().getValue(),
                valueAlgorithmBO.getShardingAlgorithm().getTableModNumber(),
                valueAlgorithmBO.getShardingAlgorithm().getAllTableSequences());

        String dataSourceName = valueAlgorithmBO.getShardingAlgorithm().getTableToDataSourceMap().get(tableSequence);
        if (valueAlgorithmBO.getAvailableTargetNames().contains(dataSourceName)) {
            return dataSourceName;
        }
        return null;
    }
}
