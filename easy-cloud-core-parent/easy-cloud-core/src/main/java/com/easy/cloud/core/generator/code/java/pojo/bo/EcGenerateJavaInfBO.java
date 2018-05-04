package com.easy.cloud.core.generator.code.java.pojo.bo;

import com.easy.cloud.core.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

public abstract class EcGenerateJavaInfBO extends EcGenerateJavaBaseBO {

	public EcGenerateJavaInfBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcTemplateDesc templateDesc,
			EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	public EcGenerateJavaInfBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildModifiers() {
		javaClassContentDesc.addModifier(EcModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(EcModifierMappingEnum.INTERFACE);
	}

}
