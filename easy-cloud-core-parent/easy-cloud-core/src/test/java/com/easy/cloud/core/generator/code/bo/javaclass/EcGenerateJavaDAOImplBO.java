package com.easy.cloud.core.generator.code.bo.javaclass;

import org.springframework.stereotype.Repository;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaImplInterfaceContentDesc;
import com.easy.cloud.core.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成数据传输对象
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class EcGenerateJavaDAOImplBO extends EcGenerateJavaClassBO {


	public EcGenerateJavaDAOImplBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		EcJavaAnnotationDesc repositoryAnnotation = new EcJavaAnnotationDesc();
		repositoryAnnotation.setName(Repository.class.getSimpleName());
		repositoryAnnotation.setSimpleClassType(Repository.class.getSimpleName());
		repositoryAnnotation.setFullClassType(Repository.class.getName());

		String value = EcStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.DAO_INF);
		repositoryAnnotation.addParam("value", value);

		super.javaClassContentDesc.addAnnotation(repositoryAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
//		extendsParentClass.setName(EcBaseRepository.class.getSimpleName());
//		extendsParentClass.setSimpleClassType(EcBaseRepository.class.getSimpleName());
//		extendsParentClass.setFullClassType(EcBaseRepository.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = EcClassNameEndWith.DAO_INF;
		String subModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
		EcJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, EcJavaImplInterfaceContentDesc.class);
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
		return EcClassCommentEndWith.DAO_IMPL;
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.DAO_IMPL;
	}
}
