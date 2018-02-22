package com.dq.easy.cloud.model.common.log.entruster.impl;

import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogAnalysisContainer;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogAnalysisBO;

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

	@Override
	protected void initDqLogAnalysisBOData() {
		dqLogAnalysisBO = DqLogAnalysisBO.newInstanceFromContainer(DqLogAnalysisContainer.getLogAnalysisContainerRepository(), dqLogDTO);
		super.log("这是repository的日志分析", "repository日志分析数据初始化");
	}

	@Override
	protected void doLogAnalysis() {
		dqLogAnalysisBO.setDqLogAnalysisDTOToContainer(DqLogAnalysisContainer.getLogAnalysisContainerRepository());
		super.log("这是repository的日志分析", dqLogAnalysisBO.getDqLogAnalysisDTO());
	}
	
	
}
