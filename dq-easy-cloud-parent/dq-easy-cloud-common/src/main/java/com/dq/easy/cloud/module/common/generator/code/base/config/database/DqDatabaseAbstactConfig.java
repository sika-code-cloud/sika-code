package com.dq.easy.cloud.module.common.generator.code.base.config.database;

import com.dq.easy.cloud.module.common.generator.code.base.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 
 * <p>
 * 数据库配置类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月23日 下午3:13:26
 */
public abstract class DqDatabaseAbstactConfig {
	/** 数据库基础url */
	private String databaseBaseUrl;
	/** 数据库端口 */
	private String databasePort;
	/** 数据库名称 */
	private String databaseName;
	/** 数据库用户名 */
	private String databaseUserName;
	/** 数据库密码 */
	private String databasePassword;
	/** 表名 */
	private String tableName;

	public DqDatabaseAbstactConfig buildDatabaseBaseUrl(String databaseBaseUrl) {
		this.databaseBaseUrl = databaseBaseUrl;
		return this;
	}

	public DqDatabaseAbstactConfig buildDatabasePort(String databasePort) {
		this.databasePort = databasePort;
		return this;
	}

	public DqDatabaseAbstactConfig buildDatabaseName(String databaseName) {
		this.databaseName = databaseName;
		return this;
	}

	public DqDatabaseAbstactConfig buildDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
		return this;
	}

	public DqDatabaseAbstactConfig buildDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
		return this;
	}
	public DqDatabaseAbstactConfig buildTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public String getDatabaseBaseUrl() {
		return databaseBaseUrl;
	}

	public void setDatabaseBaseUrl(String databaseBaseUrl) {
		this.databaseBaseUrl = databaseBaseUrl;
	}

	public String getDatabasePort() {
		return databasePort;
	}

	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseUserName() {
		return databaseUserName;
	}

	public void setDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void verifyDatabaseConfigData() {
		if (DqStringUtils.isEmpty(getDatabaseBaseUrl())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_BASEURL_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDatabaseDriver())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_DRIVER_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDatabaseName())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_NAME_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDatabasePassword())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_PASSWORD_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDatabasePort())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_PORT_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDatabaseUserName())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_USER_NAME_CANT_EMPTY);
		}
		
		if (DqStringUtils.isEmpty(getTableName())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.TABLE_NAME_CANT_NULL);
		}
	}
	/** 获取数据库驱动 */
	public abstract String getDatabaseDriver();
}
