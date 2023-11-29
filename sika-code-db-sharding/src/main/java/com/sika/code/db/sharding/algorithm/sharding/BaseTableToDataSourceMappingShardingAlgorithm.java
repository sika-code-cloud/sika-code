package com.sika.code.db.sharding.algorithm.sharding;

import com.sika.code.db.sharding.utils.ShardingUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 根据表映射数据源的分片算法
 *
 * @author daiqi
 * @date 2023/8/15
 */
public abstract class BaseTableToDataSourceMappingShardingAlgorithm {
    /**
     * 表库映射
     * <p>key: 表序号</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<Integer, String> tableToDataSourceMap = new HashMap<>();

    /**
     * 热点商户配置
     * <p>key: 热点客户标识</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<String, String> hotKeyToDataSourceMap = new HashMap<>();

    public static String dataSourceName;

    /**
     * 获取表序号的取模数
     */
    protected int tableModNumber = -1;

    /**
     * 所有表序号
     */
    protected List<Integer> allTableSequences = new ArrayList<>();
    protected Properties props;

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    public String doSharding(Collection<String> availableTargetNames, String shardingValue) {
        if (this.hotKeyToDataSourceMap.containsKey(shardingValue)) {
            return this.hotSharding(availableTargetNames, shardingValue);
        }
        return this.tableToDataSourceSharding(availableTargetNames, shardingValue);
    }

    /**
     * 热点商户映射
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    private String hotSharding(Collection<String> availableTargetNames, String shardingValue) {
        String dataSourceName = this.hotKeyToDataSourceMap.get(shardingValue);
        if (availableTargetNames.contains(dataSourceName)) {
            return dataSourceName;
        }
        return null;
    }

    /**
     * 通过表库映射分片
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    private String tableToDataSourceSharding(Collection<String> availableTargetNames, String shardingValue) {
        int tableSequence = ShardingUtils.twiceHashMod(shardingValue, this.tableModNumber, this.allTableSequences);
        String dataSourceName = this.tableToDataSourceMap.get(tableSequence);
        if (availableTargetNames.contains(dataSourceName)) {
            return dataSourceName;
        }
        return null;
    }

    public synchronized void baseInit(Properties properties) {
        this.props = properties;
        this.tableModNumber = ShardingUtils.getTableModNumber(properties);
        this.allTableSequences = ShardingUtils.getAllTableSequences(properties);
        this.tableToDataSourceMap = ShardingUtils.getTableToDatasourceMapping(properties);
        this.hotKeyToDataSourceMap = ShardingUtils.getHotCustomerDatasource(properties);
    }
}
