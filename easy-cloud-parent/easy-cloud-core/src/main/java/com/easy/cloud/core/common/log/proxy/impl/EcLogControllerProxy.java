package com.easy.cloud.core.common.log.proxy.impl;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogAnalysisContainer;
import com.easy.cloud.core.common.log.pojo.bo.EcLogAnalysisBO;

/**
 * 
 * <p>
 * 	Controller层日志处理委托者
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:59:01
 */
public class EcLogControllerProxy extends EcLogBaseProxy{

	@Override
	protected void logDataExtraPre() {
		super.log("这是控制层的日志哦", "啦啦啦啦这是控制层的日志哦之前啦");
	}

	@Override
	protected void logDataExtraAfter() {
		super.log("请求路径", logDTO.getRequestPath());
		super.log("这是控制层的日志哦", "啦啦啦啦这是控制层的日志哦之后啦");
	}

	@Override
	protected void initDqLogAnalysisBOData() {
		logAnalysisBO = EcLogAnalysisBO.newInstanceFromContainer(EcLogAnalysisContainer.getLogAnalysisContainerController(), logDTO);
		super.logWrap("这是controller的日志分析", "controller日志分析数据初始化");
	}

	@Override
	protected void doLogAnalysis() {
		logAnalysisBO.setDqLogAnalysisDTOToContainer(EcLogAnalysisContainer.getLogAnalysisContainerController());
		super.logWrap("这是controller的日志分析", logAnalysisBO.getDqLogAnalysisDTO());
	}
	
	
}
