package com.sika.code.db.sharding.core.algorithm.key.twicehash;

import com.google.common.collect.Maps;
import com.sika.code.db.sharding.core.utils.ShardingUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 根据表映射数据源的分片算法
 *
 * @author daiqi
 * @date 2023/8/15
 */
public class DataSourceTwiceHashShardingAlgorithm extends BaseShardingAlgorithm {
    /**
     * 表库映射
     * <p>key: 表序号</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<Integer, String> tableToDataSourceMap = Maps.newHashMap();

    /**
     * 热点商户配置
     * <p>key: 热点KEY</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<String, String> hotKeyToDataSourceMap = Maps.newHashMap();

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        Map.Entry<String, Collection<Comparable<?>>> columnAndValue =
            ShardingUtils.getComplexColumnAndValue(complexKeysShardingValue);

        // 调用二次分片算法
        String target =
            doSharding(availableTargetNames, columnAndValue.getValue().iterator().next(), columnAndValue.getKey());
        return Collections.singletonList(target);
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames,
        PreciseShardingValue<Comparable<?>> shardingValue) {
        return doSharding(availableTargetNames, shardingValue.getValue(), shardingValue.getColumnName());
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        RangeShardingValue<Comparable<?>> rangeShardingValue) {
        throw new RuntimeException("不支持Range查询," + availableTargetNames + "，" + rangeShardingValue);
    }

    @Override
    public String getType() {
        return "TWICE_HASH_MOD_DATASOURCE";
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    public String doSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue, String columnName) {
        Comparable<?> shardingValueBuild = buildShardingValue(columnName, shardingValue);
        String shardingValueStr = shardingValueBuild.toString();
        if (this.hotKeyToDataSourceMap.containsKey(shardingValueStr)) {
            return this.hotSharding(availableTargetNames, shardingValueStr);
        }
        String target = this.tableToDataSourceSharding(availableTargetNames, shardingValueStr);
        if (target != null) {
            return target;
        }
        throw new RuntimeException("配置有误，路由不到数据源," + availableTargetNames + "，" + shardingValueStr);
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
        return tableToDataSourceSharding(availableTargetNames, tableSequence);
    }

    protected String tableToDataSourceSharding(Collection<String> availableTargetNames, int tableSequence) {
        String dataSourceName = this.tableToDataSourceMap.get(tableSequence);
        if (availableTargetNames.contains(dataSourceName)) {
            return dataSourceName;
        }
        return null;
    }

    @Override
    public synchronized void baseInit(Properties properties) {
        super.baseInit(properties);
        this.hotKeyToDataSourceMap = ShardingUtils.getHotCustomerDatasource(properties);
        this.tableToDataSourceMap = ShardingUtils.getTableToDatasourceMapping(properties);
    }

    public Map<Integer, String> getTableToDataSourceMap() {
        return tableToDataSourceMap;
    }

    public Map<String, String> getHotKeyToDataSourceMap() {
        return hotKeyToDataSourceMap;
    }

}
