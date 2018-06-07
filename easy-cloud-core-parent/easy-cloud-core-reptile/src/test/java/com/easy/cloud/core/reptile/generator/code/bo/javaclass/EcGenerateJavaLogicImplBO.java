package com.easy.cloud.core.reptile.generator.code.bo.javaclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.logic.EcBaseLogic;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaImplInterfaceContentDesc;
import com.easy.cloud.core.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
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
public class EcGenerateJavaLogicImplBO extends EcGenerateJavaClassBO {

	public EcGenerateJavaLogicImplBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		EcJavaAnnotationDesc serviceAnnotation = new EcJavaAnnotationDesc();
		serviceAnnotation.setName(Component.class.getSimpleName());
		serviceAnnotation.setSimpleClassType(Component.class.getSimpleName());
		serviceAnnotation.setFullClassType(Component.class.getName());

		String value = EcStringUtils
				.uncapitalize(generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.LOGIC_INF);
		serviceAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(serviceAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
		extendsParentClass.setName(EcBaseLogic.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseLogic.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseLogic.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = EcClassNameEndWith.LOGIC_INF;
		String subModulePackageName = EcSubModuleDefaultPackageName.LOGIC_INF;
		EcJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith,
				subModulePackageName, EcJavaImplInterfaceContentDesc.class);
		super.javaClassContentDesc.addImplementsInterface(implementsInterface);
	}

	@Override
	protected void buildConstructors() {

	}

	@Override
	protected void buildFields() {
		EcJavaFieldContentDesc fieldContentDesc = new EcJavaFieldContentDesc();
		fieldContentDesc.setComment(super.generateJavaBaseDTO.getClassComment() + EcClassCommentEndWith.SERVICE_INF);
		// 设置属性注解
		EcJavaAnnotationDesc annotationDesc = new EcJavaAnnotationDesc();
		annotationDesc.setName(Autowired.class.getSimpleName());
		annotationDesc.setSimpleClassType(Autowired.class.getSimpleName());
		annotationDesc.setFullClassType(Autowired.class.getName());
		fieldContentDesc.addAnnotation(annotationDesc);

		// 设置属性的modifiers
		fieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
		// 设置属性名称和类型
		String daoName = super.generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.SERVICE_INF;
		fieldContentDesc.setName(daoName);
		fieldContentDesc.setSimpleClassType(daoName);
		fieldContentDesc.setPackageName(getFullPackageName(EcSubModuleDefaultPackageName.SERVICE_INF));
		fieldContentDesc.buildFullClassType();

		super.javaClassContentDesc.addField(fieldContentDesc);

	}

	@Override
	protected void buildMethods() {

	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.LOGIC_IMPL;
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.LOGIC_IMPL;
	}
}
