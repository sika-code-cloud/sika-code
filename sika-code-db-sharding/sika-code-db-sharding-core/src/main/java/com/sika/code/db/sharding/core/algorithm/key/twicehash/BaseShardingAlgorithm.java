package com.sika.code.db.sharding.core.algorithm.key.twicehash;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.db.sharding.core.algorithm.value.BaseShardingValueAlgorithm;
import com.sika.code.db.sharding.core.utils.ShardingUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * BaseShardingAlgorithm
 *
 * @author : daiqi
 * @date : 2023-12-08
 */
public abstract class BaseShardingAlgorithm
    implements StandardShardingAlgorithm<Comparable<?>>, ComplexKeysShardingAlgorithm<Comparable<?>> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Properties props;
    /**
     * 所有表序号
     */
    protected List<Integer> allTableSequences = new ArrayList<>();
    /**
     * 分片列表与对应的列与分片的算法类名
     */
    protected Map<String, BaseShardingValueAlgorithm> columnToValueAlgorithmMap = Maps.newHashMap();

    /**
     * 获取表序号的取模数
     */
    protected int tableModNumber = -1;

    protected Comparable<?> buildShardingValue(String columnName, Comparable<?> clientShardingValue) {
        if (StrUtil.isBlank(columnName) || clientShardingValue == null) {
            return clientShardingValue;
        }
        BaseShardingValueAlgorithm shardingValueAlgorithm = columnToValueAlgorithmMap.get(columnName);
        // 若对应的分片列名对应其值的算法为空-则无需对值进行处理
        if (shardingValueAlgorithm == null) {
            return clientShardingValue;
        }
        // 获取自定义策略类，解析分片键的值
        return shardingValueAlgorithm.parseValue(clientShardingValue);
    }

    public void baseInit(Properties properties) {
        this.props = properties;
        this.tableModNumber = ShardingUtils.getTableModNumber(properties);
        this.allTableSequences = ShardingUtils.getAllTableSequences(properties);
        this.columnToValueAlgorithmMap = ShardingUtils.getColumnValueAlgorithmMap(properties);
    }

    @Override
    public synchronized void init(Properties properties) {
        baseInit(properties);
    }

    @Override
    public Properties getProps() {
        return props;
    }

    public List<Integer> getAllTableSequences() {
        return allTableSequences;
    }

    public Map<String, BaseShardingValueAlgorithm> getColumnToValueAlgorithmMap() {
        return columnToValueAlgorithmMap;
    }

    public int getTableModNumber() {
        return tableModNumber;
    }
}
