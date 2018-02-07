package com.dq.easy.cloud.model.common.log.utils;

import org.slf4j.Logger;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;

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
	 * debug级别的统一格式日志
	 * </p>
	 * @param logTitle : String : 打印的日志主题
	 * @param logDetail : Object : 打印的日志详情信息
	 * @param logger : Logger : 目标类的日志记录对象
	 * @author daiqi
	 * 创建时间    2018年2月3日 下午5:21:06
	 */
	public static void debug(String logTitle, Object logDetail, Logger logger){
		logger = filterLogger(logger);
		logger.debug("\r");
		logger.debug("******************   start_logger:"+logTitle+":start_logger   ******************");
		logger.debug(DqJSONUtils.parseObject(logDetail, String.class));
		logger.debug("*********************   end_logger:"+logTitle+":end_logger   ********************");
		logger.debug("\r\n");
	}
	
	/**
	 * <p>
	 * info级别的统一格式日志
	 * </p>
	 * @param logTitle : String : 打印的日志主题
	 * @param logDetail : Object : 打印的日志详情信息
	 * @param logger : Logger : 目标类的日志记录对象
	 * @author daiqi
	 * 创建时间    2018年2月3日 下午5:21:06
	 */
	public static void info(String logTitle, Object logDetail, Logger logger){
		logger = filterLogger(logger);
		logger.info("\r");
		logger.info("******************   start_logger:"+logTitle+":start_logger   ******************");
		logger.info(DqJSONUtils.parseObject(logDetail, String.class));
		logger.info("*********************   end_logger:"+logTitle+":end_logger   ********************");
		logger.info("\r\n");
	}
	
	/**
	 * <p>
	 * warn级别的统一格式日志
	 * </p>
	 * @param logTitle : String : 打印的日志主题
	 * @param logDetail : Object : 打印的日志详情信息
	 * @param logger : Logger : 目标类的日志记录对象
	 * @author daiqi
	 * 创建时间    2018年2月3日 下午5:21:06
	 */
	public static void warn(String logTitle, Object logDetail, Logger logger){
		logger = filterLogger(logger);
		logger.warn("\r");
		logger.warn("******************   start_logger:"+logTitle+":start_logger   ******************");
		logger.warn(DqJSONUtils.parseObject(logDetail, String.class));
		logger.warn("*********************   end_logger:"+logTitle+":end_logger   ********************");
		logger.warn("\r\n");
	}
	
	/**
	 * <p>
	 * error级别的统一格式日志
	 * </p>
	 * @param logTitle : String : 打印的日志主题
	 * @param logDetail : Object : 打印的日志详情信息
	 * @param logger : Logger : 目标类的日志记录对象
	 * @author daiqi
	 * 创建时间    2018年2月3日 下午5:21:06
	 */
	public static void error(String logTitle, Object logDetail, Logger logger){
		logger = filterLogger(logger);
		logger.error("\r");
		logger.error("******************   start_logger:"+logTitle+":start_logger   ******************");
		logger.error(DqJSONUtils.parseObject(logDetail, String.class));
		logger.error("*********************   end_logger:"+logTitle+":end_logger   ********************");
		logger.error("\r\n");
	}
	
	private static Logger filterLogger(Logger logger){
		if(DqBaseUtils.isNull(logger)){
			logger = null;
		}
		return logger;
	}
}

