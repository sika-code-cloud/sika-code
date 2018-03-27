package com.dq.easy.cloud.module.common.log.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;

/**
 * 
 * <p>
 * 日志配置类
 * </p>
 *
 * <pre>
 *  说明：所有子服务都应该使用@Component注解进行注入
 *  约定：所有子服务的日志配置类都应该继承该类
 *  命名规范：UserLogConfig
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:50:39
 */
@Component
public class DqLogConfig{
	/** 日志开关配置容器 */
	private static Map<String, Boolean> DQ_LOG_SWITCH_CONFIG = new HashMap<>();
	/** 方法分析开关配置容器 */
	private static Map<String, Boolean> DQ_LOG_ANALYSIS_SWITCH_CONFIG = new HashMap<>();
	static {
		
	}
	
	/** 设置日志开关标志*/
	public static void setLogSwitchFlag(boolean switchFlag, String ... switchKeys){
		DQ_LOG_SWITCH_CONFIG.put(DqLogUtils.getLogKey(switchKeys), switchFlag);
	}
	
	public static Map<String, Boolean> getLogSwitchConfig(){
		return DQ_LOG_SWITCH_CONFIG;
	}
	
	/** 设置方法分析开关标志*/
	public static void setLogAnalysisSwitchFlag(boolean switchFlag, String ... switchKeys){
		DQ_LOG_ANALYSIS_SWITCH_CONFIG.put(DqLogUtils.getLogKey(switchKeys), switchFlag);
	}
	
	public static Map<String, Boolean> getLogAnalysisSwitchConfig(){
		return DQ_LOG_ANALYSIS_SWITCH_CONFIG;
	}
}
