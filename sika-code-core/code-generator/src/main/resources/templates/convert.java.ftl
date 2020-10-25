package ${package.Convert};

import com.sika.code.standard.base.convert.BaseConvert;
import ${package.Entity}.${entity};
import ${package.DTO}.${table.classBodyName}DTO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveBatchRequestBO.${table.classBodyName}SaveBatchRequest;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveRequestBO.${table.classBodyName}SaveRequest;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.update.${table.classBodyName}UpdateByIdRequestBO.${table.classBodyName}UpdateByIdRequest;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}ListQueryResponseBO.${table.classBodyName}ListQueryResponse;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}PageQueryResponseBO.${table.classBodyName}PageQueryResponse;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}QueryResponseBO.${table.classBodyName}QueryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 转换类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${table.classBodyName}Convert extends BaseConvert<${table.entityName}, ${table.classBodyName}DTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ${table.classBodyName}Convert INSTANCE = Mappers.getMapper(${table.classBodyName}Convert.class);

    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param ${table.entityName?uncap_first} : 实体对象
     * @return ${table.classBodyName?uncap_first}DTO : 数据传输对象
     * @author ${author}
     * @date ${cfg.date}
     */
    @Override
    @Mappings({@Mapping(source = "id", target = "${table.classBodyName?uncap_first}Id")})
    ${table.classBodyName}DTO convertToDTO(${table.entityName} ${table.entityName?uncap_first});

    /**
     * <p>
     * 将DTO对象转化为实体对象
     * </p>
     *
     * @param ${table.classBodyName?uncap_first}DTO : 数据传输对象
     * @return ${table.entityName?uncap_first} : 实体对象
     * @author ${author}
     * @date ${cfg.date}
     */
    @Override
    @Mappings({@Mapping(source = "${table.classBodyName?uncap_first}Id", target = "id")})
    ${table.entityName} convertToEntity(${table.classBodyName}DTO ${table.classBodyName?uncap_first}DTO);

    /**
     * <p>
     * 将保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 保存请求对象
     * @return ${table.classBodyName}DTO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}DTO convertToDTO(${table.classBodyName}SaveRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象
     * </p>
     *
     * @param request : 批量保存请求对象
     * @return ${table.classBodyName}DTO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}DTO convertToDTO(${table.classBodyName}SaveBatchRequest request);

    /**
     * <p>
     * 将批量保存请求对象转化为DTO对象列表
     * </p>
     *
     * @param requests : 批量保存请求对象列表
     * @return List
     * @author ${author}
     * @date ${cfg.date}
     */
    List<${table.classBodyName}DTO> convertToDTOFromSaveBatchRequests(List<${table.classBodyName}SaveBatchRequest> requests);

    /**
     * <p>
     * 更新请求对象转化为DTO对象
     * </p>
     *
     * @param request : 根据Id更新请求对象
     * @return ${table.classBodyName}DTO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}DTO convertToDTO(${table.classBodyName}UpdateByIdRequest request);

    /**
     * <p>
     * dto转换为分页响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return ${table.classBodyName}PageItem
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}PageQueryResponse convertToPageQueryResponse(${table.classBodyName}DTO dto);

    /**
     * <p>
     * dto列表转换为分页响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return ${table.classBodyName}PageItem
     * @author ${author}
     * @date ${cfg.date}
     */
    List<${table.classBodyName}PageQueryResponse> convertToPageQueryResponses(List<${table.classBodyName}DTO> dtos);

    /**
     * <p>
     * dto转换为列表响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return ${table.classBodyName}PageItem
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}ListQueryResponse convertToListQueryResponse(${table.classBodyName}DTO dto);

    /**
     * <p>
     * dto列表转换为列表响应对象列表
     * </p>
     *
     * @param dtos : 数据传输对象列表
     * @return ${table.classBodyName}PageItem
     * @author ${author}
     * @date ${cfg.date}
     */
    List<${table.classBodyName}ListQueryResponse> convertToListQueryResponses(List<${table.classBodyName}DTO> dtos);

    /**
     * <p>
     * dto转换为查询响应对象
     * </p>
     *
     * @param dto : 数据传输对象
     * @return ${table.classBodyName}QueryResponse
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}QueryResponse convertToQueryResponse(${table.classBodyName}DTO dto);

}
