package com.dq.easy.cloud.model.generate.bo.javaclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dq.easy.cloud.module.basic.service.DqBaseService;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
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
public class DqGenerateJavaServiceImplBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaServiceImplBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		DqJavaAnnotationDesc serviceAnnotation = new DqJavaAnnotationDesc();
		serviceAnnotation.setName(Service.class.getSimpleName());
		serviceAnnotation.setSimpleClassType(Service.class.getSimpleName());
		serviceAnnotation.setFullClassType(Service.class.getName());

		String value = DqStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.DAO_INF);
		serviceAnnotation.addParam("value", value);
		javaClassContentDesc.addAnnotation(serviceAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseService.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseService.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseService.class.getName());
		javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = DqClassNameEndWith.SERVICE_INF;
		String subModulePackageName = DqSubModuleDefaultPackageName.SERVICE_INF;
		DqJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, DqJavaImplInterfaceContentDesc.class);
		super.javaClassContentDesc.addImplementsInterface(implementsInterface);
	}

	@Override
	protected void buildConstructors() {
		
	}

	@Override
	protected void buildFields() {
		DqJavaFieldContentDesc fieldContentDesc = new DqJavaFieldContentDesc();
		fieldContentDesc.setComment(javaClassContentDesc.getComment() + DqClassCommentEndWith.DAO_INF);
		// 设置属性注解
		DqJavaAnnotationDesc annotationDesc = new DqJavaAnnotationDesc();
		annotationDesc.setName(Autowired.class.getSimpleName());
		annotationDesc.setSimpleClassType(Autowired.class.getSimpleName());
		annotationDesc.setFullClassType(Autowired.class.getName());
		fieldContentDesc.addAnnotation(annotationDesc);

		// 设置属性的modifiers
		fieldContentDesc.addModifier(DqModifierMappingEnum.PRIVATE);
		// 设置属性名称和类型
		String daoName = super.generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.DAO_INF;
		fieldContentDesc.setName(daoName);
		fieldContentDesc.setSimpleClassType(daoName);
		fieldContentDesc.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.DAO_INF));
		fieldContentDesc.buildFullClassType();

		javaClassContentDesc.addField(fieldContentDesc);
	}

	@Override
	protected void buildMethods() {
		
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.SERVICE_IMPL;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.SERVICE_IMPL;
	}
}
