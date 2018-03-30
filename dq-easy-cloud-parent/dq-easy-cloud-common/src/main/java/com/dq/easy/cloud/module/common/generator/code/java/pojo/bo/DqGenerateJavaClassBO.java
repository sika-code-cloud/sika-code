package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaClassBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaClassBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaModifierDesc> getModifiers() {
		// 设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		return modifiers;
		// 设置类的modifier列表---end
	}
}
