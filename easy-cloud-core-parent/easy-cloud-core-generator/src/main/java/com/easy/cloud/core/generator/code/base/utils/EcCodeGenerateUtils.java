package com.easy.cloud.core.generator.code.base.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.config.EcCodeGenerateConfig;

/**
 * 描述：代码生成器 Created by Ay on 2017/5/1.
 */
public class EcCodeGenerateUtils {

	/**
	 * 
	 * <p>
	 * 获取完整的类类型
	 * </p>
	 *
	 * @param fullPackageName
	 * @param simpleClassType
	 * @return
	 * @author daiqi 创建时间 2018年4月2日 上午9:47:17
	 */
	public static String getFullClassType(String fullPackageName, String simpleClassType) {
		StringBuilder build = EcStringUtils.newStringBuilder();
		build.append(fullPackageName).append(EcSymbol.STOP).append(simpleClassType);
		return build.toString();
	}

	/**
	 * 
	 * <p>
	 * 将包名转换为路径字符串
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param packegeName
	 * @return
	 * @author daiqi 创建时间 2018年3月22日 下午7:32:59
	 */
	public static String changePackageNameToPath(String packegeName) {
		String[] packageNameArr = packegeName.split("\\.");
		StringBuilder packagePathBuild = EcStringUtils.newStringBuilder();
		for (int i = 0; i < packageNameArr.length; ++i) {
			String tempModelBasePackageName = packageNameArr[i];
			packagePathBuild.append(tempModelBasePackageName);
			if (i < packageNameArr.length - 1) {
				packagePathBuild.append(EcSymbol.BACK_SLASH);
			}
		}
		return packagePathBuild.toString();
	}

	/**
	 * 
	 * <p>
	 * 获取数据库完整url
	 * </p>
	 *
	 * @param baseUrl
	 *            : String : 数据库基础url
	 * @param port
	 *            ： String : 数据库端口号
	 * @param databaseName
	 *            : String : 数据库名称
	 * @return 数据库完整url
	 * @author daiqi 创建时间 2018年3月22日 下午2:14:51
	 */
	public static String getDatabaseFullUrl(String baseUrl, String port, String databaseName) {
		StringBuilder sb = EcStringUtils.newStringBuilder();
		return sb.append(baseUrl).append(EcSymbol.COLON).append(port).append(EcSymbol.FORWARD_SLASH)
				.append(databaseName).toString();
	}

	/**
	 * 
	 * <p>
	 * 根据类名简称获取完整的类名信息
	 * </p>
	 *
	 * @param simpleClassName
	 *            : String : 类名简称
	 * @return
	 * @author daiqi 创建时间 2018年3月23日 下午2:05:23
	 */
	public static String getCompleteClassName(String simpleClassName) {
		return EcMapUtils.getString(EcCodeGenerateConfig.getClassNameSimpleMapping(), simpleClassName);
	}

	public static String getJavaSimpleClassTypeOfMysql(String columnType) {
		if (EcStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = EcStringUtils.lowerCase(columnType);
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			return Integer.class.getSimpleName();
		} else if (tempColumnType.contains("float")) {
			return Float.class.getSimpleName();
		} else if (tempColumnType.contains("double")) {
			return Double.class.getSimpleName();
		} else if (tempColumnType.contains("bigint")) {
			return Long.class.getSimpleName();
		} else if (tempColumnType.contains("char") || tempColumnType.contains("text")) {
			return String.class.getSimpleName();
		} else if (tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			return Date.class.getSimpleName();
		} else if (tempColumnType.contains("decimal")) {
			return BigDecimal.class.getSimpleName();
		} else if (tempColumnType.contains("blob")) {
			return "Byte []";
		}
		return null;
	}

	public static String getJavaFullClassTypeOfMysql(String columnType) {
		if (EcStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = EcStringUtils.lowerCase(columnType);
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			return Integer.class.getName();
		} else if (tempColumnType.contains("float")) {
			return Float.class.getName();
		} else if (tempColumnType.contains("double")) {
			return Double.class.getName();
		} else if (tempColumnType.contains("bigint")) {
			return Long.class.getName();
		} else if (tempColumnType.contains("char") || tempColumnType.contains("text")) {
			return String.class.getName();
		} else if (tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			return Date.class.getName();
		} else if (tempColumnType.contains("decimal")) {
			return BigDecimal.class.getName();
		} else if (tempColumnType.contains("blob")) {
			return "Byte []";
		}
		return null;
	}

	/** 获取ibatis的jdbctype类型 */
	public static String getIbatisJdbcTypeOfMysql(String columnType) {
		if (EcStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = EcStringUtils.lowerCase(columnType);
		JdbcType jdbcType = null;
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			jdbcType = JdbcType.INTEGER;
		} else if (tempColumnType.contains("float")) {
			jdbcType = JdbcType.FLOAT;
		} else if (tempColumnType.contains("double")) {
			jdbcType = JdbcType.DOUBLE;
		} else if (tempColumnType.contains("bigint")) {
			jdbcType = JdbcType.BIGINT;
		} else if (tempColumnType.equals("char")) {
			jdbcType = JdbcType.CHAR;
		} else if (tempColumnType.contains("varchar")) {
			jdbcType = JdbcType.VARCHAR;
		} else if (tempColumnType.contains("text")) {
			jdbcType = JdbcType.LONGVARCHAR;
		} else if (tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			jdbcType = JdbcType.TIMESTAMP;
		} else if (tempColumnType.contains("decimal")) {
			jdbcType = JdbcType.DECIMAL;
		} else if (tempColumnType.contains("blob")) {
			jdbcType = JdbcType.BLOB;
		}
		return jdbcType == null ? null : jdbcType.name();
	}
}
