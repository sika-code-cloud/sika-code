package ${packageNameFull};
<#if importClazzs?exists>
    <#list importClazzs as importClazz>
    	<#if importClazz?exists>
import ${importClazz};
    	</#if>
    </#list>
</#if>

/**
 <#if fileComment?exists>* 描述：${fileComment}数据传输对象</#if>
 * @author ${author}
 * @date ${createDate}
 */
public class ${classNameFull} <#if extendsParentClass?exists>extends ${extendsParentClass} </#if>{

}