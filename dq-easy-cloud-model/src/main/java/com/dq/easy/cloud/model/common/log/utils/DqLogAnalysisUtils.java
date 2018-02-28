package com.dq.easy.cloud.model.common.log.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dq.easy.cloud.model.basic.constant.DqBaseConstant;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.array.DqArrayUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.config.DqLogConfig;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogAnalysisContainer;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogType;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogAnalysisDTO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.properties.utils.DqPropertiesUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 日志分析工具类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月22日 下午12:59:12
 */
public class DqLogAnalysisUtils {
	/**
	 * <p>
	 * 获取日志分析开关
	 * </p>
	 *
	 * @param dqLog : DqLog : 日志注解
	 * @param dqLogDTO : DqLogDTO : 日志传输对象
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午6:05:15
	 */
	public static boolean getLogAnalysisSwitch(DqLog dqLog, DqLogDTO dqLogDTO) {
		boolean dqMethodAnalysisSwitch = dqLog.dqLogAnalysisSwitch();
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
		Boolean switchFlag = DqLogConfig.getLogAnalysisSwitchConfig().get(switchKey);
		// 若config中标志不为空直接返回
		if (DqBaseUtils.isNotNull(switchFlag)) {
			return switchFlag;
		}
		// 根据类名获取开关key
		switchKey = DqLogUtils.getLogKey(className);
		// 获取对类的日志开关
		switchFlag = DqLogConfig.getLogAnalysisSwitchConfig().get(switchKey);
		return switchFlag == null ? dqMethodAnalysisSwitch : switchFlag;
	}

	/**
	 * 
	 * <p>
	 * 从容器中获取日志分析数据传输对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqLogDTO.dqLogType : int : 日志类型 : 是
	 *     dqLogDTO.targetClassName : String : 目标类名 : 是
	 *     dqLogDTO.targetMethodName : String : 目标方法名 : 是
	 *     dqLogDTO.parameterTypes : Object[] : 参数类型数组 : 是
	 * </pre>
	 * 
	 * @param dqLogDTO : DqLogDTO ：日志数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static DqLogAnalysisDTO getDqLogAnalysisDTOFromContainer(Map<String, DqLogAnalysisDTO> containerMap,DqLogDTO dqLogDTO) {
		return containerMap.get(getLogKeyOfDqLogAnalysis(dqLogDTO));
	}
	
	/**
	 * 
	 * <p>
	 * 从redis中获取日志分析数据传输对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqLogDTO.dqLogType : int : 日志类型 : 是
	 *     dqLogDTO.targetClassName : String : 目标类名 : 是
	 *     dqLogDTO.targetMethodName : String : 目标方法名 : 是
	 *     dqLogDTO.parameterTypes : Object[] : 参数类型数组 : 是
	 * </pre>
	 * 
	 * @param dqLogDTO : DqLogDTO ：日志数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static DqLogAnalysisDTO getDqLogAnalysisDTOFromRedis(DqLogDTO dqLogDTO) {
		String switchKey = getLogKeyOfDqLogAnalysis(dqLogDTO);
//		从redis中获取日志分析数据传输对象
		// TODO 从redis中获取日志分析数据传输对象
		return null;
	}
	
	/**
	 * 
	 * <p>
	 * 设置日志分析数据传输对象到容器中
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqLogDTO.targetClassName : String : 目标类名 : 是
	 *     dqLogDTO.targetMethodName : String : 目标方法名 : 是
	 *     dqLogDTO.parameterTypes : Object[] : 参数类型数组 : 是
	 *     dqLogDTO.dqLogType : int : 日志类型 : 是
	 * </pre>
	 * 
	 * @param dqLogAnalysisDTO : DqLogAnalysisDTO ：日志分析数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static void setDqLogAnalysisDTOToContainer(Map<String, DqLogAnalysisDTO> dqLogAnalysisDTOContainer, DqLogAnalysisDTO dqLogAnalysisDTO) {
		String switchKey = getLogKeyOfDqLogAnalysis(dqLogAnalysisDTO.getDqLogDTO());
		dqLogAnalysisDTOContainer.put(switchKey, dqLogAnalysisDTO);
	}
	
	/**
	 * 
	 * <p>
	 * 设置日志分析数据传输对象到redis中
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqLogDTO.targetClassName : String : 目标类名 : 是
	 *     dqLogDTO.targetMethodName : String : 目标方法名 : 是
	 *     dqLogDTO.parameterTypes : Object[] : 参数类型数组 : 是
	 *     dqLogDTO.dqLogType : int : 日志类型 : 是
	 * </pre>
	 * 
	 * @param dqLogAnalysisDTO : DqLogAnalysisDTO ：日志分析数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static void setDqLogAnalysisDTOToRedis(DqLogAnalysisDTO dqLogAnalysisDTO) {
		getLogKeyOfDqLogAnalysis(dqLogAnalysisDTO.getDqLogDTO());
//		设置日志分析数据传输对象到redis中
		// TODO 设置日志分析数据传输对象到redis中
	}
	
	/** 获取日志分析的key */
	private static String getLogKeyOfDqLogAnalysis(DqLogDTO dqLogDTO) {
		String applicationName = DqPropertiesUtils.getStringValue(DqBaseConstant.APPLICATION_NAME_KEY);
		String className = dqLogDTO.getTargetClassName();
		String methodName = dqLogDTO.getTargetMethodName();
		String parameterTypes = DqArrayUtils.getClassArrayStr(dqLogDTO.getTargetParameterTypes());
		return DqLogUtils.getLogKey(applicationName, className, methodName, parameterTypes);
	}
	
	/**
	 * 
	 * <p>
	 * 根据日志类型获取日志分析容器
	 * </p>
	 *
	 * @param dqLogType
	 * @return
	 * @author daiqi 创建时间 2018年2月22日 上午11:19:48
	 */
	public static Map<String, DqLogAnalysisDTO> getLogAnalysisContainerByType(int dqLogType) {
		if (DqLogType.isController(dqLogType)) {
			return DqLogAnalysisContainer.getLogAnalysisContainerController();
		} else if (DqLogType.isLogic(dqLogType)) {
			return DqLogAnalysisContainer.getLogAnalysisContainerLogic();
		} else if (DqLogType.isService(dqLogType)) {
			return DqLogAnalysisContainer.getLogAnalysisContainerService();
		} else if (DqLogType.isRepository(dqLogType)) {
			return DqLogAnalysisContainer.getLogAnalysisContainerRepository();
		} else {
			return DqLogAnalysisContainer.getLogAnalysisContainerOther();
		}
	}

}
