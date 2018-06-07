package com.easy.cloud.core.reptile.generator.code.bo.javaenum;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaImplInterfaceContentDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaEnumBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成业务逻辑类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class EcGenerateJavaErrorCodeBO extends EcGenerateJavaEnumBO {

	public EcGenerateJavaErrorCodeBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	/** 获取实现的接口列表 */
	protected void buildImplementsInterfaces() {
		EcJavaImplInterfaceContentDesc implInterfaceContentDesc = new EcJavaImplInterfaceContentDesc();
		implInterfaceContentDesc.setSimpleClassType(EcBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setName(EcBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setFullClassType(EcBaseErrorCodeInf.class.getName());
		javaClassContentDesc.addImplementsInterface(implInterfaceContentDesc);
	}

	@Override
	protected void buildFields() {
		EcJavaFieldContentDesc codeFieldContentDesc = new EcJavaFieldContentDesc();
		codeFieldContentDesc.setComment("错误代码");
		// 设置code属性的modifiers
		codeFieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
		// 设置code属性名称和类型
		codeFieldContentDesc.setName("errorCode");
		codeFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		javaClassContentDesc.addField(codeFieldContentDesc);

		EcJavaFieldContentDesc msgFieldContentDesc = new EcJavaFieldContentDesc();
		msgFieldContentDesc.setComment("错误信息");
		// 设置msg属性的modifiers
		msgFieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
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
		return EcClassCommentEndWith.ERROR_CODE;
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.ERROR_CODE;
	}

}
