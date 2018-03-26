package com.dq.easy.cloud.module.common.file.pojo.desc;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.DqBaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;

/**
 * 文件内容基础描述
 * @author daiqi
 * @date 2018年3月24日 上午9:11:11
 */
public abstract class DqFileContentBaseDesc {
	/** 生成规则 */
	private DqGenerateRule generateRule;
	
	public DqFileContentBaseDesc() {
		
	}

	public DqFileContentBaseDesc(DqGenerateRule generateRule) {
		this.generateRule = generateRule;
	}

	/** 根据数据库数据源构建文件内容描述文件 */
	public abstract DqFileContentBaseDesc buildDescByDatabaseSources(DqDatabaseDataSources databaseDataSources);

	public DqGenerateRule getGenerateRule() {
		return generateRule;
	}

	public void setGenerateRule(DqGenerateRule generateRule) {
		this.generateRule = generateRule;
	}
	
}