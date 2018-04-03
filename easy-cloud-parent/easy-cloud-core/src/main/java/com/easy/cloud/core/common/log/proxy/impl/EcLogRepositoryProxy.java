package com.easy.cloud.core.common.log.proxy.impl;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogAnalysisContainer;
import com.easy.cloud.core.common.log.pojo.bo.EcLogAnalysisBO;

/**
 * 
 * <p>
 * 	Repository层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class EcLogRepositoryProxy extends EcLogBaseProxy{

	@Override
	protected void logDataExtraPre() {
		super.log("这是Repository层的日志", "这是Repository层的日志");
	}

	@Override
	protected void logDataExtraAfter() {
		super.logWrap("这是Repository层的日志", "这是Repository层的日志");
	}

	@Override
	protected void initDqLogAnalysisBOData() {
		ecLogAnalysisBO = EcLogAnalysisBO.newInstanceFromContainer(EcLogAnalysisContainer.getLogAnalysisContainerRepository(), ecLogDTO);
		super.logWrap("这是repository的日志分析", "repository日志分析数据初始化");
	}

	@Override
	protected void doLogAnalysis() {
		ecLogAnalysisBO.setDqLogAnalysisDTOToContainer(EcLogAnalysisContainer.getLogAnalysisContainerRepository());
		super.log("这是repository的日志分析", ecLogAnalysisBO.getDqLogAnalysisDTO());
	}
	
	
}
