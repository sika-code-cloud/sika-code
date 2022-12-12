package com.sika.code.db.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sika.code.core.base.pojo.query.PageQuery;
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

    default <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<T> queryWrapper) {
        return getMapper().selectMapsPage(page, queryWrapper);
    }

    @Override
    default T find(Q query) {
        return getMapper().find(query);
    }

    @Override
    default List<T> list(Q query) {
        return getMapper().list(query);
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
