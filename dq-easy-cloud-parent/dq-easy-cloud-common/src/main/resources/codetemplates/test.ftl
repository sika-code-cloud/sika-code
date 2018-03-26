package ${packageName};

<#if importFullClassTypes?exists>
	<#list importFullClassTypes as model>
		<#if model?exists>
import ${model};
		</#if>
	</#list>
</#if>
<#assign classSimpleClassType="UserDO">
/**
 * 描述：<#if comment?exists>${comment}</#if>
 * @author <#if author?exists>${author}</#if>
 * @date <#if createDateStr?exists>${createDateStr}</#if>
 */
<#if annotations?exists>
	<#list annotations as model>
@${model.simpleClassType}<#if model.paramsStr?exists>(${model.paramsStr})</#if>
	</#list>
</#if>
<#if classHeaderStr?exists>${classHeaderStr}</#if>{
	<#if fields?exists && generateRule.generateField>
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
	
	<#if methods?exists && generateRule?exists>
		<#list methods as model>
		<#if model.type == 1  && generateRule.generateGetMethod>
	<#if model.comment?exists>/** 获取${model.comment} */</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} get${model.name?cap_first}() {
		return this.${model.name};
	}
		</#if>
		<#if model.type == 2 && generateRule.generateSetMethod>
	<#if model.comment?exists>/** 设置${model.comment} */</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} set${model.name?cap_first}(${model.simpleClassType} ${model.name}) {
		this.${model.name} = ${model.name};
	}
		</#if>
		<#if model.type == 3 && generateRule.generateBuildMethod>
	<#if model.comment?exists>/** 构建${model.comment} */</#if>
	<#if model.modifiersStr?exists>${model.modifiersStr} </#if>${model.returnSimpleClassType} build${model.name?cap_first}(${model.simpleClassType} ${model.name}) {
		this.${model.name} = ${model.name};
		return this;
	}
		</#if>
		
		</#list>
	</#if>
}
