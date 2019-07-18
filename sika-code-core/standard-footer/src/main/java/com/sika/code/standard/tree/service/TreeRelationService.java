package com.sika.code.standard.tree.service;



import com.sika.code.standard.base.service.BaseStandardService;
import com.sika.code.standard.tree.pojo.dto.TreeRelationDTO;

import java.util.List;
import java.util.Set;

/**
 * @author daiqi
 * @create 2019-02-28 11:27
 */
public interface TreeRelationService<DTO extends TreeRelationDTO> extends BaseStandardService<DTO> {

    /**
     * <p>
     * 新增节点之间的关系
     * </p>
     *
     * @param parentNodeId  : Long : 当前节点父节点id
     * @param currentNodeId : Long : 当前节点的id
     * @param treeNodeType  : Integer : 插入节点的类型
     * @return boolean
     * @author daiqi
     * @date 2018/12/22 15:01
     */
    boolean addNodesRelation(Long parentNodeId, Long currentNodeId, Integer treeNodeType);

    /**
     * <p>
     * 删除节点之间的关系
     * </p>
     *
     * @param currentNodeId
     * @return List<Long>
     * @author daiqi
     * @date 2018/12/23 22:59
     */
    Set<Long> deleteNodesRelation(Long currentNodeId);

    /**
     * <p>
     * 根据祖父id获取该该id节点所有的子孙节点列表数据
     * </p>
     *
     * @param ancestorId
     * @return List
     * @author daiqi
     * @date 2018/12/22 14:03
     */
    List<DTO> listByAncestorId(Long ancestorId);
    /**
     * <p>
     * 根据祖父id列表获取该id列表下所有的子孙节点列表数据
     * </p>
     *
     * @param ancestorIds
     * @return List
     * @author daiqi
     * @date 2018/12/22 14:03
     */
    List<DTO> listByAncestorIds(Set<Long> ancestorIds);

    /**
     * <p>
     * 根据子孙id获取该id所有的祖节点列表数据
     * </p>
     *
     * @param descendantId
     * @return List
     * @author daiqi
     * @date 2018/12/22 14:04
     */
    List<DTO> listByDescendantId(Long descendantId);

    /**
     * <p>
     * 根据祖id获取该id下所有的子孙节点id列表
     * </p>
     *
     * @param ancestorId
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2018/12/22 14:06
     */
    Set<Long> listDescendantIdsByAncestorId(Long ancestorId);

    /**
     * <p>
     * 获取id列表下所有的子孙节点id列表
     * </p>
     *
     * @param ancestorIds
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2019/2/28 11:08
     */
    Set<Long> listDescendantIdsByAncestorIds(Set<Long> ancestorIds);

    /**
     * <p>
     * 根据后代id获取该id上所有的祖父节点id列表
     * </p>
     *
     * @param descendantId
     * @return java.manager.List<java.lang.Set>
     * @author daiqi
     * @date 2018/12/22 14:06
     */
    Set<Long> listAncestorIdsByDescendantId(Long descendantId);

    /**
     * <p>
     * 根据后代id列表获取该id上所有的祖父节点id列表
     * </p>
     *
     * @param descendantIds
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2018/12/24 17:23
     */
    Set<Long> listAncestorIdsByDescendantIds(Set<Long> descendantIds);

    /**
     * <p>
     * 查询id列表中其祖父id列表及其所有的子孙id列表
     * </p>
     *
     * @param descendantIds
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2019/2/28 11:02
     */
    Set<Long> listAncestorIdsAndDescendantIdsByIds(Set<Long> descendantIds);

}
