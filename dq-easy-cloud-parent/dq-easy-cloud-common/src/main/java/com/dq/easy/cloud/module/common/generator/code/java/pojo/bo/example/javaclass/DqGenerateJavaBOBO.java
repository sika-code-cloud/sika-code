package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成业务逻辑类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaBOBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaBOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
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
		extendsParentClass.setName(DqBaseBO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		return extendsParentClass;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_BO;
	}

	@Override
	protected List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getConstructors() {
		return super.getConstructorsByFields();
	}

	@Override
	protected List<DqJavaFieldContentDesc> getFields() {
		List<DqJavaFieldContentDesc> fieldContentDescs = new ArrayList<>();
		DqJavaFieldContentDesc fieldContentDesc = new DqJavaFieldContentDesc();
		fieldContentDesc.setComment(DqClassCommentEndWith.POJO_DTO);

		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		DqJavaModifierDesc modifierDesc = new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE);
		modifiers.add(modifierDesc);
		fieldContentDesc.setModifiers(modifiers);

		String dtoName = super.generateJavaBaseDTO.getClassBodyName() + DqClassNameEndWith.POJO_DTO;
		fieldContentDesc.setName(dtoName);
		fieldContentDesc.setSimpleClassType(dtoName);
		fieldContentDesc.setPackageName(getFullPackageName(DqSubModuleDefaultPackageName.POJO_DTO));
		fieldContentDesc.buildFullClassType();
		fieldContentDescs.add(fieldContentDesc);

		return fieldContentDescs;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getMethods() {
		return getMethodsByFields();
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.POJO_BO;
	}
}
