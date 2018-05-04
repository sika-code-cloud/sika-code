package com.easy.cloud.core.jdbc.generator.code.bo.javaclass;

import com.easy.cloud.core.basic.pojo.bo.EcBaseBO;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaFieldContentDesc;
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
public class EcGenerateJavaBOBO extends EcGenerateJavaClassBO {


	public EcGenerateJavaBOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
		extendsParentClass.setName(EcBaseBO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseBO.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.POJO_BO;
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
		EcJavaFieldContentDesc fieldContentDesc = new EcJavaFieldContentDesc();
		fieldContentDesc.setComment(EcClassCommentEndWith.POJO_DTO);

		fieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);

		String dtoName = super.generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.POJO_DTO;
		fieldContentDesc.setName(dtoName);
		fieldContentDesc.setSimpleClassType(dtoName);
		fieldContentDesc.setPackageName(getFullPackageName(EcSubModuleDefaultPackageName.POJO_DTO));
		fieldContentDesc.buildFullClassType();
		super.javaClassContentDesc.addField(fieldContentDesc);
	}

	@Override
	protected void buildMethods() {
		super.javaClassContentDesc.addMethods(getMethodsByFields());
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.POJO_BO;
	}
}
