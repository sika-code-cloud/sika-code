package com.dq.easy.cloud.model.common.log.entruster;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 
 * <p>
 * 日志委托处理接口
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:52:53
 */
public interface DqLogEntruster {
	/**
	 * 
	 * <p>
	 * 处理方法、对日志进行处理
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年2月8日 上午9:54:33
	 */
	void handle(ProceedingJoinPoint proceedingJoinPoint);
}
