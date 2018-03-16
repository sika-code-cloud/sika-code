package com.dq.easy.cloud.model.common.map.utils;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @author daiqi
 *
 */
public class DqMapUtils {
	/**
	 * 
	 * <p>判断map是否为空</p>
	 *
	 * <pre>
	 * DqMapUtils.isEmpty(null) = true
	 * </pre>
	 *
	 * @param map
	 * @return <code>true</code> 当map == null || map.size() == 0 
	 *
	 * author daiqi
	 * 创建时间  2017年12月6日 下午11:25:19
	 */
	public static <T,V> boolean isEmpty(Map<T,V> map){
		if(map == null || map.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * <p>判断map是否非空</p>
	 *
	 * <pre>
	 * DqMapUtils.isEmpty(null) = false
	 * </pre>
	 *
	 * @param map
	 * @return <code>true</code> 当map != null && map.size() > 0 
	 *
	 * author daiqi
	 * 创建时间  2017年12月6日 下午11:25:19
	 */

	public static <T,V> boolean isNotEmpty(Map<T,V> map){
		return !isEmpty(map);
	}
	
	public static <T,V> String getString(Map<T,V> map, String key){
		return MapUtils.getString(map, key);
	}
	
	public static <T,V> Integer getInteger(Map<T,V> map, String key){
		return MapUtils.getInteger(map, key);
	}
	
	public static <T,V> Long getLong(Map<T,V> map, String key){
		return MapUtils.getLong(map, key);
	}
	
	public static <T,V> Boolean getBoolean(Map<T,V> map, String key){
		return MapUtils.getBoolean(map, key);
	}
	
	public static <T,V> Short getShort(Map<T,V> map, String key){
		return MapUtils.getShort(map, key);
	}
	
	public static <T,V> Double getDouble(Map<T,V> map, String key){
		return MapUtils.getDouble(map, key);
	}
	public static <T,V> Float getFloat(Map<T,V> map, String key){
		return MapUtils.getFloat(map, key);
	}
	public static <T,V> Object getObject(Map<T,V> map, String key){
		return MapUtils.getObject(map, key);
	}
}
