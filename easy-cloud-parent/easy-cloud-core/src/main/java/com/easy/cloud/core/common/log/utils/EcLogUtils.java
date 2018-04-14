package com.easy.cloud.core.common.log.utils;

import org.slf4j.Logger;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLog;
import com.easy.cloud.core.common.log.config.EcLogConfig;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.log.proxy.EcLogProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogBaseProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogControllerProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogLogicProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogRepositoryProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogServiceProxy;
import com.easy.cloud.core.common.reflection.utils.EcReflectionUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * @ClassName : DqLogUtils
 * @Description : 日志工具类
 * @author daiqi
 * @date 2017年12月21日 上午11:49:24
 *
 */
public class EcLogUtils {

	/**
	 * <p>
	 * 获取日志的key
	 * </p>
	 *
	 * @param className
	 * @param methodName
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午5:58:48
	 */
	public static String getLogKey(String... keys) {
		if (EcArrayUtils.isEmpty(keys)) {
			return null;
		}
		StringBuilder keyBuilder = EcStringUtils.newStringBuilderDefault();

		for (String key : keys) {
			if (EcStringUtils.isNotEmpty(key)) {
				keyBuilder.append(EcStringUtils.SPLIT_COLON).append(key);
			}
		}
		String keyStr = keyBuilder.toString();
		return EcStringUtils.substring(keyStr, EcStringUtils.indexOf(keyStr, EcStringUtils.SPLIT_COLON) + 1);
	}
	
	/**
	 * <p>
	 * 获取日志开关
	 * </p>
	 *
	 * @param ecLog
	 *            : DqLog : 日志注解
	 * @param ecLogDTO
	 *            : DqLogDTO : 日志传输对象
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午6:05:15
	 */
	public static boolean getLogSwitch(EcLog ecLog, EcLogDTO ecLogDTO) {
		boolean dqLogSwitch = ecLog.logSwitch();
		String className = ecLogDTO.getTargetClassName();
		String methodName = ecLogDTO.getTargetMethodName();

		// 类名为空直接返回true
		if (EcStringUtils.isEmpty(className)) {
			return true;
		}
		// 根据类名和方法名获取开关的key
		String switchKey = EcLogUtils.getLogKey(className, methodName);
		if (EcStringUtils.isEmpty(switchKey)) {
			return true;
		}
		Boolean switchFlag = EcLogConfig.getLogSwitchConfig().get(switchKey);
		// 若config中标志不为空直接返回
		if (EcBaseUtils.isNotNull(switchFlag)) {
			return switchFlag;
		}
		// 根据类名获取开关key
		switchKey = EcLogUtils.getLogKey(className);
		// 获取对类的日志开关
		switchFlag = EcLogConfig.getLogSwitchConfig().get(switchKey);
		return switchFlag == null ? dqLogSwitch : switchFlag;
	}

	/**
	 * <p>
	 * 根据日志类型和委托类class获取日志处理委托类，设定委托处理类优先级最高
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqLogEntrusterClass : DqLogControllerEntruster.class : 日志委托处理类 : 否
	 *     dqLogType : int : 日志类型 : 否
	 * </pre>
	 *
	 * @param ecLog
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午4:02:34
	 */
	public static EcLogProxy getDqLogProxy(EcLog ecLog) {
		if (EcBaseUtils.isNull(ecLog)) {
			return null;
		}
		if (EcBaseUtils.isNotNull(ecLog.logProxyClass())
				&& EcBaseUtils.notEquals(ecLog.logProxyClass(), EcLogBaseProxy.class)) {
			return (EcLogProxy) EcReflectionUtils.newInstance(ecLog.logProxyClass());
		}
		if (EcLogTypeEnum.isController(ecLog.logType())) {
			return EcReflectionUtils.newInstance(EcLogControllerProxy.class);
		}
		if (EcLogTypeEnum.isLogic(ecLog.logType())) {
			return EcReflectionUtils.newInstance(EcLogLogicProxy.class);
		}
		if (EcLogTypeEnum.isService(ecLog.logType())) {
			return EcReflectionUtils.newInstance(EcLogServiceProxy.class);
		}
		if (EcLogTypeEnum.isRepository(ecLog.logType())) {
			return EcReflectionUtils.newInstance(EcLogRepositoryProxy.class);
		}
		return EcReflectionUtils.newInstance(EcLogBaseProxy.class);
	}

	/**
	 * <p>
	 * 根据日志级别记录日志
	 * </p>
	 *
	 * @param logLevel
	 *            : Integer : 日志级别
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月9日 上午11:42:31
	 */
	public static void logByLogLevel(EcLogLevelEnum logLevel, String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		if (EcLogLevelEnum.isDebug(logLevel)) {
			debug(logTitle, logDetail, logger, isNeedWrap);
		} else if (EcLogLevelEnum.isInfo(logLevel)) {
			info(logTitle, logDetail, logger, isNeedWrap);
		} else if (EcLogLevelEnum.isWarn(logLevel)) {
			warn(logTitle, logDetail, logger, isNeedWrap);
		} else if (EcLogLevelEnum.isError(logLevel)) {
			error(logTitle, logDetail, logger, isNeedWrap);
		}
	}
	/**
	 * <p>
	 * debug级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void debug(String logTitle, Object logDetail, Logger logger) {
		debug(logTitle, logDetail, logger, true);
	}

	/**
	 * <p>
	 * debug级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void debug(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		if (!logger.isDebugEnabled()) {
			return;
		}
		if (isNeedWrap) {
			logger.debug("\r");
		}
		logger.debug("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.debug(detail == null ? "" : detail.toString());
		logger.debug("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.debug("\r\n");
		}
	}

	/**
	 * <p>
	 * info级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void info(String logTitle, Object logDetail, Logger logger) {
		if (!logger.isInfoEnabled()) {
			return ;
		}
		logger.info("\r");
		logger.info("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.info(detail == null ? "" : detail.toString());
		logger.info("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		logger.info("\r\n");
	}

	/**
	 * <p>
	 * info级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void info(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		if (!logger.isInfoEnabled()) {
			return ;
		}
		if (isNeedWrap) {
			logger.info("\r");
		}
		logger.info("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.info(detail == null ? "" : detail.toString());
		logger.info("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.info("\r\n");
		}
	}

	/**
	 * <p>
	 * warn级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void warn(String logTitle, Object logDetail, Logger logger) {
		if (!logger.isWarnEnabled()) {
			return ;
		}
		logger.warn("\r");
		logger.warn("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.warn(detail == null ? "" : detail.toString());
		logger.warn("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		logger.warn("\r\n");
	}

	/**
	 * <p>
	 * info级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void warn(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		if (!logger.isWarnEnabled()) {
			return ;
		}
		if (isNeedWrap) {
			logger.warn("\r");
		}
		logger.warn("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.warn(detail == null ? "" : detail.toString());
		logger.warn("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.warn("\r\n");
		}
	}

	/**
	 * <p>
	 * error级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void error(String logTitle, Object logDetail, Logger logger) {
		error(logTitle, logDetail, logger, true);
	}

	/**
	 * <p>
	 * error级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param logDetail
	 *            : Object : 打印的日志详情信息
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void error(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		if (!logger.isErrorEnabled()) {
			return ;
		}
		if (isNeedWrap) {
			logger.error("\r");
		}
		logger.error("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		Object detail = EcJSONUtils.parseObject(logDetail, String.class);
		logger.error(detail == null ? "" : detail.toString());
		
		logger.error("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.error("\r\n");
		}
	}
	
	/**
	 * <p>
	 * error级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param Throwable
	 *            : throwable : 异常对象
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void error(String logTitle, Throwable throwable, Logger logger) {
		error(logTitle, throwable, logger, true);
	}
	/**
	 * <p>
	 * error级别的统一格式日志
	 * </p>
	 * 
	 * @param logTitle
	 *            : String : 打印的日志主题
	 * @param Throwable
	 *            : throwable : 异常对象
	 * @param logger
	 *            : Logger : 目标类的日志记录对象
	 * @param isNeedWrap
	 *            : boolean : 是否需要换行 true需要 false不需要
	 * @author daiqi 创建时间 2018年2月3日 下午5:21:06
	 */
	public static void error(String logTitle, Throwable throwable, Logger logger,  boolean isNeedWrap) {
		if (!logger.isErrorEnabled()) {
			return ;
		}
		if (isNeedWrap) {
			logger.error("\r");
		}
		logger.error("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.error(throwable.getMessage(), throwable);
		
		logger.error("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.error("\r\n");
		}
	}
}
