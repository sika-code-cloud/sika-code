package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.convert;

import com.easy.cloud.standard.base.convert.BaseConvert;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.entity.CreditQuotaUsageTemplateEntity;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveBatchRequestBO.CreditQuotaUsageTemplateSaveBatchRequest;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveRequestBO.CreditQuotaUsageTemplateSaveRequest;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.update.CreditQuotaUsageTemplateUpdateByIdRequestBO.CreditQuotaUsageTemplateUpdateByIdRequest;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateListQueryResponseBO.CreditQuotaUsageTemplateListQueryResponse;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplatePageQueryResponseBO.CreditQuotaUsageTemplatePageQueryResponse;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateQueryResponseBO.CreditQuotaUsageTemplateQueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 额度模板类型配置 转换类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditQuotaUsageTemplateConvert extends BaseConvert<CreditQuotaUsageTemplateEntity, CreditQuotaUsageTemplateDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    CreditQuotaUsageTemplateConvert INSTANCE = Mappers.getMapper(CreditQuotaUsageTemplateConvert.class);

    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param creditQuotaUsageTemplateEntity : 实体对象
     * @return creditQuotaUsageTemplateDTO : 数据传输对象
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    @Override
    @Mappings({@Mapping(source = "id", target = "creditQuotaUsageTemplateId")})
    CreditQuotaUsageTemplateDTO convertToDTO(CreditQuotaUsageTemplateEntity creditQuotaUsageTemplateEntity);

    /**
     * <p>
     * 将DTO对象转化为实体对象
     * </p>
     *
     * @param creditQuotaUsageTemplateDTO : 数据传输对象
     * @return creditQuotaUsageTemplateEntity : 实体对象
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    @Override
    @Mappings({@Mapping(source = "creditQuotaUsageTemplateId", target = "id")})
    CreditQuotaUsageTemplateEntity convertToEntity(CreditQuotaUsageTemplateDTO creditQuotaUsageTemplateDTO);

    /**
     * <p>
     * 将保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 保存请求对象
     * @return CreditQuotaUsageTemplateDTO
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    CreditQuotaUsageTemplateDTO convertToDTO(CreditQuotaUsageTemplateSaveRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 批量保存请求对象
     * @return CreditQuotaUsageTemplateDTO
     * @author daiqi
     * @date 2019/6/19 20:29
     */
    CreditQuotaUsageTemplateDTO convertToDTO(CreditQuotaUsageTemplateSaveBatchRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象列表
     * </p>
     *
     * @param requests : 批量保存请求对象列表
     * @return List
     * @author daiqi
     * @date 2019/6/19 20:29
     */
    List<CreditQuotaUsageTemplateDTO> convertToDTOFromSaveBatchRequests(List<CreditQuotaUsageTemplateSaveBatchRequest> requests);

    /**
     * <p>
     * 更新请求对象转化为DTO对象
     * </p>
     *
     * @param request : 根据Id更新请求对象
     * @return CreditQuotaUsageTemplateDTO
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    CreditQuotaUsageTemplateDTO convertToDTO(CreditQuotaUsageTemplateUpdateByIdRequest request);

    /**
     * <p>
     * dto转换为分页响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return CreditQuotaUsageTemplatePageItem
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    CreditQuotaUsageTemplatePageQueryResponse convertToPageQueryResponse(CreditQuotaUsageTemplateDTO dto);

    /**
     * <p>
     * dto列表转换为分页响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return CreditQuotaUsageTemplatePageItem
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    List<CreditQuotaUsageTemplatePageQueryResponse> convertToPageQueryResponses(List<CreditQuotaUsageTemplateDTO> dtos);

    /**
     * <p>
     * dto转换为列表响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return CreditQuotaUsageTemplatePageItem
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    CreditQuotaUsageTemplateListQueryResponse convertToListQueryResponse(CreditQuotaUsageTemplateDTO dto);

    /**
     * <p>
     * dto列表转换为列表响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return CreditQuotaUsageTemplatePageItem
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    List<CreditQuotaUsageTemplateListQueryResponse> convertToListQueryResponses(List<CreditQuotaUsageTemplateDTO> dtos);

    /**
     * <p>
     * dto转换为查询响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return CreditQuotaUsageTemplateQueryResponse
     * @author daiqi
     * @date 2019-06-07 10:12:50
     */
    CreditQuotaUsageTemplateQueryResponse convertToQueryResponse(CreditQuotaUsageTemplateDTO dto);

}
