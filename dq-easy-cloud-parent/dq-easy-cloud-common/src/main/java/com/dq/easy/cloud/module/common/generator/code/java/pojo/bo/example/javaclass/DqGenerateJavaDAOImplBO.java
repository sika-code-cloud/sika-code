package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.module.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
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

	public DqGenerateJavaDAOImplBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		List<DqJavaAnnotationDesc> annotationDescs = new ArrayList<>();
		DqJavaAnnotationDesc repositoryAnnotation = new DqJavaAnnotationDesc();
		repositoryAnnotation.setName(Repository.class.getSimpleName());
		repositoryAnnotation.setSimpleClassType(Repository.class.getSimpleName());
		repositoryAnnotation.setFullClassType(Repository.class.getName());

		List<DqJavaAnnotationParamDesc> repositoryAnnotationParamDescs = new ArrayList<>();
		String value = DqStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.DAO_INF);
		repositoryAnnotationParamDescs.add(new DqJavaAnnotationParamDesc("value", value));
		repositoryAnnotation.setParams(repositoryAnnotationParamDescs);

		annotationDescs.add(repositoryAnnotation);
		return annotationDescs;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		DqJavaContentBaseDesc extendsParentClass = new DqJavaContentBaseDesc();
		extendsParentClass.setName(DqBaseRepository.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseRepository.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseRepository.class.getName());
		return extendsParentClass;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.DAO_IMPL;
	}

	@Override
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getConstructors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<DqJavaFieldContentDesc> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.DAO_IMPL;
	}

}
