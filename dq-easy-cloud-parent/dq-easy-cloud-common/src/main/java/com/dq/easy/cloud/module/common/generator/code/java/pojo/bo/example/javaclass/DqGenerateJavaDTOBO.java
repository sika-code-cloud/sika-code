package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
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
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		return null;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		DqJavaContentBaseDesc extendsParentClass = new DqJavaContentBaseDesc();
		String parentName = generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.POJO_DO;
		extendsParentClass.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.POJO_DO));
		extendsParentClass.setName(parentName);
		extendsParentClass.setSimpleClassType(parentName);
		extendsParentClass.buildFullClassType();
		return extendsParentClass;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_DTO;
	}

	@Override
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		// TODO Auto-generated method stub
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
		return DqClassCommentEndWith.POJO_DTO;
	}

}
