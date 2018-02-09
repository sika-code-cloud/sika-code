package com.dq.easy.cloud.model.common.log.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 日志配置类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：所有子服务的日志配置类都应该继承该类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午5:50:39
 */
@Component
public class DqLogConfig {
	protected static final Map<String, Boolean> DQ_LOG_SWITCH_CONFIG = new HashMap<>();
	
	/**
	 * 
	 * <p>
	 * 获取日志开关
	 * </p>
	 *
	 * @param dqLogOpenFlag : Boolean : 注解的开关标志
	 * @param switchKey : String : 开关的key
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月9日 下午6:05:15
	 */
	public static boolean getLogSwitch(boolean dqLogOpenFlag, String className, String methodName){
//		若注解为关闭--不作处理直接返回
		if (!dqLogOpenFlag){
			return dqLogOpenFlag;
		}
//		类名为空直接返回true
		if (DqStringUtils.isEmpty(className)){
			return true;
		}
//		根据类名和方法名获取开关的key
		String switchKey = DqLogUtils.getLogSwitchKey(className, methodName);
		if (DqStringUtils.isEmpty(switchKey)){
			return true;
		}
		Boolean switchFlag = DQ_LOG_SWITCH_CONFIG.get(switchKey);
//		若config中标志不为空直接返回
		if (DqBaseUtils.isNotNull(switchFlag)){
			return switchFlag;
		}
//		根据类名获取开关key
		switchKey = DqLogUtils.getLogSwitchKey(className);
//		获取对类的日志开关
		switchFlag = DQ_LOG_SWITCH_CONFIG.get(switchKey);
		return switchFlag == null ? true : switchFlag;
	}
}
