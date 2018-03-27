package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeInf;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaBaseBO;
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
public class DqGenerateJavaErrorCodeBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaErrorCodeBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, dataBaseConfig, templateDesc, generateRule);

	}

	@Override
	protected List<DqJavaAnnotationDesc> getClassAnnotations() {
		return null;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		return null;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.ERROR_CODE;
	}

	@Override
	public DqGenerateJavaBaseBO buildJavaClassContentOtherData() {
		super.buildModifiers(getModifiers());
		super.buildImplementsInterfaces(getImplementsInterfaces());
		super.buildFields(getFieldContentDescs());
		return this;
	}

	private List<DqJavaModifierDesc> getModifiers() {
		List<DqJavaModifierDesc> modifierDescs = new ArrayList<>();
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.ENUM));
		return modifierDescs;
	}
	
	/** 获取实现的接口列表 */
	private List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		List<DqJavaImplInterfaceContentDesc> implInterfaceContentDescs = new ArrayList<>();
		DqJavaImplInterfaceContentDesc implInterfaceContentDesc = new DqJavaImplInterfaceContentDesc();
		implInterfaceContentDesc.setSimpleClassType(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setName(DqBaseErrorCodeInf.class.getSimpleName());
		implInterfaceContentDesc.setFullClassType(DqBaseErrorCodeInf.class.getName());
		implInterfaceContentDescs.add(implInterfaceContentDesc);
		return implInterfaceContentDescs;
	}
	/** 获取属性描述列表 */
	private List<DqJavaFieldContentDesc> getFieldContentDescs() {
		List<DqJavaFieldContentDesc> fieldContentDescs = new ArrayList<>();
		DqJavaFieldContentDesc codeFieldContentDesc = new DqJavaFieldContentDesc();
		codeFieldContentDesc.setComment("错误代码");
//		设置code属性的modifiers
		List<DqJavaModifierDesc> codeModifiers = new ArrayList<>();
		DqJavaModifierDesc codeModifierDesc = new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE);
		codeModifiers.add(codeModifierDesc);
		codeFieldContentDesc.setModifiers(codeModifiers);
//		设置code属性名称和类型
		codeFieldContentDesc.setName("errorCode");
		codeFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		fieldContentDescs.add(codeFieldContentDesc);
		
		DqJavaFieldContentDesc msgFieldContentDesc = new DqJavaFieldContentDesc();
		msgFieldContentDesc.setComment("错误信息");
//		设置msg属性的modifiers
		List<DqJavaModifierDesc> msgModifiers = new ArrayList<>();
		DqJavaModifierDesc modifierDesc = new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE);
		msgModifiers.add(modifierDesc);
		msgFieldContentDesc.setModifiers(msgModifiers);
//		设置code属性名称和类型
		msgFieldContentDesc.setName("errorMsg");
		msgFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
		fieldContentDescs.add(msgFieldContentDesc);
		return fieldContentDescs;

	}
	@Override
	protected String getClassComment() {
		return super.getClassComment() + DqClassCommentEndWith.ERROR_CODE;
	}
}
