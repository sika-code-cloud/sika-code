package com.easy.cloud.core.common.generator.code.xml.pojo.bo;

import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.file.constant.EcFileConstant.EcFileSuffix;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.file.pojo.desc.EcFileDesc;
import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcSourceCodeRelativePath;
import com.easy.cloud.core.common.generator.code.base.pojo.bo.EcGenerateBO;
import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcStatement;
import com.easy.cloud.core.common.generator.code.xml.desc.EcXmlContentDocDesc;
import com.easy.cloud.core.common.generator.code.xml.desc.EcXmlFileDesc;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlBaseDTO;
import com.easy.cloud.core.common.log.utils.EcLogUtils;

public abstract class EcGenerateXmlBaseBO extends EcGenerateBO {
	protected EcXmlContentDocDesc xmlContentDocDesc;
	protected EcDatabaseAbstactConfig dataBaseConfig;
	protected EcDatabaseDataSources databaseDataSources;
	private EcGenerateRule generateRule;
	
	public EcGenerateXmlBaseBO() {
		super();
	}

	public EcGenerateXmlBaseBO(EcFileDesc fileDesc, EcTemplateDesc templateDesc) {
		super(fileDesc, templateDesc);
	}

	public final void initData() {
		EcXmlFileDesc dqFileDesc = new EcXmlFileDesc();
		dqFileDesc.setSourceCodeRelativePath(EcSourceCodeRelativePath.RESOURCES);
		dqFileDesc.setProjectName(getGenerateXmlBaseDTO().getProjectName());
		dqFileDesc.setFileName(getFileName());
		dqFileDesc.setFileSuffix(EcFileSuffix.XML);
		dqFileDesc.setSubPath(getGenerateXmlBaseDTO().getSubPath());
		dqFileDesc.setCoverSwitch(getGenerateXmlBaseDTO().isCoverSwith());

		EcLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
		super.setFileDesc(dqFileDesc);

		this.xmlContentDocDesc = new EcXmlContentDocDesc();
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
		return EcStatement.DEFAULT;
	}
	
	protected abstract String getSourceCodeRelativePath();
	/** 获取文件名称 */
	protected abstract String getFileName();
	/** 获取文档类型 */
	protected abstract String getDocType();
	/** 获取数据传输对象 */
	protected abstract EcGenerateXmlBaseDTO getGenerateXmlBaseDTO();
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
	public EcGenerateXmlBaseBO buildDatabaseDataSources(EcDatabaseDataSources databaseDataSources) {
		this.databaseDataSources = databaseDataSources;
		return this;
	}

	@Override
	public EcFileContentBaseDesc getFileContentDesc() {
		return xmlContentDocDesc;
	}

}
