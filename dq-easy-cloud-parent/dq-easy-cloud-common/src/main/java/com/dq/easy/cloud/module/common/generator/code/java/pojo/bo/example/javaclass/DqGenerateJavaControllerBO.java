package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import org.springframework.stereotype.Controller;

import com.dq.easy.cloud.module.basic.controller.DqBaseController;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
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
public class DqGenerateJavaControllerBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaControllerBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		DqJavaAnnotationDesc controllerAnnotation = new DqJavaAnnotationDesc();
		controllerAnnotation.setName(Controller.class.getSimpleName());
		controllerAnnotation.setSimpleClassType(Controller.class.getSimpleName());
		controllerAnnotation.setFullClassType(Controller.class.getName());

		String value = DqStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.CONTROLLER);
		controllerAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(controllerAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseController.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseController.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseController.class.getName());
		super.javaClassContentDesc.setExtendsParentClass(extendsParentClass);
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
		return DqClassNameEndWith.CONTROLLER;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.CONTROLLER;
	}
}
