package com.dq.easy.cloud.module.common.generator.code.config.database.oracle;

import com.dq.easy.cloud.module.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.config.database.DqDatabaseAbstactConfig;

public class DqDataBaseOracleConfig extends DqDatabaseAbstactConfig{

	@Override
	public String getDatabaseDriver() {
		return DqCodeGenerateConfig.DATABASE_ORACLE_DRIVER;
	}

}
