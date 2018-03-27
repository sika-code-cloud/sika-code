package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example;

import java.util.List;

import com.dq.easy.cloud.module.basic.pojo.query.DqBaseQuery;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaBaseBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成查询类
 * </p>
 *
 *
 * @author daiqi
 * 创建时间    2018年3月27日 上午9:54:03
 */
public class DqGenerateJavaQueryBO extends DqGenerateJavaBaseBO{

	public DqGenerateJavaQueryBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, dataBaseConfig, templateDesc, generateRule);
		
	}

	@Override
	protected List<DqJavaAnnotationDesc> getClassAnnotations() {
		return null;
	}

	@Override
	protected DqJavaContentBaseDesc getExtendsParentClass() {
		DqJavaContentBaseDesc extendsParentClass = new DqJavaContentBaseDesc();
		extendsParentClass.setName(DqBaseQuery.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseQuery.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseQuery.class.getName());
		return extendsParentClass;
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.POJO_QUERY;
	}

	@Override
	public DqGenerateJavaBaseBO buildJavaClassContentOtherData() {
		return this;
	}
	@Override
	protected String getClassComment() {
		return super.getClassComment() + DqClassCommentEndWith.POJO_QUERY;
	}
}
