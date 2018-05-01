package com.easy.cloud.core.common.log.proxy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogModeEnum;
import com.easy.cloud.core.common.log.pojo.bo.EcLogAnalysisBO;
import com.easy.cloud.core.common.log.pojo.bo.EcLogBO;
import com.easy.cloud.core.common.log.pojo.dto.EcLogAnalysisDTO;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.log.proxy.impl.EcLogBaseProxy;
import com.easy.cloud.core.common.log.utils.EcLogAnalysisUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;

/**
 * 
 * <p>
 * 日志委托处理类
 * </p>
 *
 * <pre>
 *  说明：该类是一个抽象类
 *  约定：所有委托类都应该直接或者间接继承此类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月8日 上午10:01:43
 */
public abstract class EcLogAbstractProxy implements EcLogProxy {

	protected final Logger logger = LoggerFactory.getLogger(EcLogBaseProxy.class);
	/** 日志数据传输对象 */
	protected EcLogDTO logDTO;
	/** 日志分析数据传输对象 */
	protected EcLogAnalysisBO logAnalysisBO;
	/** 日志注解 */
	protected EcLogAnnotation log;
	/** 日志级别 */
	protected EcLogLevelEnum logLevel;
	/** 目标日志类 */
	protected Logger targetLogger = logger;
	/** 日志业务逻辑对象 */
	protected EcLogBO logBO;

	@Override
	public void handle(EcLogBO logBO) {
		try {
			// 1：数据初始化
			init(logBO);
			// 获取日志开关
			if (EcLogUtils.getLogSwitch(log, logBO.getBaseAspectDTO())) {
				// 2：执行日志处理
				doLogHandle();
			}
			// 获取日志分析开关
			if (EcLogAnalysisUtils.getLogAnalysisSwitch(log, logBO.getBaseAspectDTO())) {
				if (EcBaseUtils.isNull(logAnalysisBO)) {
					return;
				}
				// 执行方法分析处理
				doLogAnalysis();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 日志模板类 */
	protected final void logTemplate() {

		targetLogger.info("\r");
		targetLogger.info(
				"==============================================   start_logger:请求日志记录:start_logger    ==============================================");
		logDataExtraPre();
		log("请求的类名", logBO.getBaseAspectDTO().getTargetClassName());
		log("请求的方法名称", logBO.getBaseAspectDTO().getTargetMethodName());
		log("请求参数类型", logBO.getBaseAspectDTO().getTargetParameterTypes());
		log("请求参数值", logBO.getBaseAspectDTO().getTargetParameterValues());
		log("返回参数类型", logBO.getBaseAspectDTO().getTargetReturnType().getName());
		log("返回参数值", logBO.getBaseAspectDTO().getTargetReturnValue());
		log("方法运行时间", (logDTO.getRunTimeMinllis()) + "ms");
		logDataExtraAfter();
		targetLogger.info(
				"==============================================   end_logger:请求日志记录:end_logger      =============================================");
		targetLogger.info("\r\n");
	}

	/**
	 * <p>
	 * 额外的日志数据信息
	 * </p>
	 *
	 * <pre>
	 *     在模版日志之前输出
	 *     子类可以通过实现该方法进行输出额外的日志记录
	 * </pre>
	 *
	 * @author daiqi 创建时间 2018年2月9日 下午3:23:35
	 */
	protected void logDataExtraPre() {

	}

	/**
	 * <p>
	 * 额外的日志数据信息
	 * </p>
	 *
	 * <pre>
	 *     在模版日志之后输出
	 *     子类可以通过实现该方法进行输出额外的日志记录
	 * </pre>
	 *
	 * @author daiqi 创建时间 2018年2月9日 下午3:23:21
	 */
	protected void logDataExtraAfter() {

	}

	/**
	 * 
	 * <p>
	 * 记录日志
	 * </p>
	 *
	 * <pre>
	 * 所有子类记录日志通过调用该方法进行日志记录
	 * </pre>
	 *
	 * @param logTitle
	 *            : String : 日志主题
	 * @param logDetailInfo
	 *            : Object : 日志详情信息
	 *
	 *            author daiqi 创建时间 2018年2月21日 下午4:41:46
	 */
	protected final void log(String logTitle, Object logDetailInfo) {
		EcLogUtils.logByLogLevel(logLevel, logTitle, logDetailInfo, targetLogger, false);
	}

	/**
	 * 
	 * <p>
	 * 记录日志带换行格式
	 * </p>
	 *
	 * <pre>
	 * 所有子类记录日志通过调用该方法进行日志记录
	 * </pre>
	 *
	 * @param logTitle
	 *            : String : 日志主题
	 * @param logDetailInfo
	 *            : Object : 日志详情信息
	 *
	 *            author daiqi 创建时间 2018年2月21日 下午4:41:46
	 */
	protected final void logWrap(String logTitle, Object logDetailInfo) {
		EcLogUtils.logByLogLevel(logLevel, logTitle, logDetailInfo, targetLogger, true);
	}

	/** 初始化数据 */
	private void init(EcLogBO ecLogBO) {
		// 1：基础数据初始化
		initBaseData(ecLogBO);
		// 2：其他数据初始化
		initOtherData();
	}

	/** 初始化基础数据 */
	private void initBaseData(EcLogBO logBO) {
		this.logBO = logBO;
		this.logDTO = logBO.getLogDTO();
		this.log = logBO.getLogAnnotation();
		logLevel = log.level();
		this.logDTO.setLogType(log.type());

		if (EcBaseUtils.isNotNull(logDTO.getLogger())) {
			targetLogger = logDTO.getLogger();
		}
	}

	/** 初始化其他数据 */
	private void initOtherData() {
		// 初始化日志分析数据
		if (EcLogAnalysisUtils.getLogAnalysisSwitch(log, logBO.getBaseAspectDTO())) {
			initDqLogAnalysisBOData();
			logAnalysisBO.buildDqLogAnalysisData();
		}
	}

	/** 初始化日志分析业务逻辑对象数据---子类可以重写该方法进行数据初始化 */
	protected void initDqLogAnalysisBOData() {
		// 初始化日志分析数据
		Map<String, EcLogAnalysisDTO> dqLogAnalysisMap = EcLogAnalysisUtils
				.getLogAnalysisContainerByType(logDTO.getLogType());
		logAnalysisBO = EcLogAnalysisBO.newInstanceFromContainer(dqLogAnalysisMap, logBO);
	}

	/** 执行日志处理业务逻辑 */
	private void doLogHandle() {
		EcLogModeEnum logMode = log.model();
		if (EcLogModeEnum.isConsole(logMode)) {
			doLogConsole();
		} else if (EcLogModeEnum.isFile(logMode)) {
			doLogFile();
		} else if (EcLogModeEnum.isDatabase(logMode)) {
			doLogDataBase();
		} else if (EcLogModeEnum.isMq(logMode)) {
			doLogMq();
		}
	}

	/**
	 * 
	 * <p>
	 * 执行日志分析业务逻辑 子类可以重写该方法进行日志分析处理
	 * </p>
	 * <p>
	 * 重写该方法必须同时重写initDqLogAnalysisBOData()方法
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月22日 下午2:47:31
	 */
	protected void doLogAnalysis() {
		logAnalysisBO.setDqLogAnalysisDTOToContainer(EcLogAnalysisUtils.getLogAnalysisContainerByType(log.type()), logBO.getBaseAspectDTO());
		EcLogUtils.info("日志分析数据", logAnalysisBO, logger);
	}

	/** 日志记录方式---控制台 */
	protected abstract void doLogConsole();

	/** 日志记录方式---文件 */
	protected abstract void doLogFile();

	/** 日志记录方式---数据库 */
	protected abstract void doLogDataBase();

	/** 日志记录方式---消息队列 */
	protected abstract void doLogMq();

}
