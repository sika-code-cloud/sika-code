package com.easy.cloud.core.common.generator.code.java.pojo.bo;

import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * java注解逻辑处理类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月28日 上午10:37:32
 */
public abstract class EcGenerateJavaAnnotationBO extends EcGenerateJavaBaseBO {

	public EcGenerateJavaAnnotationBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcTemplateDesc templateDesc,
			EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	public EcGenerateJavaAnnotationBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildModifiers() {
		// 设置类的modifier列表---begin
		javaClassContentDesc.addModifier(EcModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(EcModifierMappingEnum.ANNOTATION);
		// 设置类的modifier列表---end
	}
}
