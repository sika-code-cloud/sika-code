package com.dq.easy.cloud.model.common.log.entruster.impl;

import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;

/**
 * 
 * <p>
 * 	Controller层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class DqLogControllerEntruster extends DqLogBaseEntruster{

	@Override
	protected void logDataExtraPre() {
		DqLogUtils.logByLogLevel(dqLog.dqLogLevel(), "这是控制层的日志哦", "啦啦啦啦这是控制层的日志哦之前啦", targetLogger);
	}

	@Override
	protected void logDataExtraAfter() {
		DqLogUtils.logByLogLevel(dqLog.dqLogLevel(), "请求路径", dqLogDTO.getRequestPath(), targetLogger);
	}


}
