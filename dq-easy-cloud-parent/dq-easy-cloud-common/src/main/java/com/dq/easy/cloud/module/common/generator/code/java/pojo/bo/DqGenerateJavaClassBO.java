package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaClassBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaClassBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected void buildModifiers() {
		// 设置类的modifier列表---begin
		javaClassContentDesc.addModifier(DqModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(DqModifierMappingEnum.CLASS);
		// 设置类的modifier列表---end
	}
}
