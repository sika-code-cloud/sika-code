package ${package.Entity};

import com.sika.check.infrastructure.common.pojo.query.BaseCheckQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 查询类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ${entity} extends BaseCheckQuery {
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
    private List<${sikaPrimaryType}> ids;
<#------------  END 字段循环遍历  ---------->
}
