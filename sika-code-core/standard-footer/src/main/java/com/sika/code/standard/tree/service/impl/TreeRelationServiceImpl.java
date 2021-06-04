package com.sika.code.standard.tree.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import com.sika.code.standard.tree.service.TreeRelationService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.tree.constant.TreeNodeType;
import com.sika.code.database.mysql.interceptor.SqlLogInterceptor;
import com.sika.code.standard.tree.entity.TreeRelationEntity;
import com.sika.code.standard.tree.pojo.dto.TreeRelationDTO;
import com.sika.code.standard.tree.pojo.query.TreeRelationQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author daiqi
 * @create 2019-02-28 11:27
 */
public abstract class TreeRelationServiceImpl<M extends BaseStandardMapper<Entity>, Entity extends TreeRelationEntity, DTO extends TreeRelationDTO> extends BaseStandardServiceImpl<M, Entity, DTO> implements TreeRelationService<DTO> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addNodesRelation(Long parentNodeId, Long currentNodeId, Integer treeNodeType) {
        List<DTO> relationDTOS = Lists.newArrayList();
        // 新增当前插入节点自身的关系
        relationDTOS.add(newDTO(currentNodeId, currentNodeId));
        // 查询以parentId为子节点的所有数据列表
        Set<Long> idsByDescendantIdForParentId = listAncestorIdsByDescendantId(parentNodeId);
        // 绑定当前节点id与以parentId为子孙节点的节点关系
        if (CollUtil.isNotEmpty(idsByDescendantIdForParentId)) {
            idsByDescendantIdForParentId.stream()
                    .forEach(id -> relationDTOS.add(newDTO(id, currentNodeId)));
        }
        // 如果是普通节点，则需要新增以parentId为父节点的所有子孙节点与该节点的关系
        if (TreeNodeType.isCommonNode(treeNodeType)) {
            relationDTOS.addAll(buildDescendantIdsByAncestorId(parentNodeId, currentNodeId));
        }
        // 批量插入
        return saveForBatch(relationDTOS);
    }

    /**
     * <p>
     * 根据祖节点id和字段节点id生成树关系对象
     * </p>
     *
     * @param ancestorId   祖id
     * @param descendantId 子孙id
     * @return DTO
     * @author daiqi
     * @date 2019/2/28 11:52
     */
    protected abstract DTO newDTO(Long ancestorId, Long descendantId);

    @Override
    public Set<Long> deleteNodesRelation(Long currentNodeId) {
        // 查询当前节点所有的子孙节点
        Set<Long> listDescendantIds = listDescendantIdsByAncestorId(currentNodeId);
        if (CollUtil.isEmpty(listDescendantIds)) {
            return Sets.newHashSet();
        }
        // 删除与该节点的子孙节点关系
        deleteByDescendantIds(listDescendantIds);
        return listDescendantIds.stream().collect(Collectors.toSet());
    }

    /**
     * <p>
     * 构建子孙节点列表
     * </p>
     *
     * @param parentNodeId
     * @param currentNodeId
     * @return java.manager.List<com.sika.code.central.oauth.business.systemmenunoderelation.pojo.pojo.DTO>
     * @author daiqi
     * @date 2018/12/23 22:24
     */
    private List<DTO> buildDescendantIdsByAncestorId(Long parentNodeId, Long currentNodeId) {
        List<DTO> relationDTOS = Lists.newArrayList();
        Set<Long> descendantIds = listDescendantIdsByAncestorId(parentNodeId);
        if (CollUtil.isNotEmpty(descendantIds)) {
            descendantIds.stream()
                    .forEach(descendantId -> relationDTOS.add(newDTO(currentNodeId, descendantId)));
        }
        return relationDTOS;
    }

    @Override
    public Set<Long> listAncestorIdsByDescendantId(Long descendantId) {
        List<DTO> relationDTOS = listByDescendantId(descendantId);
        return conventForAncestorIds(relationDTOS);
    }

    @Override
    public Set<Long> listAncestorIdsByDescendantIds(Set<Long> descendantIds) {
        List<DTO> relationDTOS = listByDescendantIds(descendantIds);
        return conventForAncestorIds(relationDTOS);
    }

    /**
     * <p>
     * 将列表信息转化为祖父id列表
     * </p>
     *
     * @param relationDTOS
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2019/2/28 11:19
     */
    private Set<Long> conventForAncestorIds(List<DTO> relationDTOS) {
        if (CollUtil.isEmpty(relationDTOS)) {
            return Sets.newLinkedHashSet();
        }
        return relationDTOS.stream()
                .map(DTO::getAncestorId)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public List<DTO> listByAncestorId(Long ancestorId) {
        if (BaseUtil.isNull(ancestorId)) {
            return Lists.newArrayList();
        }
        Set<Long> ancestorIds = Sets.newLinkedHashSet();
        ancestorIds.add(ancestorId);
        return listByAncestorIds(ancestorIds);
    }

    @Override
    public List<DTO> listByAncestorIds(Set<Long> ancestorIds) {
        if (CollUtil.isEmpty(ancestorIds)) {
            return Lists.newArrayList();
        }
        TreeRelationQuery relationQuery = new TreeRelationQuery()
                .setAncestorIds(ancestorIds);
        return listByQuery(relationQuery);
    }

    @Override
    public Set<Long> listDescendantIdsByAncestorId(Long ancestorId) {
        List<DTO> relationDTOS = listByAncestorId(ancestorId);
        return convertForDescendantIds(relationDTOS);
    }

    @Override
    public Set<Long> listDescendantIdsByAncestorIds(Set<Long> ancestorIds) {
        List<DTO> relationDTOS = listByAncestorIds(ancestorIds);
        return convertForDescendantIds(relationDTOS);
    }

    /**
     * <p>
     * 将数据id转化为子孙id列表
     * </p>
     *
     * @param relationDTOS
     * @return java.manager.Set<java.lang.Long>
     * @author daiqi
     * @date 2019/2/28 11:12
     */
    private Set<Long> convertForDescendantIds(List<DTO> relationDTOS) {
        if (CollUtil.isEmpty(relationDTOS)) {
            return Sets.newLinkedHashSet();
        }
        return relationDTOS.stream()
                .map(DTO::getDescendantId)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Long> listAncestorIdsAndDescendantIdsByIds(Set<Long> ids) {
        Set<Long> ancestorIds = listAncestorIdsByDescendantIds(ids);
        Set<Long> descendantIds = listDescendantIdsByAncestorIds(ids);
        Set<Long> ancestorIdsAndDescendantIds = new LinkedHashSet<>(ancestorIds);
        ancestorIdsAndDescendantIds.addAll(descendantIds);
        return ancestorIdsAndDescendantIds;
    }

    @Override
    public List<DTO> listByDescendantId(Long descendantId) {
        if (BaseUtil.isNull(descendantId)) {
            return Lists.newArrayList();
        }
        Set<Long> descendantIds = Sets.newLinkedHashSet();
        descendantIds.add(descendantId);
        return listByDescendantIds(descendantIds);
    }

    public List<DTO> listByDescendantIds(Set<Long> descendantIds) {
        if (CollUtil.isEmpty(descendantIds)) {
            return Lists.newArrayList();
        }
        TreeRelationQuery relationQuery = new TreeRelationQuery()
                .setDescendantIds(descendantIds);
        return listByQuery(relationQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveForBatch(List<DTO> relationDTOS) {
        SqlLogInterceptor.setExecuteSql("skip print  systemMenuNodeRelationMapper.saveListSelective  sql ......");
        return super.saveForBatch(relationDTOS);
    }

    /**
     * <p>
     * 根据子孙节点id列表删除关系数据
     * </p>
     *
     * @param descendantIds
     * @return int
     * @author daiqi
     * @date 2018/12/23 23:21
     */
    private int deleteByDescendantIds(Set<Long> descendantIds) {
        if (CollUtil.isEmpty(descendantIds)) {
            return 0;
        }
        TreeRelationQuery query = new TreeRelationQuery()
                .setDescendantIds(descendantIds);
        getMapper().deleteByQuery(query);
        return 0;
    }

    /**
     * <p>
     * 根据查询条件获取列表数据
     * </p>
     *
     * @param query
     * @return java.manager.List<com.sika.code.central.oauth.business.systemmenunoderelation.pojo.pojo.DTO>
     * @author daiqi
     * @date 2018/12/24 9:45
     */
    private List<DTO> listByQuery(TreeRelationQuery query) {
        List<Entity> entities = getMapper().listByQuery(query);
        if (CollUtil.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return convert().convertToDTOs(entities);
    }
}
