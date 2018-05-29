package com.easy.cloud.core.generator.code.bo.javaclass;

import org.springframework.stereotype.Controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
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
public class EcGenerateJavaControllerBO extends EcGenerateJavaClassBO {

	public EcGenerateJavaControllerBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		EcJavaAnnotationDesc controllerAnnotation = new EcJavaAnnotationDesc();
		controllerAnnotation.setName(Controller.class.getSimpleName());
		controllerAnnotation.setSimpleClassType(Controller.class.getSimpleName());
		controllerAnnotation.setFullClassType(Controller.class.getName());

		String value = EcStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.CONTROLLER);
		controllerAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(controllerAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
		extendsParentClass.setName(EcBaseController.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseController.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseController.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		
	}

	@Override
	protected void buildConstructors() {
		
	}

	@Override
	protected void buildFields() {
		
	}

	@Override
	protected void buildMethods() {
		
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.CONTROLLER;
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.CONTROLLER;
	}
}
