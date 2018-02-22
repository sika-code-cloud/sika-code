package com.dq.easy.cloud.model.common.log.utils;

import org.slf4j.Logger;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.array.DqArrayUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.config.DqLogConfig;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogLevel;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogType;
import com.dq.easy.cloud.model.common.log.entruster.DqLogEntruster;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogBaseEntruster;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogControllerEntruster;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogRepositoryEntruster;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogServiceEntruster;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.reflection.utils.DqReflectionUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * @ClassName : DqLogUtils
 * @Description : 日志工具类
 * @author daiqi
 * @date 2017年12月21日 上午11:49:24
 *
 */
public class DqLogUtils {

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
		if (DqArrayUtils.isEmpty(keys)) {
			return null;
		}
		StringBuilder keyBuilder = DqStringUtils.newStringBuilderDefault();

		for (String key : keys) {
			if (DqStringUtils.isNotEmpty(key)) {
				keyBuilder.append(DqStringUtils.SPLIT_COLON).append(key);
			}

		}
		String keyStr = keyBuilder.toString();
		return DqStringUtils.substring(keyStr, DqStringUtils.indexOf(keyStr, DqStringUtils.SPLIT_COLON) + 1);
	}
	
	/**
	 * <p>
	 * 获取日志开关
	 * </p>
	 *
	 * @param dqLog
	 *            : DqLog : 日志注解
	 * @param dqLogDTO
	 *            : DqLogDTO : 日志传输对象
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午6:05:15
	 */
	public static boolean getLogSwitch(DqLog dqLog, DqLogDTO dqLogDTO) {
		boolean dqLogSwitch = dqLog.dqLogSwitch();
		String className = dqLogDTO.getTargetClassName();
		String methodName = dqLogDTO.getTargetMethodName();

		// 类名为空直接返回true
		if (DqStringUtils.isEmpty(className)) {
			return true;
		}
		// 根据类名和方法名获取开关的key
		String switchKey = DqLogUtils.getLogKey(className, methodName);
		if (DqStringUtils.isEmpty(switchKey)) {
			return true;
		}
		Boolean switchFlag = DqLogConfig.getLogSwitchConfig().get(switchKey);
		// 若config中标志不为空直接返回
		if (DqBaseUtils.isNotNull(switchFlag)) {
			return switchFlag;
		}
		// 根据类名获取开关key
		switchKey = DqLogUtils.getLogKey(className);
		// 获取对类的日志开关
		switchFlag = DqLogConfig.getLogSwitchConfig().get(switchKey);
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
	 * @param dqLog
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午4:02:34
	 */
	public static DqLogEntruster getDqLogEntruster(DqLog dqLog) {
		if (DqBaseUtils.isNull(dqLog)) {
			return null;
		}
		if (DqBaseUtils.isNotNull(dqLog.dqLogEntrusterClass())
				&& DqBaseUtils.notEquals(dqLog.dqLogEntrusterClass(), DqLogBaseEntruster.class)) {
			return (DqLogEntruster) DqReflectionUtils.newInstance(dqLog.dqLogEntrusterClass());
		}
		if (DqLogType.isController(dqLog.dqLogType())) {
			return DqReflectionUtils.newInstance(DqLogControllerEntruster.class);
		}
		if (DqLogType.isService(dqLog.dqLogType())) {
			return DqReflectionUtils.newInstance(DqLogServiceEntruster.class);
		}
		if (DqLogType.isRepository(dqLog.dqLogType())) {
			return DqReflectionUtils.newInstance(DqLogRepositoryEntruster.class);
		}
		return DqReflectionUtils.newInstance(DqLogBaseEntruster.class);
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
	 * @author daiqi 创建时间 2018年2月9日 上午11:42:31
	 */
	public static void logByLogLevel(Integer logLevel, String logTitle, Object logDetail, Logger logger) {
		if (DqLogLevel.isDebug(logLevel)) {
			debug(logTitle, logDetail, logger, false);
		} else if (DqLogLevel.isInfo(logLevel)) {
			info(logTitle, logDetail, logger, false);
		} else if (DqLogLevel.isWarn(logLevel)) {
			warn(logTitle, logDetail, logger, false);
		} else if (DqLogLevel.isError(logLevel)) {
			error(logTitle, logDetail, logger, false);
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
		logger = filterLogger(logger);
		if (isNeedWrap) {
			logger.debug("\r");
		}
		logger.debug("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.debug(DqJSONUtils.parseObject(logDetail, String.class));
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
		logger = filterLogger(logger);
		logger.info("\r");
		logger.info("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.info(DqJSONUtils.parseObject(logDetail, String.class));
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
		logger = filterLogger(logger);
		if (isNeedWrap) {
			logger.info("\r");
		}
		logger.info("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.info(DqJSONUtils.parseObject(logDetail, String.class));
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
		logger = filterLogger(logger);
		logger.warn("\r");
		logger.warn("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.warn(DqJSONUtils.parseObject(logDetail, String.class));
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
		logger = filterLogger(logger);
		if (isNeedWrap) {
			logger.warn("\r");
		}
		logger.warn("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.warn(DqJSONUtils.parseObject(logDetail, String.class));
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
		logger = filterLogger(logger);
		logger.error("\r");
		logger.error("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.error(DqJSONUtils.parseObject(logDetail, String.class));
		logger.error("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		logger.error("\r\n");
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
	public static void error(String logTitle, Object logDetail, Logger logger, boolean isNeedWrap) {
		logger = filterLogger(logger);
		if (isNeedWrap) {
			logger.error("\r");
		}
		logger.error("**********************************************   start_logger:" + logTitle
				+ ":start_logger   **********************************************");
		logger.error(DqJSONUtils.parseObject(logDetail, String.class));
		logger.error("**********************************************   end_logger:" + logTitle
				+ ":end_logger   **********************************************");
		if (isNeedWrap) {
			logger.error("\r\n");
		}
	}

	private static Logger filterLogger(Logger logger) {
		if (DqBaseUtils.isNull(logger)) {
			logger = null;
		}
		return logger;
	}

}
