package com.sika.code.db.sharding.algorithm.sharding.complex;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.db.sharding.algorithm.sharding.BaseTwiceHashModMappingShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.sharding.algorithm.ShardingValueAlgorithm;
import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static com.sika.code.db.sharding.algorithm.constant.AlgorithmNameConstants.TWICE_HASH_COMPLEX_MULTI;

/**
 * 复合分片键表路由实现类
 *
 * @author daiqi
 */
public class TwiceHashModForMultiComplexKeysShardingAlgorithm extends BaseTwiceHashModMappingShardingAlgorithm
    implements ComplexKeysShardingAlgorithm<String> {

    /**
     * 分片键value自定义Spi处理算法
     */
    private Map<String, ShardingValueAlgorithm> shardingValueAlgorithmMap;

    /**
     * 根据复合分片键路由到真实表
     *
     * @param availableTargetNames     真实表名集合
     * @param complexKeysShardingValue 分片键具体的值
     * @return 真实表
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        ComplexKeysShardingValue<String> complexKeysShardingValue) {
        Collection<Comparable<?>> comparables =
            HintManager.getDatabaseShardingValues(complexKeysShardingValue.getLogicTableName());
        if (CollUtil.isNotEmpty(comparables)) {
            Comparable<?> comparable = comparables.stream().iterator().next();
            return Collections.singletonList(
                get(availableTargetNames, complexKeysShardingValue.getLogicTableName(), comparable));
        }

        Map.Entry<String, Collection<String>> columnAndValue =
            ShardingUtils.getComplexColumnAndValue(complexKeysShardingValue);

        // 获取自定义策略类，解析分片键的值
        String parseValue = shardingValueAlgorithmMap.get(columnAndValue.getKey())
            .parseValue(columnAndValue.getValue().iterator().next());
        DataNodeInfo tableDataNode = ShardingUtils.getTableDataNode(availableTargetNames, complexKeysShardingValue);
        // 调用二次分片算法
        String target = super.doSharding(availableTargetNames, parseValue, tableDataNode);
        if (target != null) {
            return Collections.singletonList(target);
        }
        throw new RuntimeException("配置有误，路由不到数据源," + availableTargetNames + "，" + complexKeysShardingValue);
    }

    public String get(Collection<String> availableTargetNames, String logicName, Comparable<?> comparable) {
        for (String s : availableTargetNames) {
            if (s.equalsIgnoreCase(logicName + "_" + comparable)) {
                logger.info("TwiceHashModForHintShardingAlgorithm-hint-[{}]", s);
                return s;
            }
        }
        return null;
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void init(Properties properties) {
        super.baseInit(properties);
        this.shardingValueAlgorithmMap = ShardingUtils.getShardingValueAlgorithm(properties);
    }

    @Override
    public String getType() {
        return TWICE_HASH_COMPLEX_MULTI;
    }
}
