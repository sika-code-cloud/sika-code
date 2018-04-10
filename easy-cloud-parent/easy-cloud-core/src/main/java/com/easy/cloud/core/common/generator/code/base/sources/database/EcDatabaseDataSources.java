package com.easy.cloud.core.common.generator.code.base.sources.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.constant.error.EcCodeGenerateErrorCodeEnum;
import com.easy.cloud.core.common.generator.code.base.sources.EcBaseDataSources;
import com.easy.cloud.core.common.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 数据库生成代码的方式
 * @author daiqi
 * @date 2018年3月24日 上午10:40:29
 */
public abstract class EcDatabaseDataSources extends EcBaseDataSources{
	private EcDatabaseAbstactConfig databaseConfig;

	public EcDatabaseDataSources(EcDatabaseAbstactConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}
	private Connection getConnection() throws Exception {
//		校验数据
		verifyDatabaseDataSourcesData();
		
		String databaseUrl = databaseConfig.getDatabaseBaseUrl();
		String databasePort = databaseConfig.getDatabasePort();
		String databaseName = databaseConfig.getDatabaseName();
		String databaseUrlFull = EcCodeGenerateUtils.getDatabaseFullUrl(databaseUrl, databasePort, databaseName);
		String databaseUserName = databaseConfig.getDatabaseUserName();
		String databasePassword = databaseConfig.getDatabasePassword();
		Class.forName(databaseConfig.getDatabaseDriver());
		return DriverManager.getConnection(databaseUrlFull, databaseUserName, databasePassword);
	}

	/**
	 * 
	 * <p>
	 * 获取column的resultSet
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
	/**
	 * 
	 * <p>
	 * 获取主键的resultSet
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
	public ResultSet getPrimaryKeyResultSet() {
		if (databaseConfig == null) {
			return null;
		}
		DatabaseMetaData databaseMetaData = null;
		Connection connection = null;
		try {
			connection = getConnection();
			databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, databaseConfig.getDatabaseName(), databaseConfig.getTableName());
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void verifyDatabaseDataSourcesData() {
		if (EcBaseUtils.isNull(databaseConfig)) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_CONFIG_CANT_NULL);
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
