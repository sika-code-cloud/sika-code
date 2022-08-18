package com.sika.code.db.repository.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.db.mapper.BaseMapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author daiqi
 * @create 2021-10-19 15:21
 */
public abstract class BaseRepositoryMyBatisPlusImpl<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable, Mapper extends BaseMapper<PO, PRIMARY>> implements BaseRepositoryMybatisPlus<PO, PRIMARY, Mapper> {
    protected Log log = LogFactory.getLog(getClass());

    /**
     * 默认批次提交数量
     */
    protected final int DEFAULT_BATCH_SIZE = 500;
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
    public Mapper getMapper() {
        return this.mapper;
    }
}
