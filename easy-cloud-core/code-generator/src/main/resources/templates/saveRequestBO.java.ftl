package ${package.SaveRequestBO};

import ${package.DTO}.${table.classBodyName}DTO;

import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}AlterRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.save.${table.classBodyName}SaveResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 保存请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${table.classBodyName}SaveRequestBO extends ${table.classBodyName}AlterRequestBO<${table.classBodyName}SaveResponseBO> {
    /**
    * 保存请求对象
    */
    private ${table.classBodyName}SaveRequest ${table.classBodyName?uncap_first};

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected ${table.classBodyName}SaveResponseBO doExecute() {
        // 转换
        ${table.classBodyName}DTO ${table.classBodyName?uncap_first}ForSave = convert().convertToDTO(this.${table.classBodyName?uncap_first});
        // 保存
        boolean success = service().save(${table.classBodyName?uncap_first}ForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<${table.classBodyName}SaveResponseBO> responseClass() {
        return ${table.classBodyName}SaveResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}SaveRequest extends ${table.classBodyName}AlterRequest {

    }
}