package com.sika.code.db.sharding.algorithm.value.bo;

import com.sika.code.db.sharding.algorithm.key.BaseTableShardingAlgorithm;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * <p>
 * 值算法BO
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/3 20:26
 */
@Data
@Accessors(chain = true)
public class TableValueAlgorithmBO {
    private PreciseShardingValue<?> preciseShardingValue;
    private Collection<String> availableTargetNames;
    private BaseTableShardingAlgorithm shardingAlgorithm;

}
