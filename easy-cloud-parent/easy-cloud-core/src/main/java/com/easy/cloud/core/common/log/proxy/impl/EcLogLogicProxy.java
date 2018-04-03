package com.easy.cloud.core.common.log.proxy.impl;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogAnalysisContainer;
import com.easy.cloud.core.common.log.pojo.bo.EcLogAnalysisBO;

/**
 * 
 * <p>
 * 	Logic层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class EcLogLogicProxy extends EcLogBaseProxy{

	@Override
	protected void logDataExtraPre() {
		super.log("这是Logic层的日志", "这是Logic层的日志");
	}

	@Override
	protected void logDataExtraAfter() {
		super.log("这是Logic层的日志", "这是Logic层的日志");
	}

	@Override
	protected void initDqLogAnalysisBOData() {
		ecLogAnalysisBO = EcLogAnalysisBO.newInstanceFromContainer(EcLogAnalysisContainer.getLogAnalysisContainerLogic(), ecLogDTO);
		super.logWrap("这是Logic的日志分析", "Logic日志分析数据初始化");
	}

	@Override
	protected void doLogAnalysis() {
		ecLogAnalysisBO.setDqLogAnalysisDTOToContainer(EcLogAnalysisContainer.getLogAnalysisContainerLogic());
		super.logWrap("这是Logic的日志分析", ecLogAnalysisBO.getDqLogAnalysisDTO());
	}
	
}
