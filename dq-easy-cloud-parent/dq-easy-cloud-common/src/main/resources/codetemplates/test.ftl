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
	<#if generateRule?exists && generateRule.generateField>
		<#if fields?exists>
			<#list fields as model>
	/** ${model.comment} */
				<#if model.annotations?exists>
					<#list model.annotations as model>
	@${model.simpleClassType}<#if model.paramsStr?exists>(${model.paramsStr})</#if>
					</#list>
				</#if>
	<#if model.modifiers?exists><#list model.modifiers as model>${model.desc} </#list></#if>${model.simpleClassType} ${model.name};
			</#list>
		</#if>
	</#if>
	
	<#if generateRule?exists && generateRule.generateGetMethod>
		<#if methods?exists>
			<#list methods as model>
			<#if model.type == 1>
	/** ${model.comment} */
	<#if model.modifiers?exists><#list model.modifiers as model>${model.desc} </#list></#if>${model.simpleClassType} get${model.name?cap_first}() {
		return this.${model.name};
	}
			</#if>
			<#if model.type == 2>
	/** ${model.comment} */
	<#if model.modifiers?exists><#list model.modifiers as model>${model.desc} </#list></#if>void set${model.name?cap_first}(${model.simpleClassType} ${model.name}) {
		this.${model.name} = ${model.name};
	}
			</#if>

			</#list>
		</#if>
	</#if>
}
