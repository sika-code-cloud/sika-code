package com.easy.cloud.core.common.log.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.basic.constant.EcBaseConstant;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.pojo.dto.EcLogAnalysisDTO;

/**
 * 
 * <p>
 * 日志常量类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月7日 下午5:21:53
 */
public class EcLogConstant extends EcBaseConstant {
	public static final Logger LOGGER = LoggerFactory.getLogger(EcLogConstant.class);
	
	/**
	 * <p>
	 * 日志记录方式静态常量类，包括控制台、文件、数据库、消息队列...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static enum EcLogModeEnum {
		/** 日志记录方式---控制台---1 */
		CONSOLE(1, "控制台方式"),
		/** 日志记录方式---文件---2 */
		FILE(2, "控制台方式"),
		/** 日志记录方式---数据库---3 */
		DATABASE(3, "控制台方式"),
		/** 日志记录方式---消息队列---4 */
		MQ(4, "控制台方式"),;
		private int type;
		private String desc;

		private EcLogModeEnum(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public int getType() {
			return type;
		}

		public String getDesc() {
			return desc;
		}

		/** 日志记录方式是控制台 */
		public static boolean isConsole(EcLogModeEnum logLevel) {
			return EcBaseUtils.equals(logLevel, CONSOLE);
		}

		/** 日志记录方式是控制台 */
		public static boolean isFile(EcLogModeEnum logLevel) {
			return EcBaseUtils.equals(logLevel, FILE);
		}

		/** 日志记录方式是数据库 */
		public static boolean isDatabase(EcLogModeEnum logLevel) {
			return EcBaseUtils.equals(logLevel, DATABASE);
		}

		/** 日志记录方式是消息队列 */
		public static boolean isMq(EcLogModeEnum logLevel) {
			return EcBaseUtils.equals(logLevel, MQ);
		}
	}

	/**
	 * <p>
	 * 日志记录级别静态常量类，包括debug、info、warn、error...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static enum EcLogLevelEnum {
		/** 日志记录级别---debug---1 */
		DEBUG(1, "控制台方式"),
		/** 日志记录级别---info---2 */
		INFO(2, "控制台方式"),
		/** 日志记录级别---warn---3 */
		WARN(3, "控制台方式"),
		/** 日志记录级别---error---4 */
		ERROR(4, "控制台方式"),;
		private int type;
		private String desc;

		private EcLogLevelEnum(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public int getType() {
			return type;
		}

		public String getDesc() {
			return desc;
		}

		/** 日志记录级别是debug */
		public static boolean isDebug(EcLogLevelEnum logLevel) {
			return EcBaseUtils.equals(logLevel, DEBUG);
		}

		/** 日志记录级别是debug */
		public static boolean isInfo(EcLogLevelEnum logLevel) {
			return EcBaseUtils.equals(logLevel, INFO);
		}

		/** 日志记录级别是info */
		public static boolean isWarn(EcLogLevelEnum logLevel) {
			return EcBaseUtils.equals(logLevel, WARN);
		}

		/** 日志记录级别是error */
		public static boolean isError(EcLogLevelEnum logLevel) {
			return EcBaseUtils.equals(logLevel, ERROR);
		}
	}

	/**
	 * <p>
	 * 日志记录类型静态常量类，包括controller、service、repository...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static enum EcLogTypeEnum {

		/** 日志记录类型---其他---0 */
		OTHER(0, "其他"),
		/** 日志记录类型---controller---1 */
		CONTROLLER(1, "controller"),
		/** 日志记录类型---logic---2 */
		LOGIC(2, "logic"),
		/** 日志记录类型---service---3 */
		SERVICE(3, "service"),
		/** 日志记录类型---repository---4 */
		REPOSITORY(4, "repository");

		private int type;
		private String desc;

		private EcLogTypeEnum(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public int getType() {
			return type;
		}

		public String getDesc() {
			return desc;
		}

		/** 记录日志是其他 */
		public static boolean isOther(EcLogTypeEnum logType) {
			return EcBaseUtils.equals(logType, OTHER);
		}

		/** 记录日志类型是Controller */
		public static boolean isController(EcLogTypeEnum logType) {
			return EcBaseUtils.equals(logType, CONTROLLER);
		}

		/** 记录日志类型是Logic */
		public static boolean isLogic(EcLogTypeEnum logType) {
			return EcBaseUtils.equals(logType, LOGIC);
		}

		/** 记录日志类型是Service */
		public static boolean isService(EcLogTypeEnum logType) {
			return EcBaseUtils.equals(logType, SERVICE);
		}

		/** 记录日志类型是Repository */
		public static boolean isRepository(EcLogTypeEnum logType) {
			return EcBaseUtils.equals(logType, REPOSITORY);
		}

	}

	/** 日志分析容器常量类 */
	public static class EcLogAnalysisContainer {
		private static final Map<String, EcLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_REPOSITORY = new ConcurrentHashMap<>();
		private static final Map<String, EcLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_SERVICE = new ConcurrentHashMap<>();
		private static final Map<String, EcLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_LOGIC = new ConcurrentHashMap<>();
		private static final Map<String, EcLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_CONTROLLER = new ConcurrentHashMap<>();
		private static final Map<String, EcLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_OTHER = new ConcurrentHashMap<>();

		/** 获取日志分析容器---repository类型 */
		public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerRepository() {
			return DQ_LOG_ANALYSIS_CONTAINER_REPOSITORY;
		}

		/** 获取日志分析容器---service类型 */
		public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerService() {
			return DQ_LOG_ANALYSIS_CONTAINER_SERVICE;
		}

		/** 获取日志分析容器---logic类型 */
		public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerLogic() {
			return DQ_LOG_ANALYSIS_CONTAINER_LOGIC;
		}

		/** 获取日志分析容器---controller类型 */
		public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerController() {
			return DQ_LOG_ANALYSIS_CONTAINER_CONTROLLER;
		}

		/** 获取日志分析容器---other类型 */
		public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerOther() {
			return DQ_LOG_ANALYSIS_CONTAINER_OTHER;
		}
	}
}
