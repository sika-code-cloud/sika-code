package com.easy.cloud.core.cache.redis.annotation;

import com.easy.cloud.core.cache.redis.proxy.EcRedisProxy;

/**
 * <p>
 * redis注解
 * </p>
 *
 * <pre>
 *  说明：通过在需要提供reids的方法上添加该注解 ，即可对方法提供将数据库保存倒reids中的的功能
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 * 
 * @author daiqi
 * @date 2018年4月20日 下午9:12:55
 */
public @interface EcRedisAnnotation {

	/**
	 * 
	 * <p>
	 * 处理redis逻辑的代理类class，所有的代理类最终都需要实现EcRedisProxy接口
	 * </p>
	 * 
	 * <pre>
	 * 通过传入不同的代理类的class从而对对redis进行定制化处理
	 * </pre>
	 *
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午10:48:19
	 */
	Class<? extends EcRedisProxy> proxyClass() default EcRedisProxy.class;

}
