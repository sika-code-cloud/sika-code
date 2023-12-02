package com.sika.code.db.sharding.algorithm.key.complex;

import com.sika.code.db.sharding.algorithm.value.temp.ShardingValueAlgorithm;
import com.sika.code.db.sharding.algorithm.key.BaseTableShardingAlgorithm;
import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static com.sika.code.db.sharding.constant.AlgorithmNameConstants.TWICE_HASH_COMPLEX_MULTI;

/**
 * 复合分片键表路由实现类
 *
 * @author daiqi
 */
public class TableComplexStrKeyShardingAlgorithm extends BaseTableShardingAlgorithm
    implements ComplexKeysShardingAlgorithm<Comparable<?>> {

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
        ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {

        Map.Entry<String, Collection<Comparable<?>>> columnAndValue =
            ShardingUtils.getComplexColumnAndValue(complexKeysShardingValue);

        // 获取自定义策略类，解析分片键的值
        Comparable<?> parseValue = shardingValueAlgorithmMap.get(columnAndValue.getKey())
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
