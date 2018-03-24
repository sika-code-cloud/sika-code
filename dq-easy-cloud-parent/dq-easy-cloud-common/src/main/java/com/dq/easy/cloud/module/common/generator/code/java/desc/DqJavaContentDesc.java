package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.List;
import java.util.Set;

import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqClassAnnotationDesc;

/**
 * java内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:06:55
 */
public class DqJavaContentDesc extends DqJavaContentBaseDesc{
	/** 注释 */
	private String comment;
	/** 注解列表 */
	private Set<DqClassAnnotationDesc> annotations;
	/** 修饰符列表 */
	private List<DqModifierMappingEnum> modifiers;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Set<DqClassAnnotationDesc> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(Set<DqClassAnnotationDesc> annotations) {
		this.annotations = annotations;
	}
	public List<DqModifierMappingEnum> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<DqModifierMappingEnum> modifiers) {
		this.modifiers = modifiers;
	}

}
