package com.sika.code.db.sharding.algorithm.sharding.hint;

import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.db.sharding.algorithm.constant.AlgorithmNameConstants;
import com.sika.code.db.sharding.algorithm.sharding.BaseTableToDataSourceMappingShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.sharding.complex.TableMultiComplexToDataSourceShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.sharding.standard.TableToDataSourceMappingShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * TableHint
 *
 * @author : daiqi
 * @date : 2023-11-30
 */
public class TableToDataSourceHintShardingAlgorithm extends BaseTableToDataSourceMappingShardingAlgorithm
    implements HintShardingAlgorithm<Integer>, StandardShardingAlgorithm<String>,
    ComplexKeysShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        HintShardingValue<Integer> shardingValue) {
        // 添加分库分表逻辑
        Collection<String> collection1 = new ArrayList<>();
        for (Integer value : shardingValue.getValues()) {
            String dataSourceName = tableToDataSourceSharding(availableTargetNames, value);
            if (dataSourceName != null) {
                collection1.add(dataSourceName);
                logger.info("TableToDataSourceHintShardingAlgorithm-hint-[{}]", dataSourceName);
            }
        }
        return collection1;
    }

    @Override
    public Properties getProps() {
        return this.props;
    }

    @Override
    public void init(Properties properties) {
        super.baseInit(properties);
    }

    @Override
    public String getType() {
        return AlgorithmNameConstants.TWICE_HASH_HINT_TO_DATASOURCE_MAPPING;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        ComplexKeysShardingValue<String> shardingValue) {
        return SpringUtil.getBean(TableMultiComplexToDataSourceShardingAlgorithm.class).doSharding(availableTargetNames, shardingValue);
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        return SpringUtil.getBean(TableToDataSourceMappingShardingAlgorithm.class).doSharding(availableTargetNames, shardingValue);
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        RangeShardingValue<String> shardingValue) {
        return SpringUtil.getBean(TableToDataSourceMappingShardingAlgorithm.class).doSharding(availableTargetNames, shardingValue);
    }
}
