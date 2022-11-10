package com.sika.code.db.sharding.repository;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.db.mapper.BaseMapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import com.sika.code.db.sharding.context.ShardingContext;

import java.io.Serializable;
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
public interface BaseShardingRepository<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable, Mapper extends BaseMapper<PO, PRIMARY>> extends BaseRepositoryMybatisPlus<PO, PRIMARY, Mapper> {
    /**
     * 批量保存-分库分表
     *
     * @param pos           ： 批量保存的对象列表
     * @param shardingValue ： 分库分表的值
     * @return
     */
    default int saveBatchSharding(List<PO> pos, Object shardingValue) {
        try {
            verifyShardingValue(shardingValue);
            ShardingContext.addShardValue(shardingValue, false);
            return saveBatch(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-分库分表
     *
     * @param pos           : 批量保存的对象列表
     * @param shardingValue : 分库分表的值
     * @return
     */
    default int insertBatchSharding(List<PO> pos, Object shardingValue) {
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
     * @param wrapper       : 更新的wrapper
     * @param shardingValue : 分库分表的值
     * @return
     */
    default int updateBatchSharding(List<PO> updatePos, UpdateWrapper wrapper, Object shardingValue) {
        try {
            verifyShardingValue(shardingValue);
            ShardingContext.addShardValue(shardingValue, false);
            return updateBatch(updatePos, wrapper);
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
    default int insertBatchAndDupIgnoreSharding(List<PO> pos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return insertBatchAndDupIgnore(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量保存-分库分表
     *
     * @param pos           ： 批量保存的对象列表
     * @param dbName ： 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default int saveBatchDbName(List<PO> pos, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return saveBatch(pos);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-分库分表
     *
     * @param pos           : 批量保存的对象列表
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default int insertBatchDbName(List<PO> pos, String dbName) {
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
     * @param updatePos     ： 批量更新的对象列表
     * @param wrapper       : 更新的wrapper
     * @param dbName : 原始的数据库名称-适用于同表不同主体的分库模式
     * @return
     */
    default int updateBatchDbName(List<PO> updatePos, UpdateWrapper wrapper, String dbName) {
        try {
            verifyDbName(dbName);
            ShardingContext.addDbName(dbName, false);
            return updateBatch(updatePos, wrapper);
        } finally {
            ShardingContext.remove();
        }
    }

    /**
     * 批量插入-忽略重复值-分库分表
     *
     * @param pos           : 批量插入的对象列表
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

    default int updateSharding(Wrapper<PO> wrapper, Object shardingValue) {
        ShardingContext.addShardDbValue(shardingValue);
        return this.update(wrapper);
    }

    default int updateSharding(Wrapper<PO> wrapper, Object shardingValue, String dbName) {
        ShardingContext.addShardDbValue(shardingValue);
        ShardingContext.addDbName(dbName);
        return this.update(wrapper);
    }

    default void verifyDbName(String dbName) {
        Assert.notEmpty(dbName, "指定的库名不能为空");
    }

    default void verifyShardingValue(Object value) {
        Assert.notNull(value, "分库分表的值不能为空");
    }
}
