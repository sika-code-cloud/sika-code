package com.easy.cloud.core.common.generator.code.java.pojo.bo;

import java.util.List;

import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaMethodContentDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

public abstract class EcGenerateJavaEnumBO extends EcGenerateJavaBaseBO {

	public EcGenerateJavaEnumBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcTemplateDesc templateDesc,
			EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	public EcGenerateJavaEnumBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildConstructors() {
		List<EcJavaMethodContentDesc> constructors = super.getConstructorsByFields();
		for (EcJavaMethodContentDesc constructor : constructors) {
			constructor.addModifier(EcModifierMappingEnum.PRIVATE);
		}
		javaClassContentDesc.addConstructors(constructors);
	}

	@Override
	protected void buildModifiers() {
		javaClassContentDesc.addModifier(EcModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(EcModifierMappingEnum.ENUM);
	}

	@Override
	protected void buildAnnotations() {
	}

	@Override
	protected void buildExtendsParentClass() {
	}
}
