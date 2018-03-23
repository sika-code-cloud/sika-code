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
public class ${classNameFull} <#if extendsParentClass?exists>extends ${extendsParentClass}</#if> {

<#if fieldDTOs?exists>
    <#list fieldDTOs as model>
    /** ${model.fieldComment} */
    	<#if model.fieldAntations?exists>
	    	<#list model.fieldAntations as fieldAntation>
	${fieldAntation}
	    	</#list>
    	</#if>
    private ${model.fieldType} ${model.fieldName?uncap_first};
    </#list>
</#if>

}