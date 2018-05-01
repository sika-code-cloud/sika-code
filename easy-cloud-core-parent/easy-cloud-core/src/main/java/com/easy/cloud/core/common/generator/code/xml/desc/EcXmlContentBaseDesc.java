package com.easy.cloud.core.common.generator.code.xml.desc;

import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlBaseDTO;

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
public abstract class EcXmlContentBaseDesc extends EcFileContentBaseDesc {
	private EcGenerateXmlBaseDTO generateXmlBaseDTO;

	public EcXmlContentBaseDesc() {
	}

	public EcXmlContentBaseDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}

	public EcXmlContentBaseDesc(EcGenerateXmlBaseDTO generateXmlBaseDTO) {
		super();
		this.generateXmlBaseDTO = generateXmlBaseDTO;
	}

	@Override
	public EcFileContentBaseDesc buildDataByDatabaseSources(EcDatabaseDataSources databaseDataSources) {
		return null;
	}

	public EcGenerateXmlBaseDTO getGenerateXmlBaseDTO() {
		return generateXmlBaseDTO;
	}

	public void setGenerateXmlBaseDTO(EcGenerateXmlBaseDTO generateXmlBaseDTO) {
		this.generateXmlBaseDTO = generateXmlBaseDTO;
	}

}
