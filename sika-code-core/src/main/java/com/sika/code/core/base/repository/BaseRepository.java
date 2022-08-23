package com.sika.code.core.base.repository;

import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.PageQuery;

import java.io.Serializable;
import java.util.List;

/**
 * 基础存储接口
 *
 * @author daiqi
 * @create 2021-10-13 22:39
 */
public interface BaseRepository<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable> {


    /**
     * <p>
     * 传入PO进行保存，并且返回持久化后主键ID
     * </p>
     *
     * @param po : 需要进行保存的PO对象
     * @return PRIMARY : 返回插入后的主键
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    PRIMARY saveRetId(PO po);

    /**
     * <p>
     * 传入PO进行保存|更新-根据id是否为空来判定是插入还是更新，成功返回1，否则返回
     * </p>
     *
     * @param po : 需要进行保存的PO对象 为空的字段不保存
     * @return int : 若插入失败返回0
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    int save(PO po);

    /**
     * <p>
     * 传入PO进行保存|更新-根据id是否为空来判定是插入还是更新，成功返回1，否则返回
     * </p>
     *
     * @param po : 需要进行保存的PO对象 为空的字段不保存
     * @return int : 若插入失败返回0
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    int insert(PO po);

    /**
     * <p>
     * 传入PO进行保存，并且返回持久化后主键ID
     * </p>
     *
     * @param po : 需要进行保存的PO对象
     * @return PRIMARY : 返回插入后的主键
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    PRIMARY insertRetId(PO po);

    /**
     * <p>
     * 传入PO进行更新成功返回的值大于0
     * </p>
     *
     * @param po : 需要进行保存的PO对象 为空的字段不保存
     * @return int : 若插入失败返回0
     * @author daiqi
     * @date 2018/12/6 9:32
     */
    int updateById(PO po);


    /**
     * <p>
     * 批量保存 返回保存成功的条数
     * </p>
     *
     * @param pos : 列表
     * @return int
     * @author daiqi
     * @date 2019/6/16 13:41
     */
    int saveBatch(List<PO> pos);

    int insertBatch(List<PO> pos, int batchSize);

    /**
     * 根据主键查询数据
     */
    PO findByPrimaryKey(PRIMARY primaryKey);

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
    <QUERY extends BaseQuery<PRIMARY>> PO find(QUERY query);

    /**
     * <p>
     * 根据查询条件对象获取id
     * </p>
     *
     * @param query : 查询对象
     * @return PO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    <QUERY extends BaseQuery<PRIMARY>> PRIMARY findId(QUERY query);

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
    <QUERY extends BaseQuery<PRIMARY>> List<PO> list(QUERY query);

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
    <QUERY extends BaseQuery<PRIMARY>> List<PRIMARY> listId(QUERY query);

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
    <QUERY extends PageQuery<PRIMARY>> List<PO> page(QUERY query);

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
    <Query extends BaseQuery<PRIMARY>> int count(Query query);

    int updateBatchById(List<PO> pos);

    int updateBatchById(List<PO> poList, int batchSize);
}
