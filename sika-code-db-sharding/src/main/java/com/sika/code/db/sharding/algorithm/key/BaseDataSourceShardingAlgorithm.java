package com.sika.code.db.sharding.algorithm.key;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.db.sharding.algorithm.value.BaseValueAlgorithm;
import com.sika.code.db.sharding.algorithm.value.bo.DataBaseValueAlgorithmBO;
import com.sika.code.db.sharding.utils.ShardingUtils;
import org.apache.shardingsphere.infra.datanode.DataNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 根据表映射数据源的分片算法
 *
 * @author daiqi
 * @date 2023/8/15
 */
public abstract class BaseDataSourceShardingAlgorithm {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 表库映射
     * <p>key: 表序号</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<Integer, String> tableToDataSourceMap = Maps.newHashMap();
    protected Map<String, DataNode> dataSourceNameToNodeMap = Maps.newHashMap();

    /**
     * 热点商户配置
     * <p>key: 热点KEY</p>
     * <p>value: 数据源名称</p>
     */
    protected Map<Comparable<?>, String> hotKeyToDataSourceMap = Maps.newHashMap();

    /**
     * 获取表序号的取模数
     */
    protected int tableModNumber = -1;

    protected Map<String, String> columnToValueAlgorithmMap = Maps.newHashMap();

    /**
     * 所有表序号
     */
    protected List<Integer> allTableSequences = new ArrayList<>();
    protected Properties props;

    /**
     * Sharding.
     *
     * @param algorithmBO available data sources
     * @return sharding result for data source
     */
    public String doSharding(DataBaseValueAlgorithmBO algorithmBO) {
        String classNameStr = columnToValueAlgorithmMap.get(algorithmBO.getPreciseShardingValue().getColumnName());
        if (StrUtil.isBlank(classNameStr)) {
            return null;
        }
        BaseValueAlgorithm valueAlgorithm = ShardingUtils.getValueAlgorithm(classNameStr);
        if (valueAlgorithm == null) {
            return null;
        }
        return valueAlgorithm.getDataBaseName(algorithmBO);
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources
     * @param shardingValue        sharding value
     * @return sharding result for data source
     */
    public String doSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue) {
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
    private String hotSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue) {
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
    private String tableToDataSourceSharding(Collection<String> availableTargetNames, Comparable<?> shardingValue) {
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

    public synchronized void baseInit(Properties properties) {
        this.props = properties;
        this.tableModNumber = ShardingUtils.getTableModNumber(properties);
        this.allTableSequences = ShardingUtils.getAllTableSequences(properties);
        this.tableToDataSourceMap = ShardingUtils.getTableToDatasourceMapping(properties);
        this.hotKeyToDataSourceMap = ShardingUtils.getHotCustomerDatasource(properties);
        this.columnToValueAlgorithmMap = ShardingUtils.getValueAlgorithm(properties);
        this.dataSourceNameToNodeMap = ShardingUtils.getDataNodeMap(properties);
    }

    public Map<Integer, String> getTableToDataSourceMap() {
        return tableToDataSourceMap;
    }

    public Map<Comparable<?>, String> getHotKeyToDataSourceMap() {
        return hotKeyToDataSourceMap;
    }

    public int getTableModNumber() {
        return tableModNumber;
    }

    public Map<String, String> getColumnToValueAlgorithmMap() {
        return columnToValueAlgorithmMap;
    }

    public List<Integer> getAllTableSequences() {
        return allTableSequences;
    }

    public Properties getProps() {
        return props;
    }
}
