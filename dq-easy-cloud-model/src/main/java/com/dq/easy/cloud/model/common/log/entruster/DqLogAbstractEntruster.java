package com.dq.easy.cloud.model.common.log.entruster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogMode;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogBaseEntruster;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogBO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
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

	private static final Logger LOG = LoggerFactory.getLogger(DqLogBaseEntruster.class);
	/** 日志传输对象 */
	protected DqLogDTO dqLogDTO;
	/** 日志注解 */
	protected DqLog dqLog;
	/** 日志级别 */
	protected int dqLogLevel;
	/** 目标日志类 */
	protected Logger targetLogger = LOG;

	@Override
	public void handle(DqLogBO dqLogBO) {
		// 1：数据初始化
		init(dqLogBO);
		// 2：执行日志处理
		doHandle();
	}

	/** 日志模板类 */
	protected final void logTemplate() {
		
		LOG.info("\r");
		LOG.info("==============================================   start_logger:请求日志记录:start_logger    ==============================================");
		logDataExtraPre();
		DqLogUtils.logByLogLevel(dqLogLevel, "请求的类名", dqLogDTO.getTargetClassName(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "请求的方法名称", dqLogDTO.getTargetMethodName(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "请求参数类型", dqLogDTO.getTargetParameterTypes(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "请求参数值", dqLogDTO.getTargetParameterValues(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "返回参数类型", dqLogDTO.getTargetReturnType(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "返回参数值", dqLogDTO.getTargetReturnValue(), targetLogger);
		DqLogUtils.logByLogLevel(dqLogLevel, "方法运行时间", (dqLogDTO.getExecutTimeMinllis()) +"ms", targetLogger);
		logDataExtraAfter();
		LOG.info("==============================================   end_logger:请求日志记录:end_logger      =============================================");
		LOG.info("\r\n");
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
		if (DqBaseUtils.isNotNull(dqLogDTO.getLogger())) {
			targetLogger = dqLogDTO.getLogger();
		}
	}

	/** 初始化其他数据 */
	private void initOtherData() {

	}

	/** 执行业务逻辑 */
	private void doHandle() {
		int dqLogMode = dqLog.dqLogMode();
		if (DqLogMode.isConsole(dqLogMode)) {
			doLogConsole();
		} else if (DqLogMode.isFile(dqLogMode)) {
			doLogFile();
		} else if (DqLogMode.isDatabase(dqLogMode)) {
			doLogDataBase();
		} else if (DqLogMode.isMq(dqLogMode)) {
			doLogDataBase();
		}
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
