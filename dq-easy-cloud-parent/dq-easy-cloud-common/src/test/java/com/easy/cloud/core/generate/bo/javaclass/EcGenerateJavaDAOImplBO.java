package com.dq.easy.cloud.model.generate.bo.javaclass;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.module.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 生成数据传输对象
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaDAOImplBO extends DqGenerateJavaClassBO {


	public DqGenerateJavaDAOImplBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		DqJavaAnnotationDesc repositoryAnnotation = new DqJavaAnnotationDesc();
		repositoryAnnotation.setName(Repository.class.getSimpleName());
		repositoryAnnotation.setSimpleClassType(Repository.class.getSimpleName());
		repositoryAnnotation.setFullClassType(Repository.class.getName());

		String value = DqStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.DAO_INF);
		repositoryAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(repositoryAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseRepository.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseRepository.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseRepository.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = DqClassNameEndWith.DAO_INF;
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_INF;
		DqJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, DqJavaImplInterfaceContentDesc.class);
		super.javaClassContentDesc.addImplementsInterface(implementsInterface);
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
		return DqClassCommentEndWith.DAO_IMPL;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.DAO_IMPL;
	}
}
