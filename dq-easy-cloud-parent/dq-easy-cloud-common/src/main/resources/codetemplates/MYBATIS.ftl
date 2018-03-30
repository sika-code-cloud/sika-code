${statement}
${docType}
<#if rootElement?exists>
<${rootElement.elementName} ${rootElement.attributesStr}>
	<#if rootElement.childrenElements?exists>
		<#list rootElement.childrenElements as model>
		<#-- resultMap节点 -->
		<#if model.elementName="resultMap">
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		<#if model.childrenElements?exists>
			<#list model.childrenElements as model>
		<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>/>
			</#list>
		</#if>
	</${model.elementName}>
		</#if>
		
		<#-- sql节点columnList -->
		<#if model.columnList=true>
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		<#if model.text?exists>
		${model.text}
		</#if>
	</${model.elementName}>
		</#if>
		
		</#list>
	</#if>
</${rootElement.elementName}>
</#if>
