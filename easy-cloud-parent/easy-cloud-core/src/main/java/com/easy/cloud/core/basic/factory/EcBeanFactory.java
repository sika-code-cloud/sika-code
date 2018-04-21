package com.easy.cloud.core.basic.factory;

/**
 * 
 * <p>
 * 对象创建工厂
 * </p>
 *
 * @author daiqi
 * @创建时间    2018年4月14日 上午11:53:23
 */
public class EcBeanFactory {
	/**
	 * 
	 * <p>
	 * 根据beanClazz 创建对象
	 * </p>
	 *
	 * @param beanClazz : Class : 泛型类的class
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午11:54:45
	 */
	public static <T> T newInstance(Class<T> beanClazz) {
		try {
			return beanClazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
