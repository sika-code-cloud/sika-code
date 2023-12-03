package com.sika.code.db.sharding.algorithm.value.index;

import com.sika.code.db.sharding.algorithm.value.BaseValueAlgorithm;
import com.sika.code.db.sharding.algorithm.value.bo.DataBaseValueAlgorithmBO;
import com.sika.code.db.sharding.algorithm.value.bo.TableValueAlgorithmBO;
import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.shardingsphere.sharding.algorithm.sharding.ShardingAutoTableAlgorithmUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/3 20:03
 */
public abstract class BaseIndexAlgorithm implements BaseValueAlgorithm {



    @Override
    public String getTableName(TableValueAlgorithmBO valueAlgorithmBO) {
        int index = ShardingUtils.twiceHashMod(valueAlgorithmBO.getPreciseShardingValue().getValue(),
                valueAlgorithmBO.getShardingAlgorithm().getTableModNumber(),
                valueAlgorithmBO.getShardingAlgorithm().getAllTableSequences());

        return ShardingAutoTableAlgorithmUtil.findMatchedTargetName(valueAlgorithmBO.getAvailableTargetNames()
                , String.valueOf(index)
                , valueAlgorithmBO.getPreciseShardingValue().getDataNodeInfo()).orElse(null);
    }
}
