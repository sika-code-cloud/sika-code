package com.dq.easy.cloud.module.common.generator.code.base.sources.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.generator.code.base.sources.DqBaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 数据库生成代码的方式
 * @author daiqi
 * @date 2018年3月24日 上午10:40:29
 */
public abstract class DqDatabaseDataSources extends DqBaseDataSources{
	private DqDatabaseAbstactConfig databaseConfig;

	public DqDatabaseDataSources(DqDatabaseAbstactConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}
	private Connection getConnection() throws Exception {
//		校验数据
		verifyDatabaseDataSourcesData();
		
		String databaseUrl = databaseConfig.getDatabaseBaseUrl();
		String databasePort = databaseConfig.getDatabasePort();
		String databaseName = databaseConfig.getDatabaseName();
		String databaseUrlFull = DqCodeGenerateUtils.getDatabaseFullUrl(databaseUrl, databasePort, databaseName);
		String databaseUserName = databaseConfig.getDatabaseUserName();
		String databasePassword = databaseConfig.getDatabasePassword();
		Class.forName(databaseConfig.getDatabaseDriver());
		return DriverManager.getConnection(databaseUrlFull, databaseUserName, databasePassword);
	}

	/**
	 * 
	 * <p>
	 * 过去resultSet
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月26日 上午9:52:07
	 */
	public ResultSet getResultSet() {
		if (databaseConfig == null) {
			return null;
		}
		DatabaseMetaData databaseMetaData = null;
		try (
				Connection connection = getConnection()
		) {
			databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, "%", databaseConfig.getTableName(), "%");
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private void verifyDatabaseDataSourcesData() {
		if (DqBaseUtils.isNull(databaseConfig)) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_CONFIG_CANT_NULL);
		}
		databaseConfig.verifyDatabaseConfigData();
	}
	/**
	 * 
	 * <p>
	 * 根据列类型获取属性类型
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param columnType
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月26日 上午10:47:56
	 */
	public abstract String getFieldTypeByColumnType(String columnType);
}
