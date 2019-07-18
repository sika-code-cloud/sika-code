package com.sika.code.standard.footer.demo.business.accessruletype.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.footer.demo.business.accessruletype.entity.AccessRuleTypeEntity;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO.AccessRuleTypeSaveBatchRequest;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO.AccessRuleTypeSaveRequest;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO.AccessRuleTypeUpdateByIdRequest;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO.AccessRuleTypeListQueryResponse;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO.AccessRuleTypePageQueryResponse;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO.AccessRuleTypeQueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 准入规则类型表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessRuleTypeConvert extends BaseConvert<AccessRuleTypeEntity, AccessRuleTypeDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    AccessRuleTypeConvert INSTANCE = Mappers.getMapper(AccessRuleTypeConvert.class);

    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param accessRuleTypeEntity : 实体对象
     * @return accessRuleTypeDTO : 数据传输对象
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    @Override
    @Mappings({@Mapping(source = "id", target = "accessRuleTypeId")})
    AccessRuleTypeDTO convertToDTO(AccessRuleTypeEntity accessRuleTypeEntity);

    /**
     * <p>
     * 将DTO对象转化为实体对象
     * </p>
     *
     * @param accessRuleTypeDTO : 数据传输对象
     * @return accessRuleTypeEntity : 实体对象
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    @Override
    @Mappings({@Mapping(source = "accessRuleTypeId", target = "id")})
    AccessRuleTypeEntity convertToEntity(AccessRuleTypeDTO accessRuleTypeDTO);

    /**
     * <p>
     * 将保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 保存请求对象
     * @return AccessRuleTypeDTO
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeDTO convertToDTO(AccessRuleTypeSaveRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 批量保存请求对象
     * @return AccessRuleTypeDTO
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeDTO convertToDTO(AccessRuleTypeSaveBatchRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象列表
     * </p>
     *
     * @param requests : 批量保存请求对象列表
     * @return List
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    List<AccessRuleTypeDTO> convertToDTOFromSaveBatchRequests(List<AccessRuleTypeSaveBatchRequest> requests);

    /**
     * <p>
     * 更新请求对象转化为DTO对象
     * </p>
     *
     * @param request : 根据Id更新请求对象
     * @return AccessRuleTypeDTO
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeDTO convertToDTO(AccessRuleTypeUpdateByIdRequest request);

    /**
     * <p>
     * dto转换为分页响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return AccessRuleTypePageItem
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypePageQueryResponse convertToPageQueryResponse(AccessRuleTypeDTO dto);

    /**
     * <p>
     * dto列表转换为分页响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return AccessRuleTypePageItem
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    List<AccessRuleTypePageQueryResponse> convertToPageQueryResponses(List<AccessRuleTypeDTO> dtos);

    /**
     * <p>
     * dto转换为列表响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return AccessRuleTypePageItem
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeListQueryResponse convertToListQueryResponse(AccessRuleTypeDTO dto);

    /**
     * <p>
     * dto列表转换为列表响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return AccessRuleTypePageItem
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    List<AccessRuleTypeListQueryResponse> convertToListQueryResponses(List<AccessRuleTypeDTO> dtos);

    /**
     * <p>
     * dto转换为查询响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return AccessRuleTypeQueryResponse
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeQueryResponse convertToQueryResponse(AccessRuleTypeDTO dto);

}
