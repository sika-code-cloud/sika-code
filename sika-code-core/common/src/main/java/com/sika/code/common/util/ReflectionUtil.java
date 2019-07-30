package com.sika.code.common.util;

import cn.hutool.core.util.ReflectUtil;
import com.sika.code.basic.util.BaseUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 * @author daiqi
 * @date 2018年1月8日 下午7:46:12
 */
public class ReflectionUtil extends ReflectUtil {
	
	/**
	 * 
	 * <p>获取指定class类及其继承的所有父类除了Object类的属性</p>
	 *
	 * <pre></pre>
	 *
	 * @param targetClass
	 * @return
	 *
	 * @author daiqi
	 * @创建时间  2018年5月4日 下午10:14:33
	 */
	public static List<Field> getDeclaredFieldsIncSup(final Class<?> targetClass) {
		List<Field> fieldList = new ArrayList<>() ;
		Class<?> tempClass = targetClass;
		//当父类为null的时候说明到达了最上层的父类(Object类).
		while (BaseUtil.isNotNull(tempClass)) {
		      fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
			//得到父类,然后赋给自己
		      tempClass = tempClass.getSuperclass();
		}
		return fieldList;
	}
	
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
		if(BaseUtil.isNull(clazz)){
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
