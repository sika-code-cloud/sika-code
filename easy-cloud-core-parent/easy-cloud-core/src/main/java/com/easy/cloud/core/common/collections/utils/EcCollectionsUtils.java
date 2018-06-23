package com.easy.cloud.core.common.collections.utils;

import java.util.Collection;

import com.easy.cloud.core.basic.utils.EcBaseUtils;

/**
 * 
 * @ClassName : DqCollectionsUtils 
 * @Description : 集合工具类 
 * @author daiqi
 * @date 2017年12月6日 下午2:49:44 
 *
 */
@SuppressWarnings("rawtypes")
public class EcCollectionsUtils {
	
	/**
	 * 
	 * <p>判断该集合是否为空</p>
	 *
	 * @param collection
	 * @return <code>true</code> 条件：collection == null || collection.size() == 0
	 * @author daiqi
	 * @date 2017年12月6日 下午2:51:01
	 */
	public static boolean isEmpty(Collection collection){
		if(EcBaseUtils.isNull(collection) || collection.size() == 0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>判断该集合是否非空</p>
	 *
	 * @param collection
	 * @return <code>true</code> 条件：collection != null && collection.size() > 0
	 * @author daiqi
	 * @date 2017年12月6日 下午2:51:01
	 */
	public static boolean isNotEmpty(Collection collection){
		return !isEmpty(collection);
	}
	
}
