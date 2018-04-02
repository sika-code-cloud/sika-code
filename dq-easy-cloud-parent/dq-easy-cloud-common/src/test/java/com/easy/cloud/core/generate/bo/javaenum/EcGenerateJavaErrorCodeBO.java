package com.dq.easy.cloud.model.generate.bo.javaenum;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeInf;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
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

	public DqGenerateJavaErrorCodeBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	/** 获取实现的接口列表 */
	protected void buildImplementsInterfaces() {
		DqJavaImplInterfaceContentDesc implInterfaceContentDesc = new DqJavaImplInterfaceContentDesc();
		implInterfaceContentDesc.setSimpleClassType(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setName(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setFullClassType(DqBaseErrorCodeInf.class.getName());
		javaClassContentDesc.addImplementsInterface(implInterfaceContentDesc);
	}

	@Override
	protected void buildFields() {
		DqJavaFieldContentDesc codeFieldContentDesc = new DqJavaFieldContentDesc();
		codeFieldContentDesc.setComment("错误代码");
		// 设置code属性的modifiers
		codeFieldContentDesc.addModifier(DqModifierMappingEnum.PRIVATE);
		// 设置code属性名称和类型
		codeFieldContentDesc.setName("errorCode");
		codeFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		javaClassContentDesc.addField(codeFieldContentDesc);

		DqJavaFieldContentDesc msgFieldContentDesc = new DqJavaFieldContentDesc();
		msgFieldContentDesc.setComment("错误信息");
		// 设置msg属性的modifiers
		msgFieldContentDesc.addModifier(DqModifierMappingEnum.PRIVATE);
		// 设置code属性名称和类型
		msgFieldContentDesc.setName("errorMsg");
		msgFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		javaClassContentDesc.addField(msgFieldContentDesc);
	}

	@Override
	protected void buildMethods() {
		javaClassContentDesc.addMethods(super.getMethodsByFields());
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
