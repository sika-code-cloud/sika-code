package com.dq.easy.cloud.model.common.log.entruster.impl;

import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogAnalysisContainer;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogAnalysisBO;

/**
 * 
 * <p>
 * 	Service层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class DqLogServiceEntruster extends DqLogBaseEntruster{

	@Override
	protected void logDataExtraPre() {
		super.log("这是Service层的日志", "这是Service层的日志");
	}

	@Override
	protected void logDataExtraAfter() {
		super.log("这是Service层的日志", "这是Service层的日志");
	}

	@Override
	protected void initDqLogAnalysisBOData() {
		dqLogAnalysisBO = DqLogAnalysisBO.newInstanceFromContainer(DqLogAnalysisContainer.getLogAnalysisContainerService(), dqLogDTO);
		super.logWrap("这是service的日志分析", "service日志分析数据初始化");
	}

	@Override
	protected void doLogAnalysis() {
		dqLogAnalysisBO.setDqLogAnalysisDTOToContainer(DqLogAnalysisContainer.getLogAnalysisContainerService());
		super.logWrap("这是service的日志分析", dqLogAnalysisBO.getDqLogAnalysisDTO());
	}
	
}
