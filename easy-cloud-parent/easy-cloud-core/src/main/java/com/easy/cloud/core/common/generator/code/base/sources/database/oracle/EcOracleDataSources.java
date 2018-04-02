package com.easy.cloud.core.common.generator.code.base.sources.database.oracle;

import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;

public class EcOracleDataSources extends EcDatabaseDataSources{

	public EcOracleDataSources(EcDatabaseAbstactConfig databaseConfig) {
		super(databaseConfig);
	}

	@Override
	public String getFieldTypeByColumnType(String columnType) {
		return null;
	}

}
