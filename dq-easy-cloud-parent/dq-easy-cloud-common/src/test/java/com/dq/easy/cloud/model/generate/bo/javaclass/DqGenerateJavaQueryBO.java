package com.dq.easy.cloud.model.generate.bo.javaclass;

import com.dq.easy.cloud.module.basic.pojo.query.DqBaseQuery;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaClassBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成查询类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaQueryBO extends DqGenerateJavaClassBO {

	public DqGenerateJavaQueryBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, templateDesc, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaContentDesc extendsParentClass = new DqJavaContentDesc();
		extendsParentClass.setName(DqBaseQuery.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseQuery.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseQuery.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_QUERY;
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
		return DqClassCommentEndWith.POJO_QUERY;
	}
}
