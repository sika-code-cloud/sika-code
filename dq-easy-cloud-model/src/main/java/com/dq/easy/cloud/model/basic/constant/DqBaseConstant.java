package com.dq.easy.cloud.model.basic.constant;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

/**
 * 
 * <p>
 * 基础常量类
 * </p>
 *
 * <pre>
 *  说明：所有常量可以继承此类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月6日 下午1:46:21
 */
public class DqBaseConstant {
	/**
	 * 
	 * <p>
	 * 校验当前值在clazz类中是否是可用的值 是返回true、否则返回false
	 * </p>
	 *
	 * @param clazz : Class : 校验的类
	 * @param value : Object : 校验的值
	 * @return boolean
	 * @author daiqi 创建时间 2018年2月6日 下午1:41:47
	 */
	public static boolean isAvailableValue(Class<?> clazz, Object value) {
		if (DqBaseUtils.isNull(clazz) || DqBaseUtils.isNull(value)) {
			return false;
		}
		return DqBaseUtils.isExistConstantValue(clazz, value);
	}
	
	/**
	 * 
	 * <p>
	 * 校验当前值在clazz类中是否是不可用的值 是不可用返回true、否则返回false
	 * </p>
	 *
	 * @param clazz : Class : 校验的类
	 * @param value : Object : 校验的值
	 * @return boolean
	 * @author daiqi 创建时间 2018年2月6日 下午1:41:47
	 */
	public static boolean isNotAvailableValue(Class<?> clazz, Object value) {
		return !isAvailableValue(clazz, value);
	}
}
