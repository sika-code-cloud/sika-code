package com.dq.easy.cloud.module.common.reflection.utils;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;

/**
 * 反射工具类
 * @author daiqi
 * @date 2018年1月8日 下午7:46:12
 */
public class DqReflectionUtils {
	/**
	 * 
	 * <p>
	 * 创建class对应的实例对象
	 * </p>
	 * @param clazz : Class : 泛型class
	 * @return 泛型对象
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午10:18:55
	 */
	public static <T> T newInstance(Class<T> clazz){
		if(DqBaseUtils.isNull(clazz)){
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
