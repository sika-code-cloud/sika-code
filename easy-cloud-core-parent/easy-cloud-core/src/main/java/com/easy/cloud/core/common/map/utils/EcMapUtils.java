package com.easy.cloud.core.common.map.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.MapUtils;

/**
 * 
 * @author daiqi
 *
 */
public class EcMapUtils {

	/** 初始化大小 */
	private static final int INIT_SIZE = 128;

	/**
	 * 
	 * <p>
	 * 创建含有初始化大小的HashMap对象
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.newHashMap()
	 * </pre>
	 *
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月18日 上午12:55:07
	 */
	public static <T, V> Map<T, V> newHashMap() {
		return new HashMap<>(INIT_SIZE);
	}

	/**
	 * 
	 * <p>
	 * 创建含有初始化大小的Hashtable对象
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.newHashMap()
	 * </pre>
	 *
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月18日 上午12:55:07
	 */
	public static <T, V> Map<T, V> newHashTable() {
		return new Hashtable<>(INIT_SIZE);
	}

	/**
	 * 
	 * <p>
	 * 创建含有初始化大小的LinkedHashMap对象
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.newLinkedHashMap()
	 * </pre>
	 *
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月18日 上午12:55:07
	 */
	public static <T, V> Map<T, V> newLinkedHashMap() {
		return new LinkedHashMap<>(INIT_SIZE);
	}

	/**
	 * 
	 * <p>
	 * 创建含有初始化大小的TreeMap对象
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.newHashMap()
	 * </pre>
	 *
	 * @return
	 *
	 * author daiqi 创建时间 2018年3月18日 上午12:55:07
	 */
	public static <T, V> Map<T, V> newTreeMap() {
		return new TreeMap<>();
	}
	/**
	 * 
	 * <p>
	 * 创建含有初始化大小的ConcurrentHashMap对象
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月16日 上午10:32:36
	 */
	public static <T, V> Map<T, V> newConcurrentHashMap() {
		return new ConcurrentHashMap<>(INIT_SIZE);
	}
	
	/**
	 * 
	 * <p>
	 * 判断map是否为空
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.isEmpty(null) = true
	 * </pre>
	 *
	 * @param map
	 * @return <code>true</code> 当map == null || map.size() == 0
	 *
	 *         author daiqi 创建时间 2017年12月6日 下午11:25:19
	 */
	public static <T, V> boolean isEmpty(Map<T, V> map) {
		if (map == null || map.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 判断map中是否包含key
	 * </p>
	 *
	 * @param map
	 * @param key
	 * @return
	 * @author daiqi 创建时间 2018年3月21日 下午8:33:51
	 */
	public static <T, V> boolean containsKey(Map<T, V> map, String key) {
		if (isEmpty(map)) {
			return false;
		}
		return map.containsKey(key);
	}

	/**
	 * 
	 * <p>
	 * 判断map是否非空
	 * </p>
	 *
	 * <pre>
	 * EcMapUtils.isEmpty(null) = false
	 * </pre>
	 *
	 * @param map
	 * @return <code>true</code> 当map != null && map.size() > 0
	 *
	 *         author daiqi 创建时间 2017年12月6日 下午11:25:19
	 */

	public static <T, V> boolean isNotEmpty(Map<T, V> map) {
		return !isEmpty(map);
	}

	public static <T, V> String getString(Map<T, V> map, String key) {
		return MapUtils.getString(map, key);
	}

	public static <T, V> Integer getInteger(Map<T, V> map, String key) {
		return MapUtils.getInteger(map, key);
	}

	public static <T, V> Long getLong(Map<T, V> map, String key) {
		return MapUtils.getLong(map, key);
	}

	public static <T, V> Boolean getBoolean(Map<T, V> map, String key) {
		return MapUtils.getBoolean(map, key);
	}

	public static <T, V> Short getShort(Map<T, V> map, String key) {
		return MapUtils.getShort(map, key);
	}

	public static <T, V> Double getDouble(Map<T, V> map, String key) {
		return MapUtils.getDouble(map, key);
	}

	public static <T, V> Float getFloat(Map<T, V> map, String key) {
		return MapUtils.getFloat(map, key);
	}

	public static <T, V> Object getObject(Map<T, V> map, String key) {
		return MapUtils.getObject(map, key);
	}
}
