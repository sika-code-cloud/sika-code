package com.dq.easy.cloud.module.common.generator.code.base.sources.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField;
import com.dq.easy.cloud.module.common.generator.code.base.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.dto.database.DqFieldDatabaseAbstractDTO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.dto.database.mysql.DqFieldMysqlDTO;
import com.dq.easy.cloud.module.common.generator.code.base.sources.DqBaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 数据库生成代码的方式
 * @author daiqi
 * @date 2018年3月24日 上午10:40:29
 */
public abstract class DqDatabaseDataSources extends DqBaseDataSources{
	private DqDatabaseAbstactConfig databaseConfig;
	private String tableName;

	public DqDatabaseDataSources(DqDatabaseAbstactConfig databaseConfig, String tableName) {
		this.databaseConfig = databaseConfig;
		this.tableName = tableName;
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

		DatabaseMetaData databaseMetaData = null;
		try (
				Connection connection = getConnection()
		) {
			databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
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
	
	protected List<?> buildFieldDTOs() {
		List<DqJavaContentDesc> dqFieldBaseDTOs = new ArrayList<>();
		ResultSet resultSet = getResultSet();
		try {
			while (resultSet.next()) {
				// id字段略过
				if (DqIgnoreField.isIgnoreField(resultSet.getString("COLUMN_NAME"))) {
					continue;
				}
				DqFieldDatabaseAbstractDTO dqFieldDTO = new DqFieldMysqlDTO();
				// 获取字段名称
				dqFieldDTO.setTableColumnName(resultSet.getString("COLUMN_NAME"));
				// 获取字段类型
				dqFieldDTO.setTableColumnType(resultSet.getString("TYPE_NAME"));
				// 字段在数据库的注释
				dqFieldDTO.setComment(resultSet.getString("REMARKS"));
				dqFieldDTO.setName(DqStringUtils.replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
				dqFieldBaseDTOs.add(dqFieldDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dqFieldBaseDTOs;
	}	
	
}
