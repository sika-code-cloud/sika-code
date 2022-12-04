package com.sika.code.core.base.service;

import com.sika.code.core.base.pojo.dto.BaseDTO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.Page;
import com.sika.code.core.base.pojo.query.PageQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务基础类
 * </p>
 *
 * <pre>
 *  说明：所有服务类可以继承此类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:24:24
 */
public interface BaseService<PRIMARY extends Serializable, DTO extends BaseDTO> {
    /**
     * <p>
     * 传入DTO进行保存，并且返回持久化后转化的DTO
     * </p>
     *
     * @param dto : 需要进行保存的DTO对象
     * @return DTO : 若插入失败返回为null
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    DTO insertAndRet(DTO dto);

    /**
     * <p>
     * 传入DTO进行保存，成功返回true，否则返回false
     * </p>
     *
     * @param dto : 需要进行保存的DTO对象
     * @return DTO : 若插入失败返回为null
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    boolean insert(DTO dto);

    /**
     * <p>
     * 批量保存并且返回持久化后的DTO列表
     * </p>
     *
     * @param dtos : 列表
     * @return java.manager.List<DTO>
     * @author daiqi
     * @date 2019/6/16 13:40
     */
    List<DTO> insertBatchAndRet(List<DTO> dtos);

    /**
     * <p>
     * 批量保存 成功返回true 失败返回false
     * </p>
     *
     * @param dtos : 列表
     * @return boolean
     * @author daiqi
     * @date 2019/6/16 13:41
     */
    boolean insertBatch(List<DTO> dtos);

    /**
     * 根据主键查询数据
     */
    DTO findByPrimaryKey(PRIMARY primaryKey);

    /**
     * <p>
     * 根据查询条件对象获取DTO对象
     * </p>
     *
     * @param query : 查询对象
     * @return DTO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    <QUERY extends BaseQuery> DTO find(QUERY query);


    /**
     * <p>
     * 根据查询条件对象获取DTO列表数据
     * </p>
     *
     * @param query : 查询对象
     * @return DTO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    <QUERY extends BaseQuery> List<DTO> list(QUERY query);

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
    <QUERY extends PageQuery> Page<DTO> page(QUERY query);
}
