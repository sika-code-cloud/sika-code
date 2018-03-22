package com.dq.easy.cloud.model.common.generator.code.config;

import java.util.Date;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 代码生成工具所需的配置信息
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月21日 下午4:48:32
 */
public class DqCodeGenerateConfig {
	/** 默认获取计算机的用户名 */
	public static final String AUTHOR_DEFAULT = System.getenv().get("USERNAME");
	/** 创建时间 */
	public static final String CREATE_DATE = DqDateFormatUtils.format(new Date(), DqDateFormatUtils.FORMAT_NORMAL);
	/** 代码模版基础包路径 */
	public static final String CODE_TEMPLATE_BASE_PACKAGE_PATH = "/codetemplates";
	/** 数据库基础url */
	public static final String DATABASE_BASE_URL = "jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com";
	/** 数据库端口 */
	public static final String DATABASE_PORT = "3306";
	/** 数据库名称 */
	public static final String DATABASE_NAME = "sea_share_db";
	/** 数据库完整url */
	public static final String DATABASE_FULL_URL = getDatabaseFullUrl(DATABASE_BASE_URL, DATABASE_PORT, DATABASE_NAME);
	/** 数据库用户名 */
	public static final String DATABASE_USER_NAME = "seashare";
	/** 数据库密码 */
	public static final String DATABASE_PASSWORD = "Seashare123";
	/** 数据库驱动---mysql---com.mysql.jdbc.Driver */
	public static final String DATABASE_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	/** 数据库驱动---oracle---oracle.jdbc.driver.OracleDriver */
	public static final String DATABASE_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * 
	 * <p>
	 * 获取数据库完整url
	 * </p>
	 *
	 * @param baseUrl : String : 数据库基础url
	 * @param port ： String : 数据库端口号
	 * @param databaseName : String : 数据库名称
	 * @return 数据库完整url
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午2:14:51
	 */
	public static String getDatabaseFullUrl(String baseUrl, String port, String databaseName) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		return sb.append(baseUrl).append(DqSymbol.SPLIT_COLON).append(port).append(DqSymbol.FORWARD_SLASH).append(databaseName).toString();
	}
}
