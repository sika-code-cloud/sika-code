package ${package.Entity};
import lombok.Getter;
import lombok.Setter;
<#list table.importPackages as pkg>
<#if pkg != 'com.baomidou.mybatisplus.annotation.TableName'>
import ${pkg};
</#if>
</#list>
import ${sikaPackage.Entity}.${sikaEntityBodyName};
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sika.check.infrastructure.db.common.mapper.BaseCheckMapper;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * ${table.comment!} 值对象类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Getter
@Setter
public class ${entity} {
    private static final long serialVersionUID = 1L;
    public Wrapper<${sikaEntityBodyName}> buildQueryWrapper(${sikaEntityBodyName}Query query) {
        LambdaQueryWrapper<${sikaEntityBodyName}> lqw = Wrappers.lambdaQuery();
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.columnType == 'STRING'>
        lqw.eq(StrUtil.isNotBlank(query.get${field.propertyName?cap_first}()), ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
    </#if>
        lqw.eq(query.get${field.propertyName?cap_first}() != null, ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
    <#else>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <#if field.columnType == 'STRING'>
        lqw.eq(StrUtil.isNotBlank(query.get${field.propertyName?cap_first}()), ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
    </#if>
        lqw.eq(query.get${field.propertyName?cap_first}() != null, ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
    <#else>
</#list>
<#------------  END 字段循环遍历  ---------->
        return lqw;
    }
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
 <#if field.comment!?length gt 0>
   /**
    * ${field.comment}
    */
 </#if>
    private ${field.propertyType} ${field.propertyName};
    // ${field.columnType}
</#list>
<#------------  END 字段循环遍历  ---------->
}
