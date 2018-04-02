package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaInfBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaInfBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	public DqGenerateJavaInfBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildModifiers() {
		javaClassContentDesc.addModifier(DqModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(DqModifierMappingEnum.INTERFACE);
	}

	@Override
	protected void buildExtendsParentClass() {
	}

	@Override
	protected void buildImplementsInterfaces() {
	}

	@Override
	protected void buildConstructors() {
	}

	@Override
	protected void buildFields() {
	}
}
