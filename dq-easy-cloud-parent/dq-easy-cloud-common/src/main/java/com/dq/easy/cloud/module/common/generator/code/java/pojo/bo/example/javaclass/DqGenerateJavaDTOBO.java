package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成数据传输对象
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaDTOBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaDTOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		String parentName = generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.POJO_DO;
		extendsParentClass.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.POJO_DO));
		extendsParentClass.setName(parentName);
		extendsParentClass.setSimpleClassType(parentName);
		extendsParentClass.buildFullClassType();
		super.javaClassContentDesc.setExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_DTO;
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
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.POJO_DTO;
	}

}
