package ${package.Entity};

import com.sika.code.demo.infrastructure.common.pojo.dto.BaseBizDTO;
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Getter;
import lombok.Setter;
<#list table.importPackages as pkg>
<#if pkg != 'com.baomidou.mybatisplus.annotation.TableName'>
import ${pkg};
</#if>
</#list>

/**
 * <p>
 * ${table.comment!} 数据传输类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Getter
@Setter
<#if swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}数据传输类")
</#if>
public class ${entity} extends BaseBizDTO {
    private static final long serialVersionUID = 1L;
    <#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    <#if swagger>
    @ApiModelProperty("${field.comment}")
    <#else>
    /**
     * ${field.comment}
     */
    </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
