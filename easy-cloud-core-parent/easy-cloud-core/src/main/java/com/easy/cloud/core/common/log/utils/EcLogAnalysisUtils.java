package com.easy.cloud.core.common.log.utils;

import java.util.Map;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant;
import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.config.EcLogConfig;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogAnalysisContainer;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.pojo.dto.EcLogAnalysisDTO;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.properties.utils.EcPropertiesUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

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
public class EcLogAnalysisUtils {
	/**
	 * <p>
	 * 获取日志分析开关
	 * </p>
	 *
	 * @param ecLogAnnotation : DqLog : 日志注解
	 * @param ecLogDTO : DqLogDTO : 日志传输对象
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午6:05:15
	 */
	public static boolean getLogAnalysisSwitch(EcLogAnnotation ecLogAnnotation, EcBaseAspectDTO baseAspectDTO) {
		boolean dqMethodAnalysisSwitch = ecLogAnnotation.analysisSwitch();
		String className = baseAspectDTO.getTargetClassName();
		String methodName = baseAspectDTO.getTargetMethodName();

		// 类名为空直接返回true
		if (EcStringUtils.isEmpty(className)) {
			return true;
		}
		// 根据类名和方法名获取开关的key
		String switchKey = EcLogUtils.getLogKey(className, methodName);
		if (EcStringUtils.isEmpty(switchKey)) {
			return true;
		}
		Boolean switchFlag = EcLogConfig.getLogAnalysisSwitchConfig().get(switchKey);
		// 若config中标志不为空直接返回
		if (EcBaseUtils.isNotNull(switchFlag)) {
			return switchFlag;
		}
		// 根据类名获取开关key
		switchKey = EcLogUtils.getLogKey(className);
		// 获取对类的日志开关
		switchFlag = EcLogConfig.getLogAnalysisSwitchConfig().get(switchKey);
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
	 * @param containerMap : Map<String, EcLogAnalysisDTO> ：日志分析容器
	 * @param baseAspectDTO : EcBaseAspectDTO ：切面数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static EcLogAnalysisDTO getDqLogAnalysisDTOFromContainer(Map<String, EcLogAnalysisDTO> containerMap,EcBaseAspectDTO baseAspectDTO) {
		return containerMap.get(getLogKeyOfDqLogAnalysis(baseAspectDTO));
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
	 * @param ecLogDTO : DqLogDTO ：日志数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static EcLogAnalysisDTO getDqLogAnalysisDTOFromRedis(EcLogDTO ecLogDTO) {
//		String switchKey = getLogKeyOfDqLogAnalysis(dqLogDTO);
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
	 * @param logAnalysisDTOContainer : Map<String, EcLogAnalysisDTO> ：日志分析容器
	 * @param ecLogAnalysisDTO : DqLogAnalysisDTO ：日志分析数据传输对象
	 * @param baseAspectDTO : EcBaseAspectDTO ：切面数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static void setDqLogAnalysisDTOToContainer(Map<String, EcLogAnalysisDTO> logAnalysisDTOContainer, EcLogAnalysisDTO ecLogAnalysisDTO, EcBaseAspectDTO baseAspectDTO) {
		String switchKey = getLogKeyOfDqLogAnalysis(baseAspectDTO);
		logAnalysisDTOContainer.put(switchKey, ecLogAnalysisDTO);
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
	 * @param ecLogAnalysisDTO : DqLogAnalysisDTO ：日志分析数据传输对象
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午11:25:33
	 */
	public static void setDqLogAnalysisDTOToRedis(EcLogAnalysisDTO ecLogAnalysisDTO, EcBaseAspectDTO baseAspectDTO) {
		getLogKeyOfDqLogAnalysis(baseAspectDTO);
//		设置日志分析数据传输对象到redis中
		// TODO 设置日志分析数据传输对象到redis中
	}
	
	/** 获取日志分析的key */
	private static String getLogKeyOfDqLogAnalysis(EcBaseAspectDTO baseAspectDTO) {
		String applicationName = EcPropertiesUtils.getStringValue(EcBaseConfigConstant.APPLICATION_NAME_KEY);
		String className = baseAspectDTO.getTargetClassName();
		String methodName = baseAspectDTO.getTargetMethodName();
		String parameterTypes = EcArrayUtils.getClassArrayStr(baseAspectDTO.getTargetParameterTypes());
		return EcLogUtils.getLogKey(applicationName, className, methodName, parameterTypes);
	}
	
	/**
	 * 
	 * <p>
	 * 根据日志类型获取日志分析容器
	 * </p>
	 *
	 * @param logType
	 * @return
	 * @author daiqi 创建时间 2018年2月22日 上午11:19:48
	 */
	public static Map<String, EcLogAnalysisDTO> getLogAnalysisContainerByType(EcLogTypeEnum logType) {
		if (EcLogTypeEnum.isController(logType)) {
			return EcLogAnalysisContainer.getLogAnalysisContainerController();
		} else if (EcLogTypeEnum.isLogic(logType)) {
			return EcLogAnalysisContainer.getLogAnalysisContainerLogic();
		} else if (EcLogTypeEnum.isService(logType)) {
			return EcLogAnalysisContainer.getLogAnalysisContainerService();
		} else if (EcLogTypeEnum.isRepository(logType)) {
			return EcLogAnalysisContainer.getLogAnalysisContainerRepository();
		} else {
			return EcLogAnalysisContainer.getLogAnalysisContainerOther();
		}
	}

}
