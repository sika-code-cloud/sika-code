package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * java注解逻辑处理类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月28日 上午10:37:32
 */
public abstract class DqGenerateJavaAnnotationBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaAnnotationBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	public DqGenerateJavaAnnotationBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildModifiers() {
		// 设置类的modifier列表---begin
		javaClassContentDesc.addModifier(DqModifierMappingEnum.PUBLIC);
		javaClassContentDesc.addModifier(DqModifierMappingEnum.ANNOTATION);
		// 设置类的modifier列表---end
	}
}
