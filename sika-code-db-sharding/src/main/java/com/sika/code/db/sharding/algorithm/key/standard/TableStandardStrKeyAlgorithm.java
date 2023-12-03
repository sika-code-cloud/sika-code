package com.sika.code.db.sharding.algorithm.key.standard;

import com.sika.code.db.sharding.algorithm.value.bo.TableValueAlgorithmBO;
import com.sika.code.db.sharding.constant.AlgorithmNameConstants;
import com.sika.code.db.sharding.algorithm.key.BaseTableShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * Hash取模后，不命中目标数据节点再次半数取模的分片算法
 *
 * @author daiqi
 * @date 2023/1/18
 */
public class TableStandardStrKeyAlgorithm extends BaseTableShardingAlgorithm
        implements StandardShardingAlgorithm<Comparable<?>> {

    /**
     * Sharding.
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @return sharding result for table name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<?>> shardingValue) {
        TableValueAlgorithmBO valueAlgorithmBO = new TableValueAlgorithmBO()
                .setShardingAlgorithm(this)
                .setPreciseShardingValue(shardingValue)
                .setAvailableTargetNames(availableTargetNames);
        String target =
                super.doSharding(valueAlgorithmBO);
        if (target != null) {
            return target;
        }
        throw new RuntimeException("配置有误，路由不到数据源," + availableTargetNames + "，" + shardingValue);
    }

    /**
     * 区间查询，不支持
     *
     * @param availableTargetNames available data sources
     * @param rangeShardingValue   sharding value
     * @return sharding result for data source
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         RangeShardingValue<Comparable<?>> rangeShardingValue) {
        throw new RuntimeException("不支持Range查询," + availableTargetNames + "，" + rangeShardingValue);
    }

    @Override
    public String getType() {
        return AlgorithmNameConstants.TWICE_HASH_MOD;
    }

    @Override
    public Properties getProps() {
        return this.props;
    }

    @Override
    public void init(Properties properties) {
        super.baseInit(properties);
    }
}
