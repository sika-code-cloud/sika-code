package com.easy.cloud.core.cache.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisActionType;
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
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EcRedisAnnotation {
	/**
	 * 
	 * <p>redis执行的动作---包括增删改查等</p>
	 *
	 * <pre>详情参考EcRedisActionType枚举类</pre>
	 *
	 * @return EcRedisActionType
	 *
	 * author daiqi
	 * 创建时间  2018年4月26日 上午12:00:21
	 */
	EcRedisActionType actionType(); 
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
	Class<? extends EcRedisProxy> proxyClass();

}
