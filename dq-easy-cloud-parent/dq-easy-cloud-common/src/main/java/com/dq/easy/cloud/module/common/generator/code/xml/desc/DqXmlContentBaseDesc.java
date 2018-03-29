package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;

/**
 * 
 * <p>
 * xml内容基础描述类
 * </p>
 *
 * <pre>
 *  说明：所有xml描述类的最终父类
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月29日 上午10:30:14
 */
public abstract class DqXmlContentBaseDesc extends DqFileContentBaseDesc {
	private DqGenerateXmlBaseDTO generateXmlBaseDTO;

	public DqXmlContentBaseDesc() {
	}

	public DqXmlContentBaseDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	public DqXmlContentBaseDesc(DqGenerateXmlBaseDTO generateXmlBaseDTO) {
		super();
		this.generateXmlBaseDTO = generateXmlBaseDTO;
	}

	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		return null;
	}

	public DqGenerateXmlBaseDTO getGenerateXmlBaseDTO() {
		return generateXmlBaseDTO;
	}

	public void setGenerateXmlBaseDTO(DqGenerateXmlBaseDTO generateXmlBaseDTO) {
		this.generateXmlBaseDTO = generateXmlBaseDTO;
	}

}
