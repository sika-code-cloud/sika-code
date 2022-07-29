package ${package.Entity};

import com.sika.code.core.base.pojo.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
/**
 * <p>
 * ${table.comment!} 更新命令类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Getter
@Setter
public class ${entity} extends BaseDTO<${sikaPrimaryType}> {
    private static final long serialVersionUID = 1L;
    <#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
