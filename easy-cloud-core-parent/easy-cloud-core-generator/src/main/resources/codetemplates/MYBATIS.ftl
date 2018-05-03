${statement}
${docType}
<#if rootElement?exists>
<${rootElement.elementName} ${rootElement.attributesStr}>
	<!-- 根节点 -->
	<#if rootElement.childrenElements?exists>
		<#list rootElement.childrenElements as model>
		<#-- resultMap节点------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=0>
	<!-- 设置resultMap -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		<#if model.childrenElements?exists>
			<#list model.childrenElements as model>
		<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>/>
			</#list>
	</${model.elementName}>
		</#if>
		</#if>
		<#-- resultMap节点------------------------------------------end -->
		<#-- sql节点columnList------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=1>
	<!-- 设置columnList -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		<#if model.text?exists>
		${model.text}
	</${model.elementName}>
		</#if>
		</#if>
		<#-- sql节点columnList------------------------------------------begin -->
		<#-- select节点findById------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=2>
	<!-- 根据id获取对象信息 -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		SELECT <#if model.childrenElements?exists><#list model.childrenElements as model><${model.elementName} ${model.attributesStr}/></#list></#if> 
		FROM ${model.mybatisDTO.tableName} ${model.mybatisDTO.tableSimpleName} 
		WHERE ${model.whereDatasStr}
	</${model.elementName}>
		</#if>
		<#-- select节点listCount------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=3>
	<!-- 统计列表数量 -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		SELECT count(*)
		FROM ${model.mybatisDTO.tableName} ${model.mybatisDTO.tableSimpleName} 
	</${model.elementName}>
		</#if>
		<#-- select节点listCount------------------------------------------end -->
		<#-- select节点listPage------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=4>
	<!-- 获取列表分页信息 -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		SELECT <#if model.childrenElements?exists><#list model.childrenElements as model><${model.elementName} ${model.attributesStr}/></#list></#if> 
		FROM ${model.mybatisDTO.tableName} ${model.mybatisDTO.tableSimpleName} 
		LIMIT ${r"#{maps.start},#{maps.pageSize}"}
	</${model.elementName}>
		</#if>
		<#-- select节点listPage------------------------------------------end -->
		<#-- select节点setColumnSql------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=5>
	<!-- 设置column -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		<#if model.childrenElements?exists>
		<#list model.childrenElements as model>
		<${model.elementName}>
			<#if model.childrenElements?exists>
			<#list model.childrenElements as model>
			<${model.elementName} ${model.attributesStr}><#if model.text?exists>${model.text}</#if></${model.elementName}>
			</#list>
			</#if>
		</${model.elementName}>
		</#list>
		</#if> 
	</${model.elementName}>
		</#if>
		<#-- sql节点setColumnSql------------------------------------------end -->
		<#-- sql节点save------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=6>
	<!-- 保存对象 -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		INSERT INTO ${model.mybatisDTO.tableName} <#if model.childrenElements?exists><#list model.childrenElements as model><${model.elementName} ${model.attributesStr}/></#list></#if>
	</${model.elementName}>
		</#if>
		<#-- sql节点save------------------------------------------end -->
		<#-- sql节点update------------------------------------------begin -->
		<#if model.sqlType?exists && model.sqlType=7>
	<!-- 更新对象 -->
	<${model.elementName}<#if model.attributesStr?exists> ${model.attributesStr}</#if>>
		UPDATE ${model.mybatisDTO.tableName} ${model.mybatisDTO.tableSimpleName} <#if model.childrenElements?exists><#list model.childrenElements as model><${model.elementName} ${model.attributesStr}/></#list></#if>
		<#if model.whereDatasStr?exists>WHERE ${model.whereDatasStr}</#if>
	</${model.elementName}>
		</#if>
		<#-- sql节点update------------------------------------------end -->
		</#list>
	</#if>
</${rootElement.elementName}>
</#if>
