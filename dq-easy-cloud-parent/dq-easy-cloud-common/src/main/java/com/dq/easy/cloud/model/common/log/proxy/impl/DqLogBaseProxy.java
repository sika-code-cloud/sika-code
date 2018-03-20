package com.dq.easy.cloud.model.common.log.proxy.impl;

import com.dq.easy.cloud.model.common.log.proxy.DqLogAbstractProxy;

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
public class DqLogBaseProxy extends DqLogAbstractProxy{

	@Override
	protected void doLogConsole() {
		super.logTemplate();
	}

	@Override
	protected void doLogFile() {
		super.logTemplate();
	}

	@Override
	protected void doLogDataBase() {
		
	}

	@Override
	protected void doLogMq() {
		
	}

}
