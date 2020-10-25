package com.sika.code.common.util;

import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.string.constant.StringConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * <p>
 * 读取propties文件的工具类，不需要spring容器进行管理
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午4:37:32
 */
public class PropUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropUtil.class);
	private static Properties load(String configFileName) {
		try {
			URL url = PropUtil.class.getClassLoader().getResource(configFileName);
			File propFile = new File(url.getFile());
			Properties props = new Properties();
			props.load(new InputStreamReader(new FileInputStream(propFile), "UTF-8"));
			return props;
		} catch (IOException e) {
			LOGGER.warn("加载文件失败", e);
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
		return MapUtil.getStr(getPropertis(fileName), key);
	}

	public static Boolean getBoolean(String fileName, String key) {
		return MapUtil.getBool(getPropertis(fileName), key);
	}

	public static Long getLong(String fileName, String key) {
		return MapUtil.getLong(getPropertis(fileName), key);
	}

	public static Integer getInteger(String fileName, String key) {
		return MapUtil.getInt(getPropertis(fileName), key);
	}

	public static Float getFloat(String fileName, String key) {
		return MapUtil.getFloat(getPropertis(fileName), key);
	}

	public static Double getDouble(String fileName, String key) {
		return MapUtil.getDouble(getPropertis(fileName), key);
	}

	public static Object getObject(String fileName, String key) {
		return MapUtil.get(getPropertis(fileName), key, Object.class);
	}

	public static Properties getPropertis(String fileName) {
		String fullFileName = fileName;
		if (!StringUtil.endWith(fullFileName, ".properties")) {
			fullFileName = fileName + StringConstant.Symbol.STOP + "properties";
		}
		return load(fullFileName);
	}

}
