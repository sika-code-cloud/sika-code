package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaEnumBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaEnumBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaMethodContentDesc> getConstructors() {
		List<DqJavaMethodContentDesc> constructors = super.getConstructorsByFields();
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE));
		for (DqJavaMethodContentDesc constructor : constructors) {
			constructor.setModifiers(modifiers);
		}
		return constructors;
	}

	@Override
	protected List<DqJavaModifierDesc> getModifiers() {
		List<DqJavaModifierDesc> modifierDescs = new ArrayList<>();
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.ENUM));
		return modifierDescs;
	}

	@Override
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		return null;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		return null;
	}
}
