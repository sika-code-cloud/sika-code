package com.easy.cloud.core.common.log.proxy;

import com.easy.cloud.core.common.log.pojo.bo.EcLogBO;

/**
 * 
 * <p>
 * 日志委托处理接口
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:52:53
 */
public interface EcLogProxy {
	/**
	 * 
	 * <p>
	 * 处理方法、对日志进行处理
	 * </p>
	 *
	 * @param ecLogBO : dqLogBO : 日志数据逻辑对象
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午9:31:17
	 */
	void handle(EcLogBO ecLogBO);
}
