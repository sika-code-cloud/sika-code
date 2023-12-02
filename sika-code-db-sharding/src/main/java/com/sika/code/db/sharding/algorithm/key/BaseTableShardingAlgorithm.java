package com.sika.code.db.sharding.algorithm.key;

import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.algorithm.sharding.ShardingAutoTableAlgorithmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Hash取模后，不命中目标数据节点再次半数取模的分片算法
 *
 * @author daiqi
 * @date 2023/1/18
 */
public class BaseTableShardingAlgorithm {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Properties props;

    /**
     * 热点商户列表
     */
    protected List<String> hotKeyList = new ArrayList<>();

    /**
     * 所有表序号
     */
    protected List<Integer> allTableSequences = new ArrayList<>();

    /**
     * 获取表序号的取模数
     */
    protected int tableModNumber = -1;
    public static String tableName;

    /**
     * Sharding.
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @param dataNodeInfo         数据节点信息
     * @return sharding result for table name
     */

    public String doSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue, DataNodeInfo dataNodeInfo) {
        if (this.hotKeyList.contains(shardingValue)) {
            return this.hotSharding(availableTargetNames, shardingValue, dataNodeInfo);
        }
        return this.twiceHashModSharding(availableTargetNames, shardingValue, dataNodeInfo);
    }

    /**
     * 热点商户映射
     *
     * @param availableTargetNames available table names
     * @param shardingValue        sharding value
     * @param dataNodeInfo         数据节点信息
     * @return sharding result for table name
     */
    private String hotSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue, DataNodeInfo dataNodeInfo) {
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
    private String twiceHashModSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue, DataNodeInfo dataNodeInfo) {
        String suffix = String.valueOf(ShardingUtils.twiceHashMod(shardingValue, this.tableModNumber, this.allTableSequences));
        return ShardingAutoTableAlgorithmUtil.findMatchedTargetName(availableTargetNames, suffix, dataNodeInfo)
                .orElse(null);
    }

    public void baseInit(Properties properties) {
        this.props = properties;
        this.tableModNumber = ShardingUtils.getTableModNumber(properties);
        this.allTableSequences = ShardingUtils.getAllTableSequences(properties);
        this.hotKeyList = ShardingUtils.getHotCustomerTable(properties);
    }
}
