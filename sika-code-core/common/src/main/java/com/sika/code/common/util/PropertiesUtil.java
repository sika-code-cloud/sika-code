package com.sika.code.common.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName : PropertiesUtils 
 * @Description : 读取配置文件工具类 
 * @author daiqi
 * @date 2017年12月6日 上午9:59:04 
 *
 */
@Component
public class PropertiesUtil {
//	默认从application.properties中取数据
	private static Environment environment;
	
	
	
	public static Environment getEnvironment() {
		return environment;
	}
	
	@Autowired
	public void setEnvironment(Environment environment) {
		PropertiesUtil.environment = environment;
	}
	/**
	 * <p></p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param key
	 * @param targetType
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 下午3:32:29
	 */
	private static <T> T getProperty(String key, Class<T> targetType){
		return environment.getProperty(key, targetType);
	}
	/**
	 * 
	 * <p>根据指定key获取String类型的Value值,当值不存在的时候返回null</p>
	 *
	 * <pre>
	 * DqStringUtils.getStringValue(null) = null
	 * DqStringUtils.getStringValue("") = null
	 * </pre>
	 *
	 * @param key : propertis中的key
	 * @return <code>null</code>当key对应的值不存在的
	 * @author daiqi
	 * @date 2017年12月6日 上午10:56:57
	 */
	public static String getStringValue(String key){
		return getProperty(key,String.class);
	}
	/**
	 * 
	 * <p>根据指定key获取Integer类型的值，当值不存在的时候返回null</p>
	 *
	 * <pre>
	 * DqStringUtils.getIntegerValue(null) = null
	 * DqStringUtils.getIntegerValue("") = null
	 * DqStringUtils.getIntegerValue("intKey") = 2
	 * </pre>
	 *
	 * @param key
	 * @return 值存在返回对应的值不存在返回null
	 * @author daiqi
	 * @date 2017年12月6日 上午11:01:41
	 */
	public static Integer getIntegerValue(String key){
		return getProperty(key, Integer.class);
	}
	/**
	 * 
	 * <p>根据指定key获取Double类型的值，当值不存在的时候返回null</p>
	 *
	 * <pre>
	 * DqStringUtils.getDoubleValue(null) = null
	 * DqStringUtils.getDoubleValue("") = null
	 * DqStringUtils.getDoubleValue("doublekey") = 2.0
	 * </pre>
	 *
	 * @param key
	 * @return 值存在返回对应的值不存在返回null
	 * @author daiqi
	 * @date 2017年12月6日 上午11:03:35
	 */
	public static Double getDoubleValue(String key){
		return getProperty(key, Double.class);
	}
	/**
	 * 
	 * <p>根据指定key获取Boolean类型的值，当值不存在的时候返回 null</p>
	 *
	 * <pre>
	 * DqStringUtils.getDoubleValue(null) = null
	 * DqStringUtils.getDoubleValue("") = null
	 * DqStringUtils.getDoubleValue("booleankey") = true
	 * </pre>
	 *
	 * @param key
	 * @return 值存在返回对应的值不存在返回null
	 * @author daiqi
	 * @date 2017年12月6日 上午11:03:35
	 */
	public static Boolean getBooleanValue(String key){
		return getProperty(key, Boolean.class);
	}
	/**
	 * 
	 * <p>根据指定key获取Long类型的值，当值不存在的时候返回 null</p>
	 *
	 * <pre>
	 * DqStringUtils.getDoubleValue(null) = null
	 * DqStringUtils.getDoubleValue("") = null
	 * DqStringUtils.getDoubleValue("longkey") = 1
	 * </pre>
	 *
	 * @param key
	 * @return 值存在返回对应的值不存在返回null
	 * @author daiqi
	 * @date 2017年12月6日 上午11:03:35
	 */
	public static Long getLongValue(String key){
		return getProperty(key, Long.class);
	}
	/**
	 * 
	 * <p>根据指定key获取Float类型的值，当值不存在的时候返回 null</p>
	 *
	 * <pre>
	 * DqStringUtils.getDoubleValue(null) = null
	 * DqStringUtils.getDoubleValue("") = null
	 * DqStringUtils.getDoubleValue("floatkey") = 1.0
	 * </pre>
	 *
	 * @param key
	 * @return 值存在返回对应的值不存在返回null
	 * @author daiqi
	 * @date 2017年12月6日 上午11:03:35
	 */
	public static Float getFloutValue(String key){
		return getProperty(key, Float.class);
	}
	
}
