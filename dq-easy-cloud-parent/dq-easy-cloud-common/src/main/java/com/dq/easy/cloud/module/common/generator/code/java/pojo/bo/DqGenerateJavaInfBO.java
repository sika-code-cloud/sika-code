package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public abstract class DqGenerateJavaInfBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaInfBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaModifierDesc> getModifiers() {
		List<DqJavaModifierDesc> modifierDescs = new ArrayList<>();
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.INTERFACE));
		return modifierDescs;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getConstructors() {
		return null;
	}

	@Override
	protected List<DqJavaFieldContentDesc> getFields() {
		return null;
	}
}
