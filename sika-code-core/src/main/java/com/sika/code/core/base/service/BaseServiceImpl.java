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

public abstract class BaseServiceImpl<PRIMARY extends Serializable, PO extends BasePO, DTO extends BaseDTO, Repository extends BaseRepository<PO>> implements BaseService<PRIMARY, DTO> {
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
        PO po = getRepository().selectById(primaryKey);
        return convertToDto(po);
    }

    @Override
    public DTO insertAndRet(DTO dto) {
        PO po = convertToPo(dto);
        getRepository().insert(po);
        return convertToDto(po);
    }

    @Override
    public boolean insert(DTO dto) {
        PO po = convertToPo(dto);
        int count = getRepository().insert(po);
        return count > 0;
    }

    @Override
    public List<DTO> insertBatchAndRet(List<DTO> dtos) {
        List<PO> pos = convertToPos(dtos);
        boolean flag = getRepository().insertBatch(pos);
        if (!flag) {
            throw new BusinessException("批量保存失败");
        }
        return convertToDtos(pos);
    }

    @Override
    public boolean insertBatch(List<DTO> dtos) {
        List<PO> pos = convertToPos(dtos);
        return getRepository().insertBatch(pos);
    }

    @Override
    public <QUERY extends BaseQuery> DTO find(QUERY query) {
        PO po = getRepository().find(query);
        return convertToDto(po);
    }


    @Override
    public <QUERY extends BaseQuery> List<DTO> list(QUERY query) {
        List<PO> pos = getRepository().list(query);
        return convertToDtos(pos);
    }

    @Override
    public <QUERY extends PageQuery> Page<DTO> page(QUERY query) {
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
