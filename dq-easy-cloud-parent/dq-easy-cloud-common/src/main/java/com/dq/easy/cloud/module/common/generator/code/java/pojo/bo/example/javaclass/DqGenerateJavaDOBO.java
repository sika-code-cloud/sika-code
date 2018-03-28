package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
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

	public DqGenerateJavaDOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		if (DqBaseUtils.isNull(dataBaseConfig)) {
			return null;
		}
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();

		DqJavaAnnotationDesc tableAnnotationDesc = new DqJavaAnnotationDesc();
		tableAnnotationDesc.setName(Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
		// 设置类注解参数---begin
		List<DqJavaAnnotationParamDesc> tableAnnotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc tableAnnotationParamDesc = new DqJavaAnnotationParamDesc();
		tableAnnotationParamDesc.setName("name");
		tableAnnotationParamDesc.setValue(dataBaseConfig.getTableName());
		tableAnnotationParamDescs.add(tableAnnotationParamDesc);
		tableAnnotationDesc.setParams(tableAnnotationParamDescs);
		// 设置类注解参数---end
		annotations.add(tableAnnotationDesc);

		DqJavaAnnotationDesc entityAnnotationDesc = new DqJavaAnnotationDesc();
		entityAnnotationDesc.setName(Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());
		annotations.add(entityAnnotationDesc);

		return annotations;
	}

	@Override
	protected DqJavaClassContentDesc getExtendsParentClass() {
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName(DqBaseEntity.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseEntity.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseEntity.class.getName());
		return extendsParentClass;
	}

	@Override
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getConstructors() {
		return null;
	}

	@Override
	protected List<DqJavaFieldContentDesc> getFields() {
		return super.getFieldsByDatabaseDataSources();
	}

	@Override
	protected List<DqJavaMethodContentDesc> getMethods() {
		return super.getMethodsByFields();
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
