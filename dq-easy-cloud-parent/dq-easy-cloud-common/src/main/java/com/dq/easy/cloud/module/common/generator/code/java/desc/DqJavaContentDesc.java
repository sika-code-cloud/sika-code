package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.List;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

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
	private List<DqJavaAnnotationDesc> annotations;
	/** 修饰符列表 */
	private List<DqJavaModifierDesc> modifiers;

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

	public List<DqJavaAnnotationDesc> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<DqJavaAnnotationDesc> annotations) {
		this.annotations = annotations;
	}

	public List<DqJavaModifierDesc> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<DqJavaModifierDesc> modifiers) {
		this.modifiers = modifiers;
	}
	
	public String getModifiersStr() {
		StringBuilder modifiersBuild = DqStringUtils.newStringBuilderDefault();
		if (DqCollectionsUtils.isNotEmpty(getModifiers())) {
			for (int i = 0 ; i < getModifiers().size() ; ++i) {
				modifiersBuild.append(getModifiers().get(i).getDesc()); 
				if (i < getModifiers().size() - 1) {
					modifiersBuild.append(DqSymbol.EMPTY);
				}
			}
		}
		return modifiersBuild.toString();
	}
}
