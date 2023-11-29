package com.sika.code.db.sharding.algorithm.sharding.standard;

import com.sika.code.db.sharding.algorithm.constant.AlgorithmNameConstants;
import com.sika.code.db.sharding.algorithm.sharding.BaseTableToDataSourceMappingShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * 根据表映射数据源的分片算法
 *
 * @author daiqi
 * @date 2023/8/15
 */
public class TableToDataSourceMappingShardingAlgorithm extends BaseTableToDataSourceMappingShardingAlgorithm
    implements StandardShardingAlgorithm<String> {

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        if (StringUtils.isNotBlank(dataSourceName)) {
            return dataSourceName;
        }
        String target = super.doSharding(availableTargetNames, shardingValue.getValue());
        if (target != null) {
            return target;
        }
        throw new RuntimeException("配置有误，路由不到数据源," + availableTargetNames + "，" + shardingValue);

    }

    @Override
    public String getType() {
        return AlgorithmNameConstants.TABLE_TO_DATASOURCE_MAPPING;
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
        RangeShardingValue<String> rangeShardingValue) {
        throw new RuntimeException("不支持Range查询," + availableTargetNames + "，" + rangeShardingValue);
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public synchronized void init(Properties properties) {
        super.baseInit(properties);
    }
}
