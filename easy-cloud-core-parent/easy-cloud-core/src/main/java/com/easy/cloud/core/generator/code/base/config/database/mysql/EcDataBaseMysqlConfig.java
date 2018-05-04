package com.easy.cloud.core.generator.code.base.config.database.mysql;

import com.easy.cloud.core.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.generator.code.base.config.database.EcDatabaseAbstactConfig;

public class EcDataBaseMysqlConfig extends EcDatabaseAbstactConfig{

	@Override
	public String getDatabaseDriver() {
		return EcCodeGenerateConfig.DATABASE_MYSQL_DRIVER;
	}

}
