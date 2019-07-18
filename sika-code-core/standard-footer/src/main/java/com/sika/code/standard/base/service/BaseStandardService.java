package com.sika.code.standard.base.service;

import com.sika.code.basic.pojo.query.BaseQuery;
import com.sika.code.database.common.Page;
import com.sika.code.database.common.PageQuery;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;

import java.util.List;

/**
 * <p>Description: 服务基础类</p>
 *
 * <pre>
 *     泛型顺序
 *     1 Mapper
 *     2 Entity
 *     3 DTO
 *     其中Mapper和Entity的顺序必须放在最前
 * </pre>
 *
 * @author daiqi
 * @date 2018/8/30
 */
public interface BaseStandardService<DTO extends BaseStandardDTO> {

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
    DTO saveAndRet(DTO dto);

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
    boolean save(DTO dto);

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
    List<DTO> saveForBatchAndRet(List<DTO> dtos);

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
    boolean saveForBatch(List<DTO> dtos);

    /**
     * <p>
     * 更新并返回更新成功的DTO
     * </p>
     *
     * @param dto : DTO
     * @return DTO
     * @author daiqi
     * @date 2019/6/16 13:41
     */
    DTO updateAndRet(DTO dto);

    /**
     * <p>
     * 更新DTO，成功返回true 失败返回false
     * </p>
     *
     * @param dto : DTO
     * @return boolean
     * @author daiqi
     * @date 2019/6/16 13:42
     */
    boolean update(DTO dto);

    /**
     * <p>
     * 批量更新并返回 更新成功的DTO列表
     * </p>
     *
     * @param dtos : DTOS
     * @return java.manager.List<DTO>
     * @author daiqi
     * @date 2019/6/16 13:42
     */
    List<DTO> updateForBatchAndRet(List<DTO> dtos);

    /**
     * <p>
     * 批量更新
     * </p>
     *
     * @param dtos : DTOS
     * @return boolean
     * @author daiqi
     * @date 2019/6/16 13:43
     */
    boolean updateForBatch(List<DTO> dtos);

    /**
     * <p>
     * 保存或者更新并且返回操作成功后的DTO对象
     * </p>
     *
     * @param dto
     * @return DTO
     * @author daiqi
     * @date 2019/6/16 13:43
     */
    DTO saveOrUpdateAndRet(DTO dto);

    /**
     * <p>
     * 保存或更新
     * </p>
     *
     * @param dto
     * @return boolean
     * @author daiqi
     * @date 2019/6/16 13:43
     */
    boolean saveOrUpdate(DTO dto);

    /**
     * <p>
     * 批量保存或更新并且返回操作成功后的对象列表
     * </p>
     *
     * @param dtos
     * @return java.manager.List<DTO>
     * @author daiqi
     * @date 2019/6/16 13:43
     */
    List<DTO> saveOrUpdateForBatchAndRet(List<DTO> dtos);

    /**
     * <p>
     * 批量保存或者更新列表
     * </p>
     *
     * @param dtos
     * @return boolean
     * @author daiqi
     * @date 2019/6/16 13:44
     */
    boolean saveOrUpdateForBatch(List<DTO> dtos);

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
     * 根据查询条件对象获取id
     * </p>
     *
     * @param query : 查询对象
     * @return DTO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    <QUERY extends BaseQuery> Long findId(QUERY query);

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
     * 获取id列表
     * </p>
     *
     * @param query
     * @return java.manager.List<java.lang.Long>
     * @author daiqi
     * @date 2019/6/16 13:45
     */
    <QUERY extends BaseQuery> List<Long> listId(QUERY query);

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

    /**
     * <p>
     * 是否存在id对应的数据，存在返回true，否则返回false
     * </p>
     *
     * @param id 主键
     * @return boolean
     * @author daiqi
     * @date 2019/6/18 21:13
     */
    boolean exist(Long id);

    /**
     * <p>
     * 是否不存在id对应的数据，不存在返回true，否则返回false
     * </p>
     *
     * @param id 主键
     * @return boolean
     * @author daiqi
     * @date 2019/6/18 21:13
     */
    boolean notExist(Long id);

    /**
     * <p>
     * 是否存在query对应的数据，存在返回true，否则返回false
     * </p>
     *
     * @param query 查询对象
     * @return boolean
     * @author daiqi
     * @date 2019/6/18 21:13
     */
    <QUERY extends BaseQuery> boolean exist(QUERY query);

    /**
     * <p>
     * 是否不存在query对应的数据，不存在返回true，否则返回false
     * </p>
     *
     * @param query 查询对象
     * @return boolean
     * @author daiqi
     * @date 2019/6/18 21:13
     */
    <QUERY extends BaseQuery> boolean notExist(QUERY query);

    /**
     * <p>
     * 校验id对应的数据是否不存在，不存在抛出异常
     * </p>
     *
     * @param id : 数据id
     * @author daiqi
     * @date 2019/6/18 21:05
     */
    void verifyNotExist(Long id);

    /**
     * <p>
     * 校验id对应的数据是否存在， 存在抛出异常
     * </p>
     *
     * @param id : 数据id
     * @author daiqi
     * @date 2019/6/18 21:06
     */
    void verifyExist(Long id);

    /**
     * <p>
     * 校验查询条件对应的数据是否不存在，不存在抛出异常
     * </p>
     *
     * @param query : 查询对象
     * @author daiqi
     * @date 2019/6/18 21:05
     */
    <QUERY extends BaseQuery> void verifyNotExist(QUERY query);

    /**
     * <p>
     * 校验查询条件对应的数据是否存在， 存在抛出异常
     * </p>
     *
     * @param query : 查询对象
     * @author daiqi
     * @date 2019/6/18 21:06
     */
    <QUERY extends BaseQuery> void verifyExist(QUERY query);

}
