package com.sika.code.cache.redis.proxy;


import com.sika.code.cache.redis.pojo.bo.RedisBO;

/**
 * 处理redis逻辑得代理接口类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午9:31:49
 */
public interface RedisProxy {
	/**
	 * 
	 * <p>
	 * 代理类的处理方法
	 * </p>
	 *
	 * @param redisBO
	 * @return 处理的结果
	 * @throws Throwable
	 *
	 * @author daiqi 
	 * @创建时间 2018年4月26日 下午9:31:51
	 */
	Object handle(RedisBO redisBO);
}
