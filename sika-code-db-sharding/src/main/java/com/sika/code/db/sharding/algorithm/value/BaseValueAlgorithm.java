package com.sika.code.db.sharding.algorithm.value;

import com.sika.code.db.sharding.algorithm.key.BaseTableShardingAlgorithm;
import com.sika.code.db.sharding.algorithm.value.bo.DataBaseValueAlgorithmBO;
import com.sika.code.db.sharding.algorithm.value.bo.TableValueAlgorithmBO;

import java.util.Collection;

/**
 * <p>
 * 基础值算法
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/3 11:59
 */
public interface BaseValueAlgorithm {
    /**
     * 获取数据库实例名称
     *
     * @param algorithmBO
     * @return 数据库实例名称
     */
    String getDataBaseName(DataBaseValueAlgorithmBO algorithmBO);

    /**
     * 获取表名称
     *
     * @param algorithmBO
     * @return 完整表名
     */
    String getTableName(TableValueAlgorithmBO algorithmBO);

}
