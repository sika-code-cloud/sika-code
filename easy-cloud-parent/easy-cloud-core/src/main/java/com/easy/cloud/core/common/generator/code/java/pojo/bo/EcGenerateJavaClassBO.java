package com.easy.cloud.core.common.generator.code.java.pojo.bo;

import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

public abstract class EcGenerateJavaClassBO extends EcGenerateJavaBaseBO {

	public EcGenerateJavaClassBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	public EcGenerateJavaClassBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcTemplateDesc templateDesc,
			EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected void buildModifiers() {
		// 设置类的modifier列表---begin
		javaClassContentDesc.addModifier(EcModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(EcModifierMappingEnum.CLASS);
		// 设置类的modifier列表---end
	}
	
}
