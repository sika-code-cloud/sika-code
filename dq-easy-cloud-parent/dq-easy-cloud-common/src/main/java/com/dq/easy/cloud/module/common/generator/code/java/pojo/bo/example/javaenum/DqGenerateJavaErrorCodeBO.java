package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaenum;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeInf;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaEnumBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成业务逻辑类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaErrorCodeBO extends DqGenerateJavaEnumBO {

	public DqGenerateJavaErrorCodeBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	/** 获取实现的接口列表 */
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		List<DqJavaImplInterfaceContentDesc> implInterfaceContentDescs = new ArrayList<>();
		DqJavaImplInterfaceContentDesc implInterfaceContentDesc = new DqJavaImplInterfaceContentDesc();
		implInterfaceContentDesc.setSimpleClassType(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setName(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setFullClassType(DqBaseErrorCodeInf.class.getName());
		implInterfaceContentDescs.add(implInterfaceContentDesc);
		return implInterfaceContentDescs;
	}

	@Override
	protected List<DqJavaFieldContentDesc> getFields() {
		List<DqJavaFieldContentDesc> fieldContentDescs = new ArrayList<>();
		DqJavaFieldContentDesc codeFieldContentDesc = new DqJavaFieldContentDesc();
		codeFieldContentDesc.setComment("错误代码");
		// 设置code属性的modifiers
		List<DqJavaModifierDesc> codeModifiers = new ArrayList<>();
		DqJavaModifierDesc codeModifierDesc = new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE);
		codeModifiers.add(codeModifierDesc);
		codeFieldContentDesc.setModifiers(codeModifiers);
		// 设置code属性名称和类型
		codeFieldContentDesc.setName("errorCode");
		codeFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		fieldContentDescs.add(codeFieldContentDesc);

		DqJavaFieldContentDesc msgFieldContentDesc = new DqJavaFieldContentDesc();
		msgFieldContentDesc.setComment("错误信息");
		// 设置msg属性的modifiers
		List<DqJavaModifierDesc> msgModifiers = new ArrayList<>();
		DqJavaModifierDesc modifierDesc = new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE);
		msgModifiers.add(modifierDesc);
		msgFieldContentDesc.setModifiers(msgModifiers);
		// 设置code属性名称和类型
		msgFieldContentDesc.setName("errorMsg");
		msgFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		fieldContentDescs.add(msgFieldContentDesc);
		return fieldContentDescs;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getMethods() {
		return super.getMethodsByFields();
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.ERROR_CODE;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.ERROR_CODE;
	}

}
