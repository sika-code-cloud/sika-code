package ${packageNameFull};
<#if importClazzs?exists>
    <#list importClazzs as importClazz>
    	<#if importClazz?exists>
import ${importClazz};
    	</#if>
    </#list>
</#if>

/**
 <#if fileComment?exists>* 描述：${fileComment}模型</#if>
 * @author ${author}
 * @date ${createDate}
 */
@Entity
@Table(name="${tableNameLower}")
public class ${classNameFull} <#if extendsParentClass?exists>extends ${extendsParentClass}</#if> {

<#if fieldDTOs?exists>
    <#list fieldDTOs as model>
    /** ${model.fieldComment} */
    @Column(name = "${model.tableColumnName}",columnDefinition = "${model.tableColumnType}")
    private ${model.fieldType} ${model.fieldName?uncap_first};
    </#list>
</#if>

<#if fieldDTOs?exists>
	<#list fieldDTOs as model>
    public ${model.fieldType} get${model.fieldName}() {
        return this.${model.fieldName?uncap_first};
    }

    public void set${model.fieldName}(${model.fieldType} ${model.fieldName?uncap_first}) {
        this.${model.fieldName?uncap_first} = ${model.fieldName?uncap_first};
    }
    
	</#list>
</#if>
}