package com.sika.code.db.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
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
public interface BaseMapper<T extends BasePO<PRIMARY>, PRIMARY extends Serializable> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
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
    <Query extends BaseQuery<PRIMARY>> List<T> list(@Param(value = "query") Query query);

    /**
     * <p>
     * 根据查询条件获取id列表
     * </p>dm
     *
     * @param query
     * @return java.manager.List<T>
     * @author daiqi
     * @date 2019/6/16 12:23
     */
    <Query extends BaseQuery<PRIMARY>> List<PRIMARY> listId(@Param(value = "query") Query query);

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
    <Query extends BaseQuery<PRIMARY>> PRIMARY findId(@Param(value = "query") Query query);


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
    <Query extends BaseQuery<PRIMARY>> T find(@Param(value = "query") Query query);

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
    <Query extends BaseQuery<PRIMARY>> List<T> page(@Param(value = "query") Query query);

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
    <Query extends BaseQuery<PRIMARY>> int count(@Param(value = "query") Query query);

    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int updateBatchCaseWhen(@Param("list") List<T> list, @Param(Constants.WRAPPER) UpdateWrapper updateWrapper);

    /**
     * <p>
     * 逻辑删除
     * </p>
     *
     * @param updateWrapper
     * @return int
     * @author sikadai
     * @since 2022/8/25 23:08
     */
    int logicDelete(@Param(Constants.WRAPPER) Wrapper updateWrapper);

}
