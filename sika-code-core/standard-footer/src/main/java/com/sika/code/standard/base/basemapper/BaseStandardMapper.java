package com.sika.code.standard.base.basemapper;

import com.sika.code.basic.pojo.query.BaseQuery;
import com.sika.code.database.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 该Mapper不能被Mappscan扫描到 因此放在basemapper包下
 *
 * @author daiqi
 * @create 2018-12-03 16:42
 */
public interface BaseStandardMapper<T> extends BaseMapper<T> {
    /**
     * <p>
     * 根据查询条件获取继承BaseEntity的实体列表数据
     * </p>
     *
     * @param query
     * @return java.manager.List<T>
     * @author daiqi
     * @date 2018/12/3 16:45
     */
    <Query extends BaseQuery> List<T> listByQuery(@Param(value = "query") Query query);

    /**
     * <p>
     * 根据查询条件获取id列表
     * </p>
     *
     * @param query
     * @return java.manager.List<T>
     * @author daiqi
     * @date 2019/6/16 12:23
     */
    <Query extends BaseQuery> List<Long> listIdByQuery(@Param(value = "query") Query query);

    /**
     * <p>
     * 根据查询条件获取id
     * </p>
     *
     * @param query
     * @return java.lang.Long
     * @author daiqi
     * @date 2019/6/16 12:24
     */
    <Query extends BaseQuery> Long findIdByQuery(@Param(value = "query") Query query);


    /**
     * <p>
     * 根据查询条件获取继承BaseEntity的实体数据
     * </p>
     *
     * @param query
     * @return T
     * @author daiqi
     * @date 2019/1/9 14:58
     */
    <Query extends BaseQuery> T findByQuery(@Param(value = "query") Query query);

    /**
     * <p>
     * 根据查询条件获取分页数据
     * </p>
     *
     * @param query
     * @return java.manager.List<T>
     * @author daiqi
     * @date 2018/12/6 11:50
     */
    <Query extends BaseQuery> List<T> pageByQuery(@Param(value = "query") Query query);

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
    <Query extends BaseQuery> int totalCountByQuery(@Param(value = "query") Query query);

    /**
     * <p>
     * 根据查询条件删除数据
     * </p>
     *
     * @param query
     * @return int
     * @author daiqi
     * @date 2019/5/9 10:20
     */
    <Query extends BaseQuery> int deleteByQuery(@Param(value = "query") Query query);
}
