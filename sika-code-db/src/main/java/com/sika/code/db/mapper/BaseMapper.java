package com.sika.code.db.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sika.code.core.base.pojo.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 业务逻辑基础类
 * </p>
 *
 * <pre>
 *  说明：所有业务逻辑类可以继承此类
 *  约定：
 *  命名规范：以Mapper结尾
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:24:24
 */
public interface BaseMapper<T, Q> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

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
    default T find(Q query) {
        Wrapper<T> wrapper = buildQueryWrapper(query);
        return selectOne(wrapper);
    }


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
    default List<T> list(Q query) {
        Wrapper<T> wrapper = buildQueryWrapper(query);
        return selectList(wrapper);
    }

    /**
     * <p>
     * 根据查询条件获取按照游标分页列表信息
     * </p>
     *
     * @param query : 查询对象
     * @return Page<PageItem>
     * @author daiqi
     * @date 2018/12/6 13:36
     */
    default List<T> listCursor(Q query, PageQuery pageQuery) {
        throw new RuntimeException("请实现游标分页列表查询");
    }

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

    Wrapper<T> buildQueryWrapper(Q q);

    default String buildLimit(PageQuery pageQuery) {
        return " limit " + pageQuery.getPageSize();
    }
}
