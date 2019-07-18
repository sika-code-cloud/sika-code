package ${package.AlterRequestBO};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.sika.code.standard.base.pojo.bo.request.BaseStandardAlterRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.${table.classBodyName}AlterResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 修改请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class ${table.classBodyName}AlterRequestBO<ResponseBO extends ${table.classBodyName}AlterResponseBO> extends BaseStandardAlterRequestBO<ResponseBO> implements ${table.classBodyName}Domain {

    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}AlterRequest extends BaseStandardAlterRequest {
    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        /**
         * ${field.comment}
         */
        private ${field.propertyType} ${field.propertyName};
    </#list>
    <#------------  END 字段循环遍历  ---------->
    }
}
