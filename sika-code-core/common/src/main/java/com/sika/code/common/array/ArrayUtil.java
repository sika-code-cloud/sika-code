package com.sika.code.common.array;

import com.sika.code.common.string.util.StringUtil;

/**
 * 数组工具类
 * @author daiqi
 * @date 2018年2月8日 下午10:39:37
 */

public class ArrayUtil extends cn.hutool.core.util.ArrayUtil {

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
		if (ArrayUtil.isEmpty(classArray)){
			return null;
		}
		StringBuilder sb = StringUtil.newStringBuilder();
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
