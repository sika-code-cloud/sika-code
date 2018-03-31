package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javainf;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaGenericityContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaInfBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public class DqGenerateJavaJpaDAOBO extends DqGenerateJavaInfBO {

	public DqGenerateJavaJpaDAOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.DAO_INF;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.DAO_INF;
	}


	@Override
	protected void buildExtendsParentClass() {
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		
		extendsParentClass.setSimpleClassType(JpaRepository.class.getSimpleName());
		extendsParentClass.setName(JpaRepository.class.getSimpleName());
		extendsParentClass.setFullClassType(JpaRepository.class.getName());
		
		DqJavaGenericityContentDesc entity = new DqJavaGenericityContentDesc();
		entity.setSimpleClassType(DqBaseEntity.class.getSimpleName());
		entity.setName(DqBaseEntity.class.getSimpleName());
		entity.setFullClassType(DqBaseEntity.class.getName());
		extendsParentClass.addGenericity(entity);
		
		DqJavaGenericityContentDesc gene = new DqJavaGenericityContentDesc();
		gene.setSimpleClassType(String.class.getSimpleName());
		gene.setName(String.class.getSimpleName());
		extendsParentClass.addGenericity(gene);
		
		
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildMethods() {
		
	}

}
