package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.List;
import java.util.Set;

import com.dq.easy.cloud.module.common.generator.code.common.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqClassAnnotationDesc;

/**
 * java内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:06:55
 */
public class DqJavaContentDesc extends DqJavaContentBaseDesc {

	/** 注释 */
	private String comment;
	/** 注解列表 */
	private List<DqClassAnnotationDesc> annotations;
	/** 修饰符列表 */
	private List<DqModifierDesc> modifiers;

	public DqJavaContentDesc() {
		super();
	}

	public DqJavaContentDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<DqClassAnnotationDesc> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<DqClassAnnotationDesc> annotations) {
		this.annotations = annotations;
	}

	public List<DqModifierDesc> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<DqModifierDesc> modifiers) {
		this.modifiers = modifiers;
	}
}
