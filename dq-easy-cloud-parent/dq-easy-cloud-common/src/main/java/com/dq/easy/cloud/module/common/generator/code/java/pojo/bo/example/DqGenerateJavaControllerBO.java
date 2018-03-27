package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.dq.easy.cloud.module.basic.controller.DqBaseController;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaBaseBO;
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
public class DqGenerateJavaControllerBO extends DqGenerateJavaBaseBO {

	public DqGenerateJavaControllerBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, dataBaseConfig, templateDesc, generateRule);

	}

	@Override
	protected List<DqJavaAnnotationDesc> getClassAnnotations() {
		List<DqJavaAnnotationDesc> annotationDescs = new ArrayList<>();
		DqJavaAnnotationDesc controllerAnnotation = new DqJavaAnnotationDesc();
		controllerAnnotation.setName(Controller.class.getSimpleName());
		controllerAnnotation.setSimpleClassType(Controller.class.getSimpleName());
		controllerAnnotation.setFullClassType(Controller.class.getName());

		List<DqJavaAnnotationParamDesc> controllerAnnotationParamDescs = new ArrayList<>();
		String value = DqStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.DAO_INF);
		controllerAnnotationParamDescs.add(new DqJavaAnnotationParamDesc("value", value));
		controllerAnnotation.setParams(controllerAnnotationParamDescs);

		annotationDescs.add(controllerAnnotation);
		return annotationDescs;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		DqJavaContentBaseDesc extendsParentClass = new DqJavaContentBaseDesc();
		extendsParentClass.setName(DqBaseController.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseController.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseController.class.getName());
		return extendsParentClass;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.CONTROLLER;
	}

	@Override
	public DqGenerateJavaBaseBO buildJavaClassContentOtherData() {
		return this;
	}


	@Override
	protected String getClassComment() {
		return super.getClassComment() + DqClassCommentEndWith.CONTROLLER;
	}
}
