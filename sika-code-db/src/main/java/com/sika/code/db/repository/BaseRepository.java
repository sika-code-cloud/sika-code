package com.sika.code.db.repository;

import com.sika.code.core.base.pojo.query.PageQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础存储接口
 *
 * @author daiqi
 * @create 2021-10-13 22:39
 */
public interface BaseRepository<T, Q> {
    int insert(T entity);

    int deleteById(Serializable id);

    int deleteById(T entity);

    int deleteByMap(Map<String, Object> columnMap);


    int deleteBatchIds(Collection<? extends Serializable> idList);

    int updateById(T entity);

    T selectById(Serializable id);

    List<T> selectBatchIds(Collection<? extends Serializable> idList);

    List<T> selectByMap(Map<String, Object> columnMap);


    boolean insertBatch(List<T> entityList);

    boolean updateBatchById(List<T> entityList);

    /**
     * <p>
     * 根据查询条件对象获取PO对象
     * </p>
     *
     * @param query : 查询对象
     * @return PO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    T find(Q query);


    /**
     * <p>
     * 根据查询条件对象获取PO列表数据
     * </p>
     *
     * @param query : 查询对象
     * @return PO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    List<T> list(Q query);

    /**
     * <p>
     * 根据查询条件获取分页列表信息
     * </p>
     *
     * @param query : 查询对象
     * @return Page<PageItem>
     * @author daiqi
     * @date 2018/12/6 13:36
     */
    List<T> pageCursor(Q query, PageQuery pageQuery);

    /**
     * <p>
     * 根据查询条件获取分页总数量
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param query
     * @return int
     * @author daiqi
     * @date 2018/12/6 11:51
     */
    int count(Q query);


}
