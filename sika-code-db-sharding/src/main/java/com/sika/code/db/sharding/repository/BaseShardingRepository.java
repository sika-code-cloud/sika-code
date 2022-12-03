package com.sika.code.db.sharding.repository;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.db.mapper.BaseMapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import com.sika.code.db.sharding.context.ShardingContext;

import java.util.List;

/**
 * <p>
 * 基础分库分表仓储
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/10/3 19:34
 */
public interface BaseShardingRepository<PO extends BasePO, Mapper extends BaseMapper<PO>> extends BaseRepositoryMybatisPlus<PO, Mapper> {

    /**
     * 批量插入-分库分表
     *
     * @param pos           : 批量保存的对象列表
     * @param shardingValue : 分库分表的值
     * @return
     */
    default boolean insertBatchSharding(List<PO> pos, Object shardingValue) {
        try {
            verifyShardingValue(shardingValue);
            ShardingContext.addShardValue(shardingValue, false);
            return insertBatch(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量更新-分库分表
     *
     * @param updatePos     ： 批量更新的对象列表
     * @param shardingValue : 分库分表的值
     * @return
     */
    default boolean updateBatchSharding(List<PO> updatePos, Object shardingValue) {
        try {
            verifyShardingValue(shardingValue);
            ShardingContext.addShardValue(shardingValue, false);
            return updateBatchById(updatePos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-忽略重复值-分库分表
     *
     * @param pos    : 批量插入的对象列表
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default boolean insertBatchAndDupIgnoreSharding(List<PO> pos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return insertBatchAndDupIgnore(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-分库分表
     *
     * @param pos    : 批量保存的对象列表
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default boolean insertBatchDbName(List<PO> pos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return insertBatch(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量更新-分库分表
     *
     * @param updatePos ： 批量更新的对象列表
     * @param dbName    : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default boolean updateBatchDbName(List<PO> updatePos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return updateBatchById(updatePos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-忽略重复值-分库分表
     *
     * @param wrapper       : 更新的wrapper对象
     * @param shardingValue : 分片的值
     * @param dbName        : 数据库名称
     * @return
     */
    default int updateSharding(Wrapper wrapper, Object shardingValue, String dbName) {
        try {
            verifyDbName(dbName);
            verifyShardingValue(shardingValue);
            ShardingContext.addShardValue(shardingValue, false);
            ShardingContext.addDbName(dbName, false);
            return update(null, wrapper);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-忽略重复值-分库分表
     *
     * @param pos    : 批量插入的对象列表
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default int insertBatchAndDupIgnoreDbName(List<PO> pos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return insertBatchAndDupIgnore(pos);
        } finally {
            ShardingContext.remove();
        }
    }


    /**
     * 批量插入-忽略重复值-分库分表
     *
     * @param pos    : 批量插入的对象列表
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default boolean insertBatchAndDupIgnoreDbName(List<PO> pos, Object shardingValue, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addShardValue(shardingValue, false);
            ShardingContext.addDbName(dbName, false);
            return insertBatchAndDupIgnore(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    default void verifyDbName(String dbName) {
        Assert.notEmpty(dbName, "指定的库名不能为空");
    }

    default void verifyShardingValue(Object value) {
        Assert.notNull(value, "分库分表的值不能为空");
    }
}
