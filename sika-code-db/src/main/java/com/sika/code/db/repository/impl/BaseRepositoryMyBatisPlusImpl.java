package com.sika.code.db.repository.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.sika.code.db.mapper.BaseMapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author daiqi
 * @create 2021-10-19 15:21
 */
public abstract class BaseRepositoryMyBatisPlusImpl<T, Q, M extends BaseMapper<T, Q>> implements BaseRepositoryMybatisPlus<T, Q, M> {
    protected Log log = LogFactory.getLog(getClass());

    /**
     * 默认批次提交数量
     */
    protected final int public_BATCH_SIZE = 1000;
    /**
     * 只需要动态获取一次即可
     */
    protected M m = currentMapper();
    protected Class<M> mapperClass = currentMapperClass();
    protected Class<T> poClass = currentModelClass();

    protected M currentMapper() {
        return SpringUtil.getBean(currentMapperClass());
    }

    protected Class<M> currentMapperClass() {
        return (Class<M>) getSuperClassGenericType(2);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) getSuperClassGenericType(0);
    }

    private Class<?> getSuperClassGenericType(int index) {
        return ReflectionKit.getSuperClassGenericType(this.getClass(), BaseRepositoryMybatisPlus.class, index);
    }

    public List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }

    /**
     * 批量插入
     */
    @Override
    public boolean insertBatch(List<T> entityList) {
        return insertBatch(entityList, public_BATCH_SIZE);
    }

    /**
     * 批量更新
     */
    @Override
    public boolean updateBatchById(List<T> entityList) {
        return updateBatchById(entityList, public_BATCH_SIZE);
    }

    /**
     * 批量插入或更新
     */
    public boolean insertOrUpdateBatch(List<T> entityList) {
        return insertOrUpdateBatch(entityList, public_BATCH_SIZE);
    }

    /**
     * 批量插入(包含限制条数)
     */
    public boolean insertBatch(List<T> entityList, int batchSize) {
        String sqlStatement = SqlHelper.getSqlStatement(this.currentMapperClass(), SqlMethod.INSERT_ONE);
        return SqlHelper.executeBatch(this.currentModelClass(), log, entityList, batchSize,
                (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    /**
     * 批量更新(包含限制条数)
     */
    public boolean updateBatchById(List<T> entityList, int batchSize) {
        String sqlStatement = SqlHelper.getSqlStatement(this.currentMapperClass(), SqlMethod.UPDATE_BY_ID);
        return SqlHelper.executeBatch(this.currentModelClass(), log, entityList, batchSize,
                (sqlSession, entity) -> {
                    MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                    param.put(Constants.ENTITY, entity);
                    sqlSession.update(sqlStatement, param);
                });
    }

    /**
     * 批量插入或更新(包含限制条数)
     */
    public boolean insertOrUpdateBatch(List<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.currentModelClass());
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        com.baomidou.mybatisplus.core.toolkit.Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.currentModelClass(), this.currentMapperClass(), log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = tableInfo.getPropertyValue(entity, keyProperty);
            String sqlStatement = SqlHelper.getSqlStatement(this.currentMapperClass(), SqlMethod.SELECT_BY_ID);
            return StringUtils.checkValNull(idVal)
                    || CollUtil.isEmpty(sqlSession.selectList(sqlStatement, entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            String sqlStatement = SqlHelper.getSqlStatement(this.currentMapperClass(), SqlMethod.UPDATE_BY_ID);
            sqlSession.update(sqlStatement, param);
        });
    }

    /**
     * 插入或更新(包含限制条数)
     */
    public boolean insertOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.currentModelClass());
            com.baomidou.mybatisplus.core.toolkit.Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(selectById((Serializable) idVal)) ? insert(entity) > 0 : updateById(entity) > 0;
        }
        return false;
    }
    /**
     * <p>
     * 批量插入-若唯一索引冲突-则忽略
     * </p >
     *
     * @param pos
     * @return int
     * @author sikadai
     * @since 2022/9/1 19:55
     */
    @Override
    public boolean insertBatchAndDupIgnore(List<T> pos) {
        if (CollUtil.isEmpty(pos)) {
            return false;
        }
        int count = 0;
        List<List<T>> waitLists = Lists.partition(pos, public_BATCH_SIZE);
        for (List<T> poList : waitLists) {
            try {
                insertBatch(poList);
            } catch (DuplicateKeyException e) {
                // 此处执行单条插入
                for (T po : poList) {
                    try {
                        insert(po);
                    } catch (DuplicateKeyException de) {
                        // 此处唯一索引则无需处理
                    }
                }
            }
        }
        return true;
    }

    @Override
    public M getMapper() {
        return this.m;
    }
}
