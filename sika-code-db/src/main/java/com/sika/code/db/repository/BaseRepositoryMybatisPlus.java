package com.sika.code.db.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.core.base.repository.BaseRepository;
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
public interface BaseRepositoryMybatisPlus<T, Mapper extends BaseMapper<T>> extends BaseRepository<T> {
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
    default <QUERY extends BaseQuery> T find(QUERY query) {
        return null;
    }

    @Override
    default <QUERY extends BaseQuery> List<T> list(QUERY query) {
        return null;
    }

    @Override
    default <QUERY extends PageQuery> List<T> page(QUERY query) {
        return null;
    }

    @Override
    default <Query extends BaseQuery> int count(Query query) {
        return 0;
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
