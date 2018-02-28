package com.dq.easy.cloud.model.common.number.utils;

import org.apache.commons.lang3.math.NumberUtils;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

/***
 * 
 * <p>
 * 数值工具类
 * </p>
 *
 * <pre>
 *  说明：所有数值类型的工具类皆可以继承此类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月7日 下午7:49:33
 */
public class DqNumberUtils extends DqBaseUtils{
	/**
	 * 
	 * <p>
	 * 判断字符串是否为整数
	 * </p>
	 *
	 * @param str
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午3:56:21
	 */
	public static boolean isDigits(String str){
		return NumberUtils.isDigits(str);
	}
	/**
	 * 
	 * <p>
	 * 判断字符串是否为数字
	 * </p>
	 *
	 * @param str
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午3:56:21
	 */
	public static boolean isNumber(String str){
		return NumberUtils.isNumber(str);
	}
	
}
