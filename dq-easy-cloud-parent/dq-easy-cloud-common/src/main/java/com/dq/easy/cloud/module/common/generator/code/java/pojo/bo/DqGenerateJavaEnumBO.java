package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaEnumBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaEnumBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, dataBaseConfig, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaModifierDesc> getModifiers() {
		List<DqJavaModifierDesc> modifierDescs = new ArrayList<>();
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.ENUM));
		return modifierDescs;
	}

	@Override
	protected DqGenerateJavaBaseBO buildJavaClassContentOtherData() {
		return null;
	}

	@Override
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		return null;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		return null;
	}
}
