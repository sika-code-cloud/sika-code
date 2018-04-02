package com.dq.easy.cloud.module.common.generator.code.base.utils;

import java.math.BigDecimal;
import java.util.Date;

import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 描述：代码生成器 Created by Ay on 2017/5/1.
 */
public class DqCodeGenerateUtils {

	/**
	 * 
	 * <p>
	 * 获取完整的类类型
	 * </p>
	 *
	 * @param fullPackageName
	 * @param simpleClassType
	 * @return
	 * @author daiqi
	 * 创建时间    2018年4月2日 上午9:47:17
	 */
	public static String getFullClassType(String fullPackageName, String simpleClassType) {
		StringBuilder build = DqStringUtils.newStringBuilderDefault();
		build.append(fullPackageName).append(DqSymbol.STOP).append(simpleClassType);
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
		StringBuilder packagePathBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0 ; i < packageNameArr.length; ++i) {
			String tempModelBasePackageName = packageNameArr[i];
			packagePathBuild.append(tempModelBasePackageName);
			if (i < packageNameArr.length - 1) {
				packagePathBuild.append(DqSymbol.BACK_SLASH);
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
	
	/**
	 * 
	 * <p>
	 * 根据类名简称获取完整的类名信息
	 * </p>
	 *
	 * @param simpleClassName : String : 类名简称
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月23日 下午2:05:23
	 */
	public static String getCompleteClassName(String simpleClassName) {
		return DqMapUtils.getString(DqCodeGenerateConfig.getClassNameSimpleMapping(), simpleClassName);
	}
	
	public static String getJavaSimpleClassTypeOfMysql(String columnType) {
		if (DqStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = DqStringUtils.lowerCase(columnType);
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			return Integer.class.getSimpleName();
		} else if(tempColumnType.contains("float")) {
			return Float.class.getSimpleName();
		} else if(tempColumnType.contains("double")) {
			return Double.class.getSimpleName();
		} else if(tempColumnType.contains("bigint")) {
			return Long.class.getSimpleName();
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			return String.class.getSimpleName();
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			return Date.class.getSimpleName();
		} else if(tempColumnType.contains("decimal")) {
			return BigDecimal.class.getSimpleName();
		} else if(tempColumnType.contains("blob")) {
			return "Byte []";
		}
		return null;
	}
	
	public static String getJavaFullClassTypeOfMysql(String columnType) {
		if (DqStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = DqStringUtils.lowerCase(columnType);
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			return Integer.class.getName();
		} else if(tempColumnType.contains("float")) {
			return Float.class.getName();
		} else if(tempColumnType.contains("double")) {
			return Double.class.getName();
		} else if(tempColumnType.contains("bigint")) {
			return Long.class.getName();
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			return String.class.getName();
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			return Date.class.getName();
		} else if(tempColumnType.contains("decimal")) {
			return BigDecimal.class.getName();
		} else if(tempColumnType.contains("blob")) {
			return "Byte []";
		}
		return null;
	}
}
