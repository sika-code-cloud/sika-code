package com.sika.code.core.base.service;

import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.core.base.pojo.dto.BaseDTO;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.repository.BaseRepository;
import com.sika.code.core.base.util.ScReflectUtil;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.Page;
import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.core.exception.BusinessException;
import com.sika.code.core.util.BeanUtil;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<PRIMARY extends Serializable, PO extends BasePO<PRIMARY>, DTO extends BaseDTO<PRIMARY>, Repository extends BaseRepository<PO, PRIMARY>> implements BaseService<PRIMARY, DTO> {
    protected Class<PO> poClass = currentPoClass();
    protected Class<DTO> dtoClass = currentDtoClass();
    protected Class<Repository> repositoryClass = currentRepositoryClass();
    protected Repository repository = currentRepository();

    private Class<PO> currentPoClass() {
        return getSuperClassGenericType(1);
    }

    private Class<DTO> currentDtoClass() {
        return getSuperClassGenericType(2);
    }

    private Class<Repository> currentRepositoryClass() {
        return getSuperClassGenericType(3);
    }

    private <T> Class<T> getSuperClassGenericType(int index) {
        return (Class<T>) ScReflectUtil.getSuperClassGenericType(this.getClass(), BaseServiceImpl.class, index);
    }

    protected Repository currentRepository() {
        return SpringUtil.getBean(currentRepositoryClass());
    }

    protected Class<PO> getPoClass() {
        return this.poClass;
    }

    protected Class<DTO> getDtoClass() {
        return this.dtoClass;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected DTO convertToDto(PO po) {
        return BeanUtil.toBean(po, getDtoClass());
    }

    protected PO convertToPo(DTO DTO) {
        return BeanUtil.toBean(DTO, getPoClass());
    }

    protected List<DTO> convertToDtos(List<PO> pos) {
        return BeanUtil.toBeans(pos, getDtoClass());
    }

    protected List<PO> convertToPos(List<DTO> DTOS) {
        return BeanUtil.toBeans(DTOS, getPoClass());
    }

    @Override
    public DTO findByPrimaryKey(PRIMARY primaryKey) {
        PO po = getRepository().findByPrimaryKey(primaryKey);
        return convertToDto(po);
    }

    @Override
    public DTO saveAndRet(DTO dto) {
        PO po = convertToPo(dto);
        getRepository().saveRetId(po);
        return convertToDto(po);
    }

    @Override
    public boolean save(DTO dto) {
        PO po = convertToPo(dto);
        int count = getRepository().save(po);
        return count > 0;
    }

    @Override
    public List<DTO> saveBatchAndRet(List<DTO> dtos) {
        List<PO> pos = convertToPos(dtos);
        int count = getRepository().saveBatch(pos);
        if (count == 0) {
            throw new BusinessException("批量保存失败");
        }
        return convertToDtos(pos);
    }

    @Override
    public boolean saveBatch(List<DTO> dtos) {
        List<PO> pos = convertToPos(dtos);
        int count = getRepository().saveBatch(pos);
        return count > 0;
    }

    @Override
    public <QUERY extends BaseQuery<PRIMARY>> DTO find(QUERY query) {
        PO po = getRepository().find(query);
        return convertToDto(po);
    }

    @Override
    public <QUERY extends BaseQuery<PRIMARY>> PRIMARY findId(QUERY query) {
        return getRepository().findId(query);
    }

    @Override
    public <QUERY extends BaseQuery<PRIMARY>> List<DTO> list(QUERY query) {
        List<PO> pos = getRepository().list(query);
        return convertToDtos(pos);
    }

    @Override
    public <QUERY extends BaseQuery<PRIMARY>> List<PRIMARY> listId(QUERY query) {
        return getRepository().listId(query);
    }

    @Override
    public <QUERY extends PageQuery<PRIMARY>> Page<DTO> page(QUERY query) {
        int totalCount = getRepository().count(query);
        List<DTO> DTOS;
        if (totalCount == 0) {
            DTOS = Lists.newArrayList();
        } else {
            List<PO> pos = getRepository().page(query);
            DTOS = convertToDtos(pos);
        }
        return new Page<>(query, totalCount, DTOS);
    }
}
