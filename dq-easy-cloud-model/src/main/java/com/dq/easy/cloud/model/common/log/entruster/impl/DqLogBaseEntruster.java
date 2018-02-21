package com.dq.easy.cloud.model.common.log.entruster.impl;

import com.dq.easy.cloud.model.common.log.entruster.DqLogAbstractEntruster;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;

/**
 * 
 * <p>
 * 日志基础委托类
 * </p>
 *
 * <pre>
 *  说明：所有日志委托类的直接或间接父类
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 上午10:05:02
 */
public class DqLogBaseEntruster extends DqLogAbstractEntruster{

	@Override
	protected void doLogConsole() {
		logTemplate();
	}

	@Override
	protected void doLogFile() {
		logTemplate();
	}

	@Override
	protected void doLogDataBase() {
		
	}

	@Override
	protected void doLogMq() {
		
	}
	
}
