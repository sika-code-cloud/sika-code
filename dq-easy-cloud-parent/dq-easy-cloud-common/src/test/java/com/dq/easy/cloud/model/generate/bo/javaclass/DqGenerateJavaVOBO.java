package com.dq.easy.cloud.model.generate.bo.javaclass;

import com.dq.easy.cloud.module.basic.pojo.vo.DqBaseVO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成vo对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:49:06
 */
public class DqGenerateJavaVOBO extends DqGenerateJavaDOBO {

	public DqGenerateJavaVOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildExtendsParentClass() {
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName(DqBaseVO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseVO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseVO.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.POJO_VO;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_VO;
	}

}
