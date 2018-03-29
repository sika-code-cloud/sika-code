package com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGenerateBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFileDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqStatement;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentDocDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentElementDesc;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;

public abstract class DqGenerateXmlBaseBO extends DqGenerateBO {
	protected DqGenerateJavaBaseDTO generateJavaBaseDTO;
	protected DqDatabaseAbstactConfig dataBaseConfig;
	protected DqXmlContentDocDesc xmlContentDocDesc;
	protected DqDatabaseDataSources databaseDataSources;
	private DqGenerateRule generateRule;

	private void initData() {
		DqJavaFileDesc dqFileDesc = new DqJavaFileDesc();
		dqFileDesc.setProjectName(generateJavaBaseDTO.getProjectName());
		dqFileDesc.setSourceCodeRelativePath(DqSourceCodeRelativePath.RESOURCES);
		dqFileDesc.setFileSuffix(DqFileSuffix.XML);
		dqFileDesc.setCoverSwitch(generateJavaBaseDTO.isCoverSwith());

		DqLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
		super.setFileDesc(dqFileDesc);

		super.setFileContentDesc(new DqJavaClassContentDesc(generateRule));
		xmlContentDocDesc = (DqXmlContentDocDesc) super.getFileContentDesc();
	}

	@Override
	public void generateCode() throws Exception {
		xmlContentDocDesc.setStatement(getStatement());
		xmlContentDocDesc.setDocType(getDocType());
		xmlContentDocDesc.setGenerateRule(generateRule);
		xmlContentDocDesc.setRootContentElementDesc(getRootContentElementDesc());
		// 调用真正的生成代码方法
		super.generateCode();
	}

	/** 获取申明 */
	protected String getStatement() {
		return DqStatement.DEFAULT;
	}
	/** 获取文档类型 */
	protected abstract String getDocType();
	/** 获取根内容元素描述 */
	protected abstract DqXmlContentElementDesc getRootContentElementDesc();
	/**
	 * 
	 * <p>
	 * 构建数据源
	 * </p>
	 *
	 * @param databaseDataSources
	 * @return
	 * @author daiqi 创建时间 2018年3月27日 上午11:38:51
	 */
	public DqGenerateXmlBaseBO buildDatabaseDataSources(DqDatabaseDataSources databaseDataSources) {
		this.databaseDataSources = databaseDataSources;
		return this;
	}

}
