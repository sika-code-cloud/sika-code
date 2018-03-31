package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaEnumBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaEnumBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected void buildConstructors() {
		List<DqJavaMethodContentDesc> constructors = super.getConstructorsByFields();
		for (DqJavaMethodContentDesc constructor : constructors) {
			constructor.addModifier(DqModifierMappingEnum.PRIVATE);
		}
		javaClassContentDesc.addConstructors(constructors);
	}

	@Override
	protected void buildModifiers() {
		javaClassContentDesc.addModifier(DqModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(DqModifierMappingEnum.ENUM);
	}

	@Override
	protected void buildAnnotations() {
	}

	@Override
	protected void buildExtendsParentClass() {
	}
}
