package com.dq.easy.cloud.module.common.generator.code.base.sources.database.oracle;

import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;

public class DqOracleDataSources extends DqDatabaseDataSources{

	public DqOracleDataSources(DqDatabaseAbstactConfig databaseConfig, String tableName) {
		super(databaseConfig, tableName);
	}

	@Override
	public String getFieldTypeByColumnType(String columnType) {
		return null;
	}

}
