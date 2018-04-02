package com.dq.easy.cloud.model.generate.bo.javaclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.basic.logic.DqBaseLogic;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 生成业务逻辑类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaLogicImplBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaLogicImplBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		DqJavaAnnotationDesc serviceAnnotation = new DqJavaAnnotationDesc();
		serviceAnnotation.setName(Component.class.getSimpleName());
		serviceAnnotation.setSimpleClassType(Component.class.getSimpleName());
		serviceAnnotation.setFullClassType(Component.class.getName());

		String value = DqStringUtils
				.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.SERVICE_INF);
		serviceAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(serviceAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseLogic.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseLogic.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseLogic.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = DqClassNameEndWith.LOGIC_INF;
		String subModulePackageName = DqSubModuleDefaultPackageName.LOGIC_INF;
		DqJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith,
				subModulePackageName, DqJavaImplInterfaceContentDesc.class);
		super.javaClassContentDesc.addImplementsInterface(implementsInterface);
	}

	@Override
	protected void buildConstructors() {

	}

	@Override
	protected void buildFields() {
		DqJavaFieldContentDesc fieldContentDesc = new DqJavaFieldContentDesc();
		fieldContentDesc.setComment(javaClassContentDesc.getComment() + DqClassCommentEndWith.SERVICE_INF);
		// 设置属性注解
		DqJavaAnnotationDesc annotationDesc = new DqJavaAnnotationDesc();
		annotationDesc.setName(Autowired.class.getSimpleName());
		annotationDesc.setSimpleClassType(Autowired.class.getSimpleName());
		annotationDesc.setFullClassType(Autowired.class.getName());
		fieldContentDesc.addAnnotation(annotationDesc);

		// 设置属性的modifiers
		fieldContentDesc.addModifier(DqModifierMappingEnum.PRIVATE);
		// 设置属性名称和类型
		String daoName = super.generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.SERVICE_INF;
		fieldContentDesc.setName(daoName);
		fieldContentDesc.setSimpleClassType(daoName);
		fieldContentDesc.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.SERVICE_INF));
		fieldContentDesc.buildFullClassType();

		super.javaClassContentDesc.addField(fieldContentDesc);

	}

	@Override
	protected void buildMethods() {

	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.LOGIC_IMPL;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.LOGIC_IMPL;
	}
}
