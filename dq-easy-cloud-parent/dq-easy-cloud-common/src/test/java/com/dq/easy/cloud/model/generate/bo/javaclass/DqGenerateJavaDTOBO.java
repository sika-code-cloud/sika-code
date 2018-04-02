package com.dq.easy.cloud.model.generate.bo.javaclass;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
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


	public DqGenerateJavaDTOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		String nameEndwith = DqClassNameEndWith.POJO_DO;
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_DO;
		DqJavaContentDesc extendsParentClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, DqJavaContentDesc.class);
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_DTO;
	}

	@Override
	protected void buildImplementsInterfaces() {
		
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
		return DqClassCommentEndWith.POJO_DTO;
	}

}
