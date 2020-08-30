package com.sika.code.standard.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.sika.code.basic.pojo.query.BaseQuery;
import com.sika.code.basic.service.BaseService;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.database.common.Page;
import com.sika.code.database.common.PageQuery;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.sika.code.standard.base.service.BaseStandardService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public abstract class BaseStandardServiceImpl<M extends BaseStandardMapper<Entity>, Entity extends BaseStandardEntity, DTO extends BaseStandardDTO> extends BaseService<M, Entity> implements BaseStandardService<DTO> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DTO saveAndRet(DTO dto) {
        Entity entity = convertToEntity(dto);
        boolean success = super.save(entity);
        if (!success) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DTO dto) {
        return super.save(convertToEntity(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DTO> saveForBatchAndRet(List<DTO> dtos) {
        List<Entity> entities = convertToEntities(dtos);
        boolean success = super.saveBatch(entities);
        if (!success) {
            return Lists.newArrayList();
        }
        return convertToDTOs(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveForBatch(List<DTO> dtos) {
        return super.saveBatch(convertToEntities(dtos));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DTO updateAndRet(DTO dto) {
        Entity entity = convertToEntity(dto);
        boolean update = super.updateById(entity);
        if (!update) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(DTO dto) {
        return super.updateById(convertToEntity(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DTO> updateForBatchAndRet(List<DTO> dtos) {
        List<Entity> entities = convertToEntities(dtos);
        boolean success = super.updateBatchById(entities);
        if (!success) {
            return Lists.newArrayList();
        }
        return convertToDTOs(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateForBatch(List<DTO> dtos) {
        return super.updateBatchById(convertToEntities(dtos));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DTO saveOrUpdateAndRet(DTO dto) {
        Entity entity = convertToEntity(dto);
        boolean success = super.saveOrUpdate(entity);
        if (!success) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(DTO dto) {
        return super.saveOrUpdate(convertToEntity(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DTO> saveOrUpdateForBatchAndRet(List<DTO> dtos) {
        List<Entity> entities = convertToEntities(dtos);
        boolean success = super.saveOrUpdateBatch(entities);
        if (!success) {
            return Lists.newArrayList();
        }
        return convertToDTOs(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateForBatch(List<DTO> dtos) {
        return super.saveOrUpdateBatch(convertToEntities(dtos));
    }

    @Override
    public <QUERY extends BaseQuery> DTO find(QUERY query) {
        Entity entity = getMapper().findByQuery(query);
        if (BaseUtil.isNull(entity)) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public <QUERY extends BaseQuery> Long findId(QUERY query) {
        verifyQuery(query);
        return getMapper().findIdByQuery(query);
    }

    @Override
    public <QUERY extends BaseQuery> List<DTO> list(QUERY query) {
        verifyQuery(query);
        return convertToDTOs(getMapper().listByQuery(query));
    }

    @Override
    public <QUERY extends BaseQuery> List<Long> listId(QUERY query) {
        verifyQuery(query);
        return getMapper().listIdByQuery(query);
    }

    @Override
    public <QUERY extends PageQuery> Page<DTO> page(QUERY query) {
        verifyQuery(query);
        int totalCount = totalCountByQuery(query);
        List<DTO> dtos;
        if (totalCount == 0) {
            dtos = Lists.newArrayList();
        } else {
            dtos = pageByQuery(query);
        }
        return new Page<>(query, totalCount, dtos);
    }

    @Override
    public boolean exist(Long id) {
        Assert.verifyObjNull(id, "数据id");
        return BaseUtil.isNotNull(getById(id));
    }

    @Override
    public boolean notExist(Long id) {
        return !exist(id);
    }

    @Override
    public <QUERY extends BaseQuery> boolean exist(QUERY query) {
        verifyQuery(query);
        return BaseUtil.isNotNull(findId(query));
    }

    @Override
    public <QUERY extends BaseQuery> boolean notExist(QUERY query) {
        return !exist(query);
    }


    @Override
    public <QUERY extends BaseQuery> void verifyNotExist(QUERY query) {
        Assert.verifyDataNotExistent(notExist(query), "查询条件对应的数据");
    }

    @Override
    public <QUERY extends BaseQuery> void verifyExist(QUERY query) {
        Assert.verifyDataExistent(exist(query), "查询条件对应的数据");
    }


    @Override
    public void verifyNotExist(Long id) {
        Assert.verifyDataNotExistent(notExist(id), id + "对应的数据");
    }

    @Override
    public void verifyExist(Long id) {
        Assert.verifyDataExistent(exist(id), id + "对应的数据");
    }

    /**
     * <p>
     * 根据查询条件对象获取DTO分页列表
     * </p>
     *
     * @param query : 查询对象
     * @return DTO
     * @author daiqi
     * @date 2018/12/3 16:58
     */
    private <QUERY extends BaseQuery> List<DTO> pageByQuery(QUERY query) {
        return convertToDTOs(getMapper().pageByQuery(query));
    }

    /**
     * <p>
     * 根据查询条件获取符合条件的数据总条数
     * </p>
     *
     * @param query : 查询对象
     * @return int
     * @author daiqi
     * @date 2018/12/6 11:54
     */
    private <QUERY extends BaseQuery> int totalCountByQuery(QUERY query) {
        return getMapper().totalCountByQuery(query);
    }

    /**
     * dto转entity
     */
    private Entity convertToEntity(DTO dto) {
        Entity entity = convert().convertToEntity(dto);
        return entity;
    }

    protected void buildCreateDate(Entity entity) {
        if (entity.getCreateDate() == null) {
            entity.setCreateDate(new Date());
        }
    }

    protected void buildUpdateDate(Entity entity) {
        if (entity.getUpdateDate() == null) {
            entity.setUpdateDate(new Date());
        }
    }

    /**
     * entity转dto
     */
    private DTO convertToDTO(Entity entity) {
        return convert().convertToDTO(entity);
    }

    /**
     * 转化为entity列表
     */
    private List<Entity> convertToEntities(List<DTO> dtos) {
        List<Entity> entities = convert().convertToEntities(dtos);
        if (CollUtil.isEmpty(entities)) {
            return entities;
        }
        return entities;
    }

    protected void buildCreateOrUpdateDate(Entity entity) {
        if (entity.getId() == null) {
            buildCreateDate(entity);
        }
        buildUpdateDate(entity);
    }

    /**
     * 转化为dto列表
     */
    private List<DTO> convertToDTOs(List<Entity> entities) {
        return convert().convertToDTOs(entities);
    }

    /**
     * <p>
     * 获取mapper对象
     * </p>
     *
     * @return BaseMapper
     * @author daiqi
     * @date 2018/12/3 16:40
     */
    protected BaseStandardMapper<Entity> getMapper() {
        return getBaseMapper();
    }

    private <QUERY extends BaseQuery> void verifyQuery(QUERY query) {
        Assert.verifyObjNull(query, "查询对象");
    }

    /**
     * <p>
     * 获取转化实现类
     * </p>
     *
     * @return com.sika.code.central.base.convert.BaseConvert<Entity, DTO>
     * @author daiqi
     * @date 2019/4/1 21:18
     */
    protected abstract BaseConvert<Entity, DTO> convert();

}
