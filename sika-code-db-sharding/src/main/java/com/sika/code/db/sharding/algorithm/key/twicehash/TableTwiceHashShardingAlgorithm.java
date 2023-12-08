package com.sika.code.db.sharding.algorithm.key.twicehash;

import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.algorithm.sharding.ShardingAutoTableAlgorithmUtil;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Hash取模后，不命中目标数据节点再次半数取模的分片算法
 *
 * @author daiqi
 * @date 2023/1/18
 */
public class TableTwiceHashShardingAlgorithm extends BaseShardingAlgorithm {
    /**
     * 热点商户列表
     */
    protected List<String> hotKeyList = new ArrayList<>();

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {

        Map.Entry<String, Collection<Comparable<?>>> columnAndValue =
            ShardingUtils.getComplexColumnAndValue(complexKeysShardingValue);

        // 获取自定义策略类，解析分片键的值
        DataNodeInfo tableDataNode = ShardingUtils.getTableDataNode(availableTargetNames, complexKeysShardingValue);
        // 调用二次分片算法
        String target = doSharding(availableTargetNames, columnAndValue.getValue().iterator().next(), tableDataNode,
            columnAndValue.getKey());
        return Collections.singletonList(target);
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames,
        PreciseShardingValue<Comparable<?>> shardingValue) {
        return doSharding(availableTargetNames, shardingValue.getValue(), shardingValue.getDataNodeInfo(),
            shardingValue.getColumnName());
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        RangeShardingValue<Comparable<?>> shardingValue) {
        throw new RuntimeException("不支持Range查询," + availableTargetNames + "，" + shardingValue);
    }

    @Override
    public String getType() {
        return "TWICE_HASH_MOD_TABLE";
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @param dataNodeInfo         数据节点信息
     * @return sharding result for table name
     */

    public String doSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue,
        DataNodeInfo dataNodeInfo, String columnName) {
        Comparable<?> sharingValueBuild = buildShardingValue(columnName, shardingValue);
        String shardingValueStr = sharingValueBuild.toString();
        if (this.hotKeyList.contains(shardingValueStr)) {
            return this.hotSharding(availableTargetNames, shardingValueStr, dataNodeInfo);
        }
        return this.twiceHashModSharding(availableTargetNames, shardingValueStr, dataNodeInfo);
    }

    /**
     * 热点商户映射
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @param dataNodeInfo         数据节点信息
     * @return sharding result for table name
     */
    private String hotSharding(Collection<String> availableTargetNames, String shardingValue,
        DataNodeInfo dataNodeInfo) {
        String targetName = dataNodeInfo.getPrefix() + shardingValue;
        if (availableTargetNames.contains(targetName)) {
            return targetName;
        }
        return null;
    }

    /**
     * 2次hash求余分片
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @param dataNodeInfo         数据节点信息
     * @return sharding result for table name
     */
    private String twiceHashModSharding(Collection<String> availableTargetNames, String shardingValue,
        DataNodeInfo dataNodeInfo) {
        String suffix =
            String.valueOf(ShardingUtils.twiceHashMod(shardingValue, this.tableModNumber, this.allTableSequences));
        return ShardingAutoTableAlgorithmUtil.findMatchedTargetName(availableTargetNames, suffix, dataNodeInfo)
            .orElse(null);
    }

    @Override
    public void baseInit(Properties properties) {
        super.baseInit(properties);
        this.hotKeyList = ShardingUtils.getHotShardingTable(properties);
    }

    public List<String> getHotKeyList() {
        return hotKeyList;
    }
}
