package com.sika.code.standard.base.convert;

import com.sika.code.basic.pojo.dto.BaseDTO;
import com.sika.code.database.common.entity.BaseEntity;

import java.util.List;

/**
 * <p>基础转换类</p>
 * <pre>所有转化类的基础父接口</pre>
 *
 * @author daiqi
 * @create 2019-03-22 14:36
 */
public interface BaseConvert<ENTITY extends BaseEntity, DTO extends BaseDTO> {
    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param entity
     * @return DTO
     * @author daiqi
     * @date 2019/3/29 23:41
     */
    DTO convertToDTO(ENTITY entity);

    /**
     * <p>
     * 将实体对象列表转化为DTO对象列表
     * </p>
     *
     * @param entities
     * @return java.manager.List<DTO>
     * @author daiqi
     * @date 2019/3/29 23:42
     */
    List<DTO> convertToDTOs(List<ENTITY> entities);

    /**
     * <p>
     * 将dto对象转化为实体类
     * </p>
     *
     * @param dto
     * @return ENTITY
     * @author daiqi
     * @date 2019/3/29 23:42
     */
    ENTITY convertToEntity(DTO dto);

    /**
     * <p>
     * 将dto对象列表转化为实体对象列表
     * </p>
     *
     * @param dtos
     * @return java.manager.List<ENTITY>
     * @author daiqi
     * @date 2019/3/29 23:43
     */
    List<ENTITY> convertToEntities(List<DTO> dtos);
}
