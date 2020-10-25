package ${package.UpdateResponseBO};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.${table.classBodyName}AlterResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 更新响应类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@Accessors(chain = true)
public class ${table.classBodyName}UpdateResponseBO extends ${table.classBodyName}AlterResponseBO {

}