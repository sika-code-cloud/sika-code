package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * java注解逻辑处理类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月28日 上午10:37:32
 */
public abstract class DqGenerateJavaAnnotationBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaAnnotationBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaModifierDesc> getModifiers() {
		// 设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.ANNOTATION));
		return modifiers;
		// 设置类的modifier列表---end
	}
}
