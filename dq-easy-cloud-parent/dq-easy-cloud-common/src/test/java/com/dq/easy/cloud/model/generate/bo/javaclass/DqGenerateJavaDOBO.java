package com.dq.easy.cloud.model.generate.bo.javaclass;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成持久化对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:49:06
 */
public class DqGenerateJavaDOBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaDOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		if (DqBaseUtils.isNull(dataBaseConfig)) {
			return;
		}

		DqJavaAnnotationDesc tableAnnotationDesc = new DqJavaAnnotationDesc();
		tableAnnotationDesc.setName(Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
		// 设置类注解参数---begin
		tableAnnotationDesc.addParam("name", dataBaseConfig.getTableName());
		// 设置类注解参数---end
		super.javaClassContentDesc.addAnnotation(tableAnnotationDesc);

		DqJavaAnnotationDesc entityAnnotationDesc = new DqJavaAnnotationDesc();
		entityAnnotationDesc.setName(Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());
		super.javaClassContentDesc.addAnnotation(entityAnnotationDesc);

	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName(DqBaseEntity.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseEntity.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseEntity.class.getName());
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
		return DqClassCommentEndWith.POJO_DO;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_DO;
	}

}
