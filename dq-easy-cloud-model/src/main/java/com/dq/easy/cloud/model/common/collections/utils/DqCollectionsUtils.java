package com.dq.easy.cloud.model.common.collections.utils;

import java.util.Collection;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

/**
 * 
 * @ClassName : DqCollectionsUtils 
 * @Description : 集合工具类 
 * @author daiqi
 * @date 2017年12月6日 下午2:49:44 
 *
 */
@SuppressWarnings("rawtypes")
public class DqCollectionsUtils {
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
		if(DqBaseUtils.isNull(collection) || collection.size() == 0){
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
