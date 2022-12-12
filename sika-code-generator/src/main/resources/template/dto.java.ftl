package ${package.Entity};

import com.sika.check.infrastructure.common.pojo.dto.BaseCheckDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class ${entity} extends BaseCheckDTO {
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
