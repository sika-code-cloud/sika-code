package com.dq.easy.cloud.model.common.log.entruster.impl;

/**
 * 
 * <p>
 * 	Repository层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class DqLogRepositoryEntruster extends DqLogBaseEntruster{

	@Override
	protected void logDataExtraPre() {
		super.log("这是Repository层的日志", "这是Repository层的日志");
	}

	@Override
	protected void logDataExtraAfter() {
		super.log("这是Repository层的日志", "这是Repository层的日志");
	}

}
