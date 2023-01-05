package com.sika.code.db.repository;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.core.util.BeanUtil;
import com.sika.code.db.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * mybatis基础持久化接口
 *
 * @author daiqi
 * @create 2021-10-15 22:39
 */
public interface BaseRepositoryMybatisPlus<T, Q, Mapper extends BaseMapper<T, Q>> extends BaseRepository<T, Q> {
    @Override
    default int insert(T entity) {
        return getMapper().insert(entity);
    }

    @Override
    default int deleteById(Serializable id) {
        return getMapper().deleteById(id);
    }

    @Override
    default int deleteById(T entity) {
        return getMapper().deleteById(entity);
    }

    @Override
    default int deleteByMap(Map<String, Object> columnMap) {
        return getMapper().deleteByMap(columnMap);
    }

    default int delete(Wrapper<T> queryWrapper) {
        return getMapper().delete(queryWrapper);
    }

    @Override
    default int deleteBatchIds(Collection<? extends Serializable> idList) {
        return getMapper().deleteBatchIds(idList);
    }

    @Override
    default int updateById(T entity) {
        return getMapper().updateById(entity);
    }

    default int update(Wrapper<T> updateWrapper) {
        return update(null, updateWrapper);
    }
    default int update(T entity, Wrapper<T> updateWrapper) {
        return getMapper().update(entity, updateWrapper);
    }

    @Override
    default T selectById(Serializable id) {
        return getMapper().selectById(id);
    }

    @Override
    default List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return getMapper().selectBatchIds(idList);
    }

    @Override
    default List<T> selectByMap(Map<String, Object> columnMap) {
        return getMapper().selectByMap(columnMap);
    }


    default T selectOne(Wrapper<T> queryWrapper) {
        return getMapper().selectOne(queryWrapper);
    }

    default Long selectCount(Wrapper<T> queryWrapper) {
        return getMapper().selectCount(queryWrapper);
    }


    default List<T> selectList(Wrapper<T> queryWrapper) {
        return getMapper().selectList(queryWrapper);
    }

    default List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
        return getMapper().selectMaps(queryWrapper);
    }

    default List<Object> selectObjs(Wrapper<T> queryWrapper) {
        return getMapper().selectObjs(queryWrapper);
    }

    default <P extends IPage<T>> P selectPage(P page, Wrapper<T> queryWrapper) {
        return getMapper().selectPage(page, queryWrapper);
    }

    /**
     * 分页查询VO
     */
    default <C, P extends IPage<C>> P selectPage(IPage<T> page, Wrapper<T> wrapper, Class<C> retClass) {
        IPage<T> pageData = this.selectPage(page, wrapper);
        IPage<C> voPage = new Page<>(pageData.getCurrent(), pageData.getSize(), pageData.getTotal());
        if (CollUtil.isEmpty(pageData.getRecords())) {
            return (P) voPage;
        }
        voPage.setRecords(BeanUtil.toBeans(pageData.getRecords(), retClass));
        return (P) voPage;
    }

    /**
     * <p>
     * 分页查询数据
     * </p>
     *
     * @param page   : 分页对象
     * @param query  : 查询条件
     * @param rClass : 返回的类型class
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<R>
     * @author sikadai
     * @since 2022/12/12 23:37
     */
    default <R, P extends IPage<T>> Page<R> selectPage(P page, Q query, Class<R> rClass) {
        Wrapper<T> wrapper = getMapper().buildQueryWrapper(query);
        return selectPage(page, wrapper, rClass);
    }

    default <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<T> queryWrapper) {
        return getMapper().selectMapsPage(page, queryWrapper);
    }

    /**
     * 根据 ID 查询
     */
    default <C> C selectById(Serializable id, Class<C> retClass) {
        T obj = this.selectById(id);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return BeanUtil.toBean(obj, retClass);
    }


    /**
     * 查询（根据ID 批量查询）
     */
    default <C> List<C> selectBatchIds(Collection<? extends Serializable> idList, Class<C> retClass) {
        List<T> list = this.selectBatchIds(idList);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanUtil.toBeans(list, retClass);
    }

    /**
     * 查询（根据 columnMap 条件）
     */
    default <C> List<C> selectByMap(Map<String, Object> map, Class<C> retClass) {
        List<T> list = this.selectByMap(map);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanUtil.toBeans(list, retClass);
    }

    /**
     * 根据 entity 条件，查询一条记录
     */
    default <C> C selectOne(Wrapper<T> wrapper, Class<C> retClass) {
        T obj = this.selectOne(wrapper);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return BeanUtil.toBean(obj, retClass);
    }

    /**
     * 根据 entity 条件，查询全部记录
     */
    default <C> List<C> selectList(Wrapper<T> wrapper, Class<C> retClass) {
        List<T> list = this.selectList(wrapper);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanUtil.toBeans(list, retClass);
    }

    @Override
    default T find(Q query) {
        return getMapper().find(query);
    }

    @Override
    default List<T> list(Q query) {
        return getMapper().list(query);
    }

    default <R> R findRet(Q query, Class<R> rClass) {
        return BeanUtil.toBean(find(query), rClass);
    }

    default <R> List<R> listRet(Q query, Class<R> rClass) {
        return BeanUtil.toBeans(list(query), rClass);
    }

    @Override
    default List<T> pageCursor(Q query, PageQuery pageQuery) {
        return getMapper().listCursor(query, pageQuery);
    }

    @Override
    default int count(Q query) {
        return getMapper().count(query);
    }

    @Override
    boolean insertBatch(List<T> entityList);

    @Override
    boolean updateBatchById(List<T> entityList);

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
    boolean insertBatchAndDupIgnore(List<T> pos);

    Mapper getMapper();
}
