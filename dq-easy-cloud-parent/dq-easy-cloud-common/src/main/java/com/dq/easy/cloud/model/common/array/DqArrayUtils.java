package com.dq.easy.cloud.model.common.array;

import org.apache.commons.lang3.ArrayUtils;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 数组工具类
 * @author daiqi
 * @date 2018年2月8日 下午10:39:37
 */

public class DqArrayUtils {
	/**
	 * <p>是否是空数组</p>
	 *
	 * <pre>
	 * 
	 * </pre>
	 * @param array : 数组
	 * @return 是返回true 不是返回false
	 *
	 * author daiqi
	 * 创建时间  2018年2月8日 下午10:42:37
	 */
	public static <T> boolean isEmpty(T [] array){
		return ArrayUtils.isEmpty(array);
	}
	
	/**
	 * <p>是否是非空数组</p>
	 *
	 * <pre>
	 * 
	 * </pre>
	 * @param array : 数组
	 * @return 是非空返回true 不是非空返回false
	 *
	 * author daiqi
	 * 创建时间  2018年2月8日 下午10:42:37
	 */
	public static <T> boolean isNotEmpty(T [] array){
		return !isEmpty(array);
	}
	
	/**
	 * <p>将fromArray中得数据压入到targetArray数组中</p>
	 *
	 * @param fromArray
	 * @param targetArray
	 *
	 * author daiqi
	 * 创建时间  2018年2月8日 下午11:04:54
	 */
	public static <T> void putToTargetArray(T [] fromArray, T[] targetArray){
		if (isEmpty(fromArray) || isEmpty(targetArray)){
			return ;
		}
		for (int i = 0 ; i < targetArray.length ; ++i){
			targetArray[i] = fromArray[i];
		}
	}
	
	/**
	 * 
	 * <p>
	 * 获取class数组的字符串格式
	 * </p>
	 *
	 * @param classArray
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 下午2:15:22
	 */
	public static String getClassArrayStr(Class<?> [] classArray){
		if (DqArrayUtils.isEmpty(classArray)){
			return null;
		}
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		sb.append("[");
		for(int i = 0 ; i < classArray.length ; ++i){
			sb.append(classArray[i].getName());
			if (i != classArray.length - 1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
