package com.dq.easy.cloud.model.common.log.entruster;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogMode;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogBaseEntruster;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogAnalysisBO;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogBO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogAnalysisDTO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.log.utils.DqLogAnalysisUtils;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;

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
public abstract class DqLogAbstractEntruster implements DqLogEntruster {

	protected final Logger LOG = LoggerFactory.getLogger(DqLogBaseEntruster.class);
	/** 日志数据传输对象 */
	protected DqLogDTO dqLogDTO;
	/** 日志分析数据传输对象 */
	protected DqLogAnalysisBO dqLogAnalysisBO;
	/** 日志注解 */
	protected DqLog dqLog;
	/** 日志级别 */
	protected int dqLogLevel;
	/** 目标日志类 */
	protected Logger targetLogger = LOG;

	@Override
	public void handle(DqLogBO dqLogBO) {
		try {
			// 1：数据初始化
			init(dqLogBO);
//			获取日志开关
			if (DqLogUtils.getLogSwitch(dqLog, dqLogDTO)){
//				2：执行日志处理
				doLogHandle();
			}
//			获取日志分析开关
			if (DqLogAnalysisUtils.getLogAnalysisSwitch(dqLog, dqLogDTO)){
				if (DqBaseUtils.isNull(dqLogAnalysisBO)) {
					return ;
				}
//				执行方法分析处理
				doLogAnalysis();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	/** 日志模板类 */
	protected final void logTemplate() {
		
		targetLogger.info("\r");
		targetLogger.info("==============================================   start_logger:请求日志记录:start_logger    ==============================================");
		logDataExtraPre();
		log("请求的类名", dqLogDTO.getTargetClassName());
		log("请求的方法名称", dqLogDTO.getTargetMethodName());
		log("请求参数类型", dqLogDTO.getTargetParameterTypes());
		log("请求参数值", dqLogDTO.getTargetParameterValues());
		log("返回参数类型", dqLogDTO.getTargetReturnType());
		log("返回参数值", dqLogDTO.getTargetReturnValue());
		log("方法运行时间", (dqLogDTO.getRunTimeMinllis()) +"ms");
		logDataExtraAfter();
		targetLogger.info("==============================================   end_logger:请求日志记录:end_logger      =============================================");
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
	 * <p>记录日志</p>
	 *
	 * <pre>所有子类记录日志通过调用该方法进行日志记录</pre>
	 *
	 * @param logTitle : String : 日志主题
	 * @param logDetailInfo : Object : 日志详情信息
	 *
	 * author daiqi
	 * 创建时间  2018年2月21日 下午4:41:46
	 */
	protected final void log(String logTitle, Object logDetailInfo) {
		DqLogUtils.logByLogLevel(dqLogLevel, logTitle,logDetailInfo, targetLogger, false);
	}
	
	/**
	 * 
	 * <p>记录日志带换行格式</p>
	 *
	 * <pre>所有子类记录日志通过调用该方法进行日志记录</pre>
	 *
	 * @param logTitle : String : 日志主题
	 * @param logDetailInfo : Object : 日志详情信息
	 *
	 * author daiqi
	 * 创建时间  2018年2月21日 下午4:41:46
	 */
	protected final void logWrap(String logTitle, Object logDetailInfo) {
		DqLogUtils.logByLogLevel(dqLogLevel, logTitle,logDetailInfo, targetLogger, true);
	}
	/** 初始化数据 */
	private void init(DqLogBO dqLogBO) {
		// 1：基础数据初始化
		initBaseData(dqLogBO);
		// 2：其他数据初始化
		initOtherData();
	}

	/** 初始化基础数据 */
	private void initBaseData(DqLogBO dqLogBO) {
		this.dqLogDTO = dqLogBO.getDqLogDTO();
		this.dqLog = dqLogBO.getDqLog();
		dqLogLevel = dqLog.dqLogLevel();
		this.dqLogDTO.setLogType(dqLog.dqLogType());
		
		if (DqBaseUtils.isNotNull(dqLogDTO.getLogger())) {
			targetLogger = dqLogDTO.getLogger();
		}
	}

	/** 初始化其他数据 */
	private void initOtherData() {
//		初始化日志分析数据
		if (DqLogAnalysisUtils.getLogAnalysisSwitch(dqLog, dqLogDTO)){
			initDqLogAnalysisBOData();
			dqLogAnalysisBO.buildDqLogAnalysisData();
		}
	}
	
	/** 初始化日志分析业务逻辑对象数据---子类可以重写该方法进行数据初始化 */
	protected void initDqLogAnalysisBOData() {
//		初始化日志分析数据
		Map<String, DqLogAnalysisDTO> dqLogAnalysisMap = DqLogAnalysisUtils.getLogAnalysisContainerByType(dqLogDTO.getLogType());
		dqLogAnalysisBO = DqLogAnalysisBO.newInstanceFromContainer(dqLogAnalysisMap, dqLogDTO);
	}
	
	/** 执行日志处理业务逻辑 */
	private void doLogHandle() {
		int dqLogMode = dqLog.dqLogMode();
		if (DqLogMode.isConsole(dqLogMode)) {
			doLogConsole();
		} else if (DqLogMode.isFile(dqLogMode)) {
			doLogFile();
		} else if (DqLogMode.isDatabase(dqLogMode)) {
			doLogDataBase();
		} else if (DqLogMode.isMq(dqLogMode)) {
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
	 * @author daiqi
	 * 创建时间    2018年2月22日 下午2:47:31
	 */
	protected void doLogAnalysis(){
		dqLogAnalysisBO.setDqLogAnalysisDTOToContainer(DqLogAnalysisUtils.getLogAnalysisContainerByType(dqLog.dqLogType()));
		DqLogUtils.info("日志分析数据", dqLogAnalysisBO, LOG);
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
