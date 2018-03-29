package com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql;

import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 数据来源---mysql
 * @author daiqi
 * @date 2018年3月24日 上午10:55:35
 */
public class DqMysqlDataSources extends DqDatabaseDataSources{

	public DqMysqlDataSources(DqDatabaseAbstactConfig databaseConfig) {
		super(databaseConfig);
	}

	@Override
	public String getFieldTypeByColumnType(String columnType) {
		if (DqStringUtils.isEmpty(columnType)) {
			return null;
		}
		return DqCodeGenerateUtils.getJavaSimpleClassTypeOfMysql(columnType);
	}

}
