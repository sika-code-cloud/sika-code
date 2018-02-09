package com.dq.easy.cloud.model.common.log.entruster.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
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

	
	@Override
	protected void logDataExtraPre() {
		DqLogUtils.logByLogLevel(dqLog.dqLogLevel(), "这是额外的日志之前啦", "啦啦啦啦日志之前啦", targetLogger);
	}

	@Override
	protected void logDataExtraAfter() {
		DqLogUtils.logByLogLevel(dqLog.dqLogLevel(), "这是额外的日志之后啦", "啦啦啦啦日志之后啦", targetLogger);
	}
	
}
