package com.easy.cloud.core.generate.bo.javaclass;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成持久化对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:49:06
 */
public class EcGenerateJavaDOBO extends EcGenerateJavaClassBO {

	public EcGenerateJavaDOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		if (EcBaseUtils.isNull(dataBaseConfig)) {
			return;
		}

		EcJavaAnnotationDesc tableAnnotationDesc = new EcJavaAnnotationDesc();
		tableAnnotationDesc.setName(Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
		// 设置类注解参数---begin
		tableAnnotationDesc.addParam("name", dataBaseConfig.getTableName());
		// 设置类注解参数---end
		super.javaClassContentDesc.addAnnotation(tableAnnotationDesc);

		EcJavaAnnotationDesc entityAnnotationDesc = new EcJavaAnnotationDesc();
		entityAnnotationDesc.setName(Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());
		super.javaClassContentDesc.addAnnotation(entityAnnotationDesc);

	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
		extendsParentClass.setName(EcBaseEntity.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseEntity.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseEntity.class.getName());
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
		super.javaClassContentDesc.addFields(getFieldsByDatabaseDataSources());
	}

	@Override
	protected void buildMethods() {
		super.javaClassContentDesc.addMethods(super.getMethodsByFields());
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.POJO_DO;
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.POJO_DO;
	}

}
