package com.easy.cloud.core.common.generator.code.base.config.database.oracle;

import com.easy.cloud.core.common.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;

public class EcDataBaseOracleConfig extends EcDatabaseAbstactConfig{

	@Override
	public String getDatabaseDriver() {
		return EcCodeGenerateConfig.DATABASE_ORACLE_DRIVER;
	}

}
