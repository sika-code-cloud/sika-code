package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;

/**
 * 
 * <p>
 * xml内容基础描述类
 * </p>
 *
 * <pre>
 *  说明：所有xml内容描述文件的基础父类
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月28日 上午10:49:49
 */
public class DqXmlContentBaseDesc extends DqFileContentBaseDesc{

	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		return null;
	}

}
