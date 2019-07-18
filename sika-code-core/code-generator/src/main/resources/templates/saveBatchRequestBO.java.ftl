package ${package.SaveBatchRequestBO};

import ${package.DTO}.${table.classBodyName}DTO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}AlterRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.save.${table.classBodyName}SaveResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * <p>
 * ${table.comment!} 批量保存请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${table.classBodyName}SaveBatchRequestBO extends ${table.classBodyName}AlterRequestBO<${table.classBodyName}SaveResponseBO> {
    /**
    * 批量保存请求对象列表
    */
    private List<${table.classBodyName}SaveBatchRequest> ${table.classBodyName?uncap_first}s;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected ${table.classBodyName}SaveResponseBO doExecute() {
        // 转换
        List<${table.classBodyName}DTO> ${table.classBodyName?uncap_first}sForSave = convert().convertToDTOFromSaveBatchRequests(this.${table.classBodyName?uncap_first}s);
        // 保存
        boolean success = service().saveForBatch(${table.classBodyName?uncap_first}sForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<${table.classBodyName}SaveResponseBO> responseClass() {
        return ${table.classBodyName}SaveResponseBO.class;
    }


    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}SaveBatchRequest extends ${table.classBodyName}AlterRequest {

    }
}