package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javainf;

import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaBaseBO;
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

	public DqGenerateJavaServiceBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, dataBaseConfig, templateDesc, generateRule);

	}

	@Override
	protected List<DqJavaAnnotationDesc> getAnnotations() {
		return null;
	}

	@Override
	protected List<DqJavaMethodContentDesc> getMethods() {
		return null;
	}

	@Override
	public DqGenerateJavaBaseBO buildJavaClassContentOtherData() {
		return this;
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