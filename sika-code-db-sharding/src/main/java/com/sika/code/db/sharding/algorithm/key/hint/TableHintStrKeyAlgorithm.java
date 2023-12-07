package com.sika.code.db.sharding.algorithm.key.hint;

import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.db.sharding.constant.AlgorithmNameConstants;
import com.sika.code.db.sharding.algorithm.key.BaseTableShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.key.complex.TableComplexStrKeyShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.key.standard.TableStandardStrKeyAlgorithm;
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
 * TwiceHashModForHintShardingAlgorithm
 *
 * @author : daiqi
 * @date : 2023-11-30
 */
public class TableHintStrKeyAlgorithm extends BaseTableShardingAlgorithm
        implements HintShardingAlgorithm<Integer>, StandardShardingAlgorithm<Comparable<?>>, ComplexKeysShardingAlgorithm<Comparable<?>> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         HintShardingValue<Integer> shardingValue) {
        // 添加分库分表逻辑
        Collection<String> collection1 = new ArrayList<>();
        for (String s : availableTargetNames) {
            for (Integer value : shardingValue.getValues()) {
                if (s.equalsIgnoreCase(shardingValue.getLogicTableName() + "_" + value)) {
                    collection1.add(s);
                    logger.info("TwiceHashModForHintShardingAlgorithm-hint-[{}]", s);
                }
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
        return AlgorithmNameConstants.TWICE_HASH_HINT;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        return SpringUtil.getBean(TableComplexStrKeyShardingAlgorithm.class)
                .doSharding(availableTargetNames, shardingValue);
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<?>> shardingValue) {
        return SpringUtil.getBean(TableStandardStrKeyAlgorithm.class)
                .doSharding(availableTargetNames, shardingValue);
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         RangeShardingValue<Comparable<?>> shardingValue) {
        return SpringUtil.getBean(TableStandardStrKeyAlgorithm.class)
                .doSharding(availableTargetNames, shardingValue);
    }
}