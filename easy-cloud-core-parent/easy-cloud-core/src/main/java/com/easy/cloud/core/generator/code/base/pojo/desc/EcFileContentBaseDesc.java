package com.easy.cloud.core.generator.code.base.pojo.desc;

import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.base.sources.database.EcDatabaseDataSources;

/**
 * 文件内容基础描述
 * @author daiqi
 * @date 2018年3月24日 上午9:11:11
 */
public abstract class EcFileContentBaseDesc {
	/** 生成规则 */
	private EcGenerateRule generateRule;
	
	
	public EcFileContentBaseDesc() {
		
	}

	public EcFileContentBaseDesc(EcGenerateRule generateRule) {
		this.generateRule = generateRule;
	}

	/** 根据数据库数据源构建数据 */
	public abstract EcFileContentBaseDesc buildDataByDatabaseSources(EcDatabaseDataSources databaseDataSources);

	public EcGenerateRule getGenerateRule() {
		return generateRule;
	}

	public void setGenerateRule(EcGenerateRule generateRule) {
		this.generateRule = generateRule;
	}
	
}