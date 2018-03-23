package com.dq.easy.cloud.module.common.generator.code.pojo.dto;

import java.util.List;
import java.util.Set;

import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;

/**
 * 代码生成数据传输对象---java
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:06:55
 */
public class DqCodeGeneratorJavaDTO extends DqCodeGeneratorJavaBaseDTO{
	/** 导入的类名列表 */
	private Set<String> importClassNames;
	/** 类的注释 */
	private String comment;
	/** 注解列表 */
	private Set<DqCodeGeneratorAnnotation> annotations;
	/** 类的修饰符列表 */
	private List<DqModifierMappingEnum> modifiers;
	/** 继承的父类 */
	private DqCodeGeneratorJavaBaseDTO extendsParentClass;
	/** 实现的接口列表 */
	private List<DqCodeGeneratorJavaBaseDTO> implementsInterfaces;
	/** 属性列表 */
	private List<DqFieldBaseDTO> fields;
	public Set<String> getImportClassNames() {
		return importClassNames;
	}
	public void setImportClassNames(Set<String> importClassNames) {
		this.importClassNames = importClassNames;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Set<DqCodeGeneratorAnnotation> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(Set<DqCodeGeneratorAnnotation> annotations) {
		this.annotations = annotations;
	}
	public List<DqModifierMappingEnum> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<DqModifierMappingEnum> modifiers) {
		this.modifiers = modifiers;
	}
	public DqCodeGeneratorJavaBaseDTO getExtendsParentClass() {
		return extendsParentClass;
	}
	public void setExtendsParentClass(DqCodeGeneratorJavaBaseDTO extendsParentClass) {
		this.extendsParentClass = extendsParentClass;
	}
	public List<DqCodeGeneratorJavaBaseDTO> getImplementsInterfaces() {
		return implementsInterfaces;
	}
	public void setImplementsInterfaces(List<DqCodeGeneratorJavaBaseDTO> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
	}

}
