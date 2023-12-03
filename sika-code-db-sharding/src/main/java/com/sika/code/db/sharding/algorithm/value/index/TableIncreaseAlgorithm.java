package com.sika.code.db.sharding.algorithm.value.index;

import com.sika.code.db.sharding.algorithm.value.bo.DataBaseValueAlgorithmBO;
import com.sika.code.db.sharding.utils.ShardingUtils;

/**
 * <p>
 * 表索引递增算法 如库10 1000表
 * 库0-表【0-99】
 * 库1-表【100-199】
 * .。。
 * 库9-表【900-999】
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/2 17:28
 */
public class TableIncreaseAlgorithm extends BaseIndexAlgorithm {
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
