package com.dq.easy.cloud.model.generate.bo.javainf;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaInfBO;
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
public class DqGenerateJavaServiceBO extends DqGenerateJavaInfBO {

	public DqGenerateJavaServiceBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	protected void buildAnnotations() {
	}

	@Override
	protected void buildMethods() {
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.SERVICE_INF;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.SERVICE_INF;
	}

}
