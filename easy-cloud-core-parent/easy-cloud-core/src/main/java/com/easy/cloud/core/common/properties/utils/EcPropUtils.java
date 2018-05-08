package com.easy.cloud.core.common.properties.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 读取propties文件的工具类，不需要spring容器进行管理
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午4:37:32
 */
public class EcPropUtils {

	private static Properties load(String configFileName) {
		try {
			URL url = EcPropUtils.class.getClassLoader().getResource(configFileName);
			File propFile = new File(url.getFile());
			Properties props = new Properties();
			props.load(new InputStreamReader(new FileInputStream(propFile), "UTF-8"));
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * <p>
	 * 从fileName相对路径的propertis文件获取key对应的值,不需带properties后缀
	 * </p>
	 *
	 * @param fileName
	 * @param key
	 * @return
	 * @author daiqi
	 * @创建时间 2018年5月7日 下午4:38:17
	 */
	public static String getString(String fileName, String key) {
		return EcMapUtils.getString(getPropertis(fileName), key);
	}

	public static Boolean getBoolean(String fileName, String key) {
		return EcMapUtils.getBoolean(getPropertis(fileName), key);
	}

	public static Long getLong(String fileName, String key) {
		return EcMapUtils.getLong(getPropertis(fileName), key);
	}

	public static Integer getInteger(String fileName, String key) {
		return EcMapUtils.getInteger(getPropertis(fileName), key);
	}

	public static Float getFloat(String fileName, String key) {
		return EcMapUtils.getFloat(getPropertis(fileName), key);
	}

	public static Double getDouble(String fileName, String key) {
		return EcMapUtils.getDouble(getPropertis(fileName), key);
	}

	public static Object getObject(String fileName, String key) {
		return EcMapUtils.getObject(getPropertis(fileName), key);
	}

	public static Properties getPropertis(String fileName) {
		String fullFileName = fileName;
		if (!EcStringUtils.endsWith(fullFileName, ".properties")) {
			fullFileName = fileName + EcSymbol.STOP + "properties";
		}
		return load(fullFileName);
	}

}
