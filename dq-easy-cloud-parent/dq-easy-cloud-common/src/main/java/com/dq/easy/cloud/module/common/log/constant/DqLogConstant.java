package com.dq.easy.cloud.module.common.log.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.log.pojo.dto.DqLogAnalysisDTO;

/**
 * 
 * <p>
 * 日志常量类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月7日 下午5:21:53
 */
public class DqLogConstant extends DqBaseConstant {
	public static final Logger LOGGER = LoggerFactory.getLogger(DqLogConstant.class);

	/**
	 * <p>
	 * 日志记录方式静态常量类，包括控制台、文件、数据库、消息队列...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static class DqLogMode extends DqLogConstant {
		/** 日志记录方式---控制台---1 */
		public static final int CONSOLE = 1;
		/** 日志记录方式---文件---2 */
		public static final int FILE = 2;
		/** 日志记录方式---数据库---3 */
		public static final int DATABASE = 3;
		/** 日志记录方式---消息队列---4 */
		public static final int MQ = 4;

		/** 日志记录方式是控制台 */
		public static boolean isConsole(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, CONSOLE);
		}

		/** 日志记录方式是控制台 */
		public static boolean isFile(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, FILE);
		}

		/** 日志记录方式是数据库 */
		public static boolean isDatabase(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, DATABASE);
		}

		/** 日志记录方式是消息队列 */
		public static boolean isMq(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, MQ);
		}
	}

	/**
	 * <p>
	 * 日志记录级别静态常量类，包括debug、info、warn、error...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static class DqLogLevel extends DqLogConstant {
		/** 日志记录级别---debug---1 */
		public static final int DEBUG = 1;
		/** 日志记录级别---info---2 */
		public static final int INFO = 2;
		/** 日志记录级别---warn---3 */
		public static final int WARN = 3;
		/** 日志记录级别---error---4 */
		public static final int ERROR = 4;

		/** 日志记录级别是debug */
		public static boolean isDebug(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, DEBUG);
		}

		/** 日志记录级别是debug */
		public static boolean isInfo(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, INFO);
		}

		/** 日志记录级别是info */
		public static boolean isWarn(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, WARN);
		}

		/** 日志记录级别是error */
		public static boolean isError(Integer dqLogLevel) {
			return DqBaseUtils.equals(dqLogLevel, ERROR);
		}
	}

	/**
	 * <p>
	 * 日志记录类型静态常量类，包括controller、service、repository...
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月7日 下午5:08:19
	 */
	public static class DqLogType extends DqLogConstant {
		/** 日志记录类型---其他---0 */
		public static final int OTHER = 0;
		/** 日志记录类型---controller---1 */
		public static final int CONTROLLER = 1;
		/** 日志记录类型---logic---2 */
		public static final int LOGIC = 2;
		/** 日志记录类型---service---3 */
		public static final int SERVICE = 3;
		/** 日志记录类型---repository---4 */
		public static final int REPOSITORY = 4;
		/** 记录日志是其他 */
		public static boolean isOther(Integer dqLogType) {
			return DqBaseUtils.equals(dqLogType, OTHER);
		}

		/** 记录日志类型是Controller */
		public static boolean isController(Integer dqLogType) {
			return DqBaseUtils.equals(dqLogType, CONTROLLER);
		}

		/** 记录日志类型是Logic */
		public static boolean isLogic(Integer dqLogType) {
			return DqBaseUtils.equals(dqLogType, LOGIC);
		}
		
		/** 记录日志类型是Service */
		public static boolean isService(Integer dqLogType) {
			return DqBaseUtils.equals(dqLogType, SERVICE);
		}

		/** 记录日志类型是Repository */
		public static boolean isRepository(Integer dqLogType) {
			return DqBaseUtils.equals(dqLogType, REPOSITORY);
		}
		
	}

	/** 日志分析容器常量类 */
	public static class DqLogAnalysisContainer {
		private static final Map<String, DqLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_REPOSITORY = new ConcurrentHashMap<>();
		private static final Map<String, DqLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_SERVICE = new ConcurrentHashMap<>();
		private static final Map<String, DqLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_LOGIC = new ConcurrentHashMap<>();
		private static final Map<String, DqLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_CONTROLLER = new ConcurrentHashMap<>();
		private static final Map<String, DqLogAnalysisDTO> DQ_LOG_ANALYSIS_CONTAINER_OTHER = new ConcurrentHashMap<>();

		/** 获取日志分析容器---repository类型 */
		public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerRepository() {
			return DQ_LOG_ANALYSIS_CONTAINER_REPOSITORY;
		}

		/** 获取日志分析容器---service类型 */
		public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerService() {
			return DQ_LOG_ANALYSIS_CONTAINER_SERVICE;
		}

		/** 获取日志分析容器---logic类型 */
		public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerLogic() {
			return DQ_LOG_ANALYSIS_CONTAINER_LOGIC;
		}
		
		/** 获取日志分析容器---controller类型 */
		public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerController() {
			return DQ_LOG_ANALYSIS_CONTAINER_CONTROLLER;
		}

		/** 获取日志分析容器---other类型 */
		public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerOther() {
			return DQ_LOG_ANALYSIS_CONTAINER_OTHER;
		}
	}
}
