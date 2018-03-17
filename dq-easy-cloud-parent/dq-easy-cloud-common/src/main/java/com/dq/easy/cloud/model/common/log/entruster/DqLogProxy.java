package com.dq.easy.cloud.model.common.log.entruster;

import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogBO;

/**
 * 
 * <p>
 * 日志委托处理接口
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:52:53
 */
public interface DqLogProxy {
	/**
	 * 
	 * <p>
	 * 处理方法、对日志进行处理
	 * </p>
	 *
	 * @param dqLogBO : dqLogBO : 日志数据逻辑对象
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午9:31:17
	 */
	void handle(DqLogBO dqLogBO);
}
