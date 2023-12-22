package ${package.Entity};

import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * <p>
 * ${table.comment!} 持久化类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Getter
@Setter
@TableName("${table.name}")
public class ${entity} extends BaseBizPO {
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
