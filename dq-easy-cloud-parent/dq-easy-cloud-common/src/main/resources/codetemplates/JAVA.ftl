package ${packageName};

<#if importFullClassTypes?exists && (importFullClassTypes?size > 0)>
	<#list importFullClassTypes as model>
		<#if model?exists>
import ${model};
		</#if>
	</#list>

</#if>
/**
 * 描述：<#if comment?exists>${comment}</#if>
 * 
 * @author <#if author?exists>${author}</#if>
 * @date <#if createDateStr?exists>${createDateStr}</#if>
 */
<#if annotations?exists>
	<#list annotations as model>
@${model.simpleClassType}<#if model.paramsStr?exists>(${model.paramsStr})</#if>
	</#list>
</#if>
<#if classHeaderStr?exists>${classHeaderStr}</#if> {
	<#if enum?exists && enum>
	;
	</#if>
	
	<#if fields?exists && generateRule?exists && generateRule.generateField>
		<#list fields as model>
	<#if model.comment?exists && model.comment != "">
	/** ${model.comment} */
	</#if>
		<#if model.annotations?exists>
			<#list model.annotations as model>
	@${model.simpleClassType}<#if model.paramsStr?exists>(${model.paramsStr})</#if>
			</#list>
		</#if>
	<#if model.modifiers?exists><#list model.modifiers as model>${model.desc} </#list></#if>${model.simpleClassType} ${model.name?uncap_first};
		</#list>
		
	</#if>
	<#if constructors?exists>
	<#list constructors as model>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.name?cap_first}(<#if model.argsStr?exists>${model.argsStr}</#if>) {
		<#if model.args?exists>
			<#list model.args as model>
		this.${model.name?uncap_first} = ${model.name?uncap_first};
			</#list>
		<#else>
		
		</#if>
	}
	</#list>
	
	</#if>
	<#if methods?exists && generateRule?exists>
		<#list methods as model>
		<#if model.type == 1  && generateRule.generateGetMethod>
	<#if model.comment?exists && model.comment != "">
	/** 获取${model.comment} */
	</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} get${model.name?cap_first}() {
		return this.${model.name?uncap_first};
	}

		</#if>
		<#if model.type == 2 && generateRule.generateSetMethod>
	<#if model.comment?exists && model.comment != "">
	/** 设置${model.comment} */
	</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} set${model.name?cap_first}(${model.simpleClassType} ${model.name?uncap_first}) {
		this.${model.name?uncap_first} = ${model.name?uncap_first};
	}

		</#if>
		<#if model.type == 3 && generateRule.generateBuildMethod>
	<#if model.comment?exists && model.comment != "">
	/** 构建${model.comment} */
	</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} build${model.name?cap_first}(${model.simpleClassType} ${model.name?uncap_first}) {
		this.${model.name?uncap_first} = ${model.name?uncap_first};
		return this;
	}

		</#if>
		</#list>
	</#if>
}
