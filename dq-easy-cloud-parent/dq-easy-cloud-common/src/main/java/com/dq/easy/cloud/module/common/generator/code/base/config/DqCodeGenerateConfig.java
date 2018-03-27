package com.dq.easy.cloud.module.common.generator.code.base.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.dq.easy.cloud.module.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameSimple;
import com.dq.easy.cloud.module.common.map.utils.DqMapUtils;

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
	/** 数据库驱动---mysql---com.mysql.jdbc.Driver */
	public static final String DATABASE_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	/** 数据库驱动---oracle---oracle.jdbc.driver.OracleDriver */
	public static final String DATABASE_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static Map<String, Object>  CLASS_NAME_SIMPLE_MAPPING = DqMapUtils.newHashMap();
	/** 过滤的文件名称 */
	public static Map<String, Object> NEED_FILTER_DIRECTORY_NAME = new HashMap<>();

	static {
		initSimpleClassNameMapping();
		NEED_FILTER_DIRECTORY_NAME.put("target", true);
		NEED_FILTER_DIRECTORY_NAME.put("src", true);
		NEED_FILTER_DIRECTORY_NAME.put(".git", true);
		NEED_FILTER_DIRECTORY_NAME.put(".settings", true);
	}
	
	private static void initSimpleClassNameMapping() {
		CLASS_NAME_SIMPLE_MAPPING.put(DqClassNameSimple.DATE, "java.util.Date");
		CLASS_NAME_SIMPLE_MAPPING.put(DqClassNameSimple.BIGDECIMAL, "java.math.BigDecimal");
	}
	
	public static Map<String, Object> getClassNameSimpleMapping() {
		return CLASS_NAME_SIMPLE_MAPPING;
	}
	public static Map<String, Object> getNeedFilterDirectoryName() {
		return NEED_FILTER_DIRECTORY_NAME;
	}
	
}
