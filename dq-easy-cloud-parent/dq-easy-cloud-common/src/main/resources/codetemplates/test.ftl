package ${packageName};

<#if importClassNames?exists>
	<#list importClassNames as model>
import ${model};
	</#list>
</#if>

/**
 * 描述：${comment} 
 */
<#if annotations?exists>
	<#list annotations as model>
@${model.simpleClassType}<#if model.paramsStr?exists>(${model.paramsStr})</#if>
	</#list>
</#if>
<#if modifiers?exists><#list modifiers as model>${model.desc} </#list></#if>${name} <#if extendsParentClass?exists>extends ${extendsParentClass.simpleClassType}</#if><#if implementsInterfacesStr?exists> implements ${implementsInterfacesStr}</#if> {

}
