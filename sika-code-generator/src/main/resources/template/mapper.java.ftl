package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName};
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sika.check.infrastructure.db.common.mapper.BaseCheckMapper;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} extends BaseCheckMapper<${sikaEntityBodyName}${r","} ${sikaEntityBodyName}Query> {

    @Override
    default Wrapper<${sikaEntityBodyName}> buildQueryWrapper(${sikaEntityBodyName}Query query) {
        LambdaQueryWrapper<${sikaEntityBodyName}> lqw = Wrappers.lambdaQuery();
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
        lqw.eq(query.get${field.propertyName?cap_first}() != null, ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        lqw.eq(query.get${field.propertyName?cap_first}() != null, ${sikaEntityBodyName}::get${field.propertyName?cap_first}, query.get${field.propertyName?cap_first}());
</#list>
<#------------  END 字段循环遍历  ---------->
        return lqw;
   }
}
