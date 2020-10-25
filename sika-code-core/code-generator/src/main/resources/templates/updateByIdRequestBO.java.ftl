package ${package.UpdateByIdRequestBO};

import ${package.DTO}.${table.classBodyName}DTO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}AlterRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.update.${table.classBodyName}UpdateResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 根据id更新请求逻辑类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${table.classBodyName}UpdateByIdRequestBO extends ${table.classBodyName}AlterRequestBO<${table.classBodyName}UpdateResponseBO> {
    /**
    * 根据id更新数据的请求对象
    */
    private ${table.classBodyName}UpdateByIdRequest ${table.classBodyName?uncap_first};

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {
        // 校验数据是否存在，不存在抛出异常
        service().verifyNotExist(this.${table.classBodyName?uncap_first}.get${table.classBodyName}Id());
    }

    @Override
    protected ${table.classBodyName}UpdateResponseBO doExecute() {
        // 转换
        ${table.classBodyName}DTO ${table.classBodyName?uncap_first}ForUpdate = convert().convertToDTO(${table.classBodyName?uncap_first});
        // 执行
        Boolean success = service().update(${table.classBodyName?uncap_first}ForUpdate);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<${table.classBodyName}UpdateResponseBO> responseClass() {
        return ${table.classBodyName}UpdateResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ${table.classBodyName}UpdateByIdRequest extends ${table.classBodyName}AlterRequest {
        /**
         * 表数据id
         */
        private Long ${table.classBodyName?uncap_first}Id;
    }
}