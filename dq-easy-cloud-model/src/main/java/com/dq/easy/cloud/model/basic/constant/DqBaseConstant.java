package com.dq.easy.cloud.model.basic.constant;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;

/**
 * 
 * <p>
 * 	基础常量类
 * </p>
 *
 * <pre>
 *  说明：所有常量都应该继承此类
 *  约定：各个模块的各状态等常量类应该做为静态累不内放在统一的模块常量类中并且最终应该继承DqBaseConstant
 *  命名规范：各个模块常量类以constant为后缀
 *  使用示例：用户模块：用户状态  
 *  public class UserConstant{
 *  	public static class UserStatus{
 *  		public static final int effective = 1; // 有效
 *  		public static final int invalid = 1; // 无效
 *  	} 
 *  }
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
	public static <T extends DqBaseConstant> boolean isAvailableValue(Class<T> clazz, Object value) {
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
	public static <T extends DqBaseConstant> boolean isNotAvailableValue(Class<T> clazz, Object value) {
		return !isAvailableValue(clazz, value);
	}
}
