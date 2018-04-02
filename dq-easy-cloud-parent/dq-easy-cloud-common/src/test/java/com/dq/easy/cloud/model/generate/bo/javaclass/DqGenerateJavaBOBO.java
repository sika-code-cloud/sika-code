package com.dq.easy.cloud.model.generate.bo.javaclass;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
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
public class DqGenerateJavaBOBO extends DqGenerateJavaClassBO {


	public DqGenerateJavaBOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseBO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_BO;
	}

	@Override
	protected void buildImplementsInterfaces() {
		
	}

	@Override
	protected void buildConstructors() {
		super.javaClassContentDesc.addConstructors(super.getConstructorsByFields());
	}

	@Override
	protected void buildFields() {
		DqJavaFieldContentDesc fieldContentDesc = new DqJavaFieldContentDesc();
		fieldContentDesc.setComment(DqClassCommentEndWith.POJO_DTO);

		fieldContentDesc.addModifier(DqModifierMappingEnum.PRIVATE);

		String dtoName = super.generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.POJO_DTO;
		fieldContentDesc.setName(dtoName);
		fieldContentDesc.setSimpleClassType(dtoName);
		fieldContentDesc.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.POJO_DTO));
		fieldContentDesc.buildFullClassType();
		super.javaClassContentDesc.addField(fieldContentDesc);
	}

	@Override
	protected void buildMethods() {
		super.javaClassContentDesc.addMethods(getMethodsByFields());
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.POJO_BO;
	}
}
