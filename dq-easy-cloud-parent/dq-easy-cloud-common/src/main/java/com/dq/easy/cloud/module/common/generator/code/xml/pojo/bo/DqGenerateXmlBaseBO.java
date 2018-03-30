package com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGenerateBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqStatement;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentDocDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlFileDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;

public abstract class DqGenerateXmlBaseBO extends DqGenerateBO {
	protected DqXmlContentDocDesc xmlContentDocDesc;
	protected DqDatabaseAbstactConfig dataBaseConfig;
	protected DqDatabaseDataSources databaseDataSources;
	private DqGenerateRule generateRule;
	
	public DqGenerateXmlBaseBO() {
		super();
	}

	public DqGenerateXmlBaseBO(DqFileDesc fileDesc, DqTemplateDesc templateDesc) {
		super(fileDesc, templateDesc);
	}

	public final void initData() {
		DqXmlFileDesc dqFileDesc = new DqXmlFileDesc();
		dqFileDesc.setSourceCodeRelativePath(DqSourceCodeRelativePath.RESOURCES);
		dqFileDesc.setProjectName(getGenerateXmlBaseDTO().getProjectName());
		dqFileDesc.setFileName(getFileName());
		dqFileDesc.setFileSuffix(DqFileSuffix.XML);
		dqFileDesc.setSubPath(getGenerateXmlBaseDTO().getSubPath());
		dqFileDesc.setCoverSwitch(getGenerateXmlBaseDTO().isCoverSwith());

		DqLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
		super.setFileDesc(dqFileDesc);

		this.xmlContentDocDesc = new DqXmlContentDocDesc();
	}

	@Override
	public void generateCode() throws Exception {
		xmlContentDocDesc.setStatement(getStatement());
		xmlContentDocDesc.setDocType(getDocType());
		xmlContentDocDesc.setGenerateRule(generateRule);
		// 调用真正的生成代码方法
		super.generateCode();
	}

	/** 获取申明 */
	protected String getStatement() {
		return DqStatement.DEFAULT;
	}
	
	protected abstract String getSourceCodeRelativePath();
	/** 获取文件名称 */
	protected abstract String getFileName();
	/** 获取文档类型 */
	protected abstract String getDocType();
	/** 获取数据传输对象 */
	protected abstract DqGenerateXmlBaseDTO getGenerateXmlBaseDTO();
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

	@Override
	public DqFileContentBaseDesc getFileContentDesc() {
		return xmlContentDocDesc;
	}

}
