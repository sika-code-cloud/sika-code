package com.sika.code.db.repository.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.sika.code.core.base.constant.BaseConstant;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.db.constant.DbConstant;
import com.sika.code.db.mapper.BaseMapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DuplicateKeyException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author daiqi
 * @create 2021-10-19 15:21
 */
public abstract class BaseRepositoryMyBatisPlusImpl<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable, Mapper extends BaseMapper<PO, PRIMARY>> implements BaseRepositoryMybatisPlus<PO, PRIMARY, Mapper> {
    protected Log log = LogFactory.getLog(getClass());

    protected static final String SET_VERSION_SQL = DbConstant.SET_VERSION_SQL;
    protected static final String LOGIC_DELETED_SQL = DbConstant.LOGIC_DELETED_SQL;
    protected static final String IS_DELETED_KEY = DbConstant.IS_DELETED_KEY;
    /**
     * 默认批次提交数量
     */
    protected final int DEFAULT_BATCH_SIZE = 1000;
    /**
     * 只需要动态获取一次即可
     */
    protected Mapper mapper = currentMapper();
    protected Class<Mapper> mapperClass = currentMapperClass();
    protected Class<PO> poClass = currentPoClass();

    protected Mapper currentMapper() {
        return SpringUtil.getBean(currentMapperClass());
    }

    protected Class<Mapper> currentMapperClass() {
        return (Class<Mapper>) getSuperClassGenericType(2);
    }

    protected Class<PO> currentPoClass() {
        return (Class<PO>) getSuperClassGenericType(0);
    }

    private Class<?> getSuperClassGenericType(int index) {
        return ReflectionKit.getSuperClassGenericType(this.getClass(), BaseRepositoryMybatisPlus.class, index);
    }


    /**
     * 批量插入
     *
     * @param poList ignore
     * @return ignore
     */
    @Override
    public int insertBatch(List<PO> poList) {
        return insertBatch(poList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public int updateBatchById(List<PO> poList) {
        return updateBatchById(poList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public <Query extends BaseQuery<PRIMARY>> int updateBatch(List<PO> pos, Query query) {
        return updateBatch(pos, query, DEFAULT_BATCH_SIZE);
    }

    @Override
    public <Query extends BaseQuery<PRIMARY>> int updateBatch(List<PO> pos, Query query, int batchSize) {
        if (CollUtil.isEmpty(pos)) {
            return 0;
        }
        List<List<PO>> waitPos = Lists.partition(pos, batchSize);
        int count = 0;
        for (List<PO> updatePos : waitPos) {
            List<PRIMARY> primaries = updatePos.stream().map(PO::getId).distinct().collect(Collectors.toList());
            if (CollUtil.isEmpty(primaries)) {
                continue;
            }
            UpdateWrapper<Query> updateWrapper = new UpdateWrapper<>();
            updateWrapper.in(ID_KEY, primaries);
            // 批量更新
            buildUpdateWrapper(updateWrapper, query);
            count += updateBatch(updatePos, updateWrapper);
        }
        return count;
    }

    protected <Query extends BaseQuery<PRIMARY>> void buildUpdateWrapper(UpdateWrapper<Query> updateWrapper, Query query) {

    }

    /**
     * 批量插入
     *
     * @param poList    ignore
     * @param batchSize ignore
     * @return ignore
     */
    @Override
    public int insertBatch(List<PO> poList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        boolean result = executeBatch(poList, batchSize, (sqlSession, po) -> sqlSession.insert(sqlStatement, po));
        if (result) {
            return poList.size();
        }
        return 0;
    }

    @Override
    public int updateBatchById(List<PO> poList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        boolean result = executeBatch(poList, batchSize, (sqlSession, po) -> {
            MapperMethod.ParamMap<PO> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, po);
            sqlSession.update(sqlStatement, param);
        });
        if (result) {
            return poList.size();
        }
        return 0;
    }


    /**
     * 获取mapperStatementId
     *
     * @param sqlMethod 方法名
     * @return 命名id
     * @since 3.4.0
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }

    /**
     * 执行批量操作
     *
     * @param list      数据集合
     * @param batchSize 批量大小
     * @param consumer  执行方法
     * @param <E>       泛型
     * @return 操作结果
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(poClass, this.log, list, batchSize, consumer);
    }

    /**
     * 执行批量操作（默认批次提交数量）
     *
     * @param list     数据集合
     * @param consumer 执行方法
     * @param <E>      泛型
     * @return 操作结果
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return executeBatch(list, DEFAULT_BATCH_SIZE, consumer);
    }

    @Override
    public int update(Wrapper<PO> updateWrapper) {
        buildIsDeletedNo(updateWrapper);
        buildUpdateVersion(updateWrapper);
        return getMapper().update(null, updateWrapper);
    }

    protected void buildUpdateVersion(Wrapper<PO> updateWrapper) {
        if (updateWrapper instanceof UpdateWrapper) {
            ((UpdateWrapper<PO>) updateWrapper).setSql(SET_VERSION_SQL);
        } else if (updateWrapper instanceof LambdaUpdateWrapper) {
            ((LambdaUpdateWrapper<PO>) updateWrapper).setSql(SET_VERSION_SQL);
        }
    }

    @Override
    public int update(PO po, Wrapper<PO> updateWrapper) {
        buildIsDeletedNo(updateWrapper);
        buildUpdateVersion(updateWrapper);
        return getMapper().update(po, updateWrapper);
    }

    protected void buildIsDeletedNo(Wrapper<PO> updateWrapper) {
        if (updateWrapper == null) {
            return;
        }
        if (updateWrapper instanceof UpdateWrapper) {
            ((UpdateWrapper<PO>) updateWrapper).eq(IS_DELETED_KEY, BaseConstant.IsDeletedEnum.NO.getType());
        } else if (updateWrapper instanceof LambdaUpdateWrapper) {
            ((LambdaUpdateWrapper<PO>) updateWrapper).eq(BasePO::getIsDeleted, BaseConstant.IsDeletedEnum.NO.getType());
        }
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
    public int insertBatchAndDupIgnore(List<PO> pos) {
        if (CollUtil.isEmpty(pos)) {
            return 0;
        }
        int count = 0;
        List<List<PO>> waitLists = Lists.partition(pos, DEFAULT_BATCH_SIZE);
        for (List<PO> poList : waitLists) {
            try {
                count += insertBatch(poList);
            } catch (DuplicateKeyException e) {
                // 此处执行单条插入
                for (PO po : poList) {
                    try {
                        count += insert(po);
                    } catch (DuplicateKeyException de) {
                        // 此处唯一索引则无需处理
                    }
                }
            }
        }
        return count;
    }

    @Override
    public Mapper getMapper() {
        return this.mapper;
    }
}
