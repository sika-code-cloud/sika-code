package com.dq.easy.cloud.model.common.map.utils;

import java.util.Map;

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
	
	
}
