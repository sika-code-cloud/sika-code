package com.easy.cloud.core.common.log.pojo.bo;

import java.util.Map;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.constant.EcLogErrorCodeEnum;
import com.easy.cloud.core.common.log.pojo.dto.EcLogAnalysisDTO;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.log.utils.EcLogAnalysisUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 日志分析业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：日志分析的业务逻辑对象
 *  约定：创建该对象的实例必须使用newInstance(DqLogDTO dqLogDTO)静态方法进行创建，默认构造只是提供给json序列号
 *  命名规范：
 *  使用示例：DqLogAnalysisBO.newInstance(dqLogDTO)
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月22日 上午9:53:18
 */
public class EcLogAnalysisBO {

	private EcLogAnalysisDTO ecLogAnalysisDTO;

	public static EcLogAnalysisBO newInstanceFromContainer(Map<String, EcLogAnalysisDTO> containerMap,
			EcLogDTO ecLogDTO) {
		EcLogAnalysisDTO ecLogAnalysisDTO = EcLogAnalysisUtils.getDqLogAnalysisDTOFromContainer(containerMap, ecLogDTO);
		return newInstance(ecLogAnalysisDTO, ecLogDTO);
	}

	public static EcLogAnalysisBO newInstanceFromRedis(EcLogDTO ecLogDTO) {
		EcLogAnalysisDTO ecLogAnalysisDTO = EcLogAnalysisUtils.getDqLogAnalysisDTOFromRedis(ecLogDTO);
		return newInstance(ecLogAnalysisDTO, ecLogDTO);
	}

	public static EcLogAnalysisBO newInstance(EcLogAnalysisDTO ecLogAnalysisDTO, EcLogDTO ecLogDTO) {
		if (EcBaseUtils.isNull(ecLogAnalysisDTO)) {
			return new EcLogAnalysisBO(ecLogDTO);
		} else {
			return new EcLogAnalysisBO(ecLogAnalysisDTO).buildDqLogDTO(ecLogDTO);
		}
	}

	public EcLogAnalysisBO() {

	}

	private EcLogAnalysisBO(EcLogDTO ecLogDTO) {
		ecLogAnalysisDTO = EcLogAnalysisDTO.newInstance(ecLogDTO);
	}

	private EcLogAnalysisBO(EcLogAnalysisDTO ecLogAnalysisDTO) {
		this.ecLogAnalysisDTO = ecLogAnalysisDTO;
	}

	/** 构建日志分析数据---必须对dqLogAnalysisDTO中的dqLogDTO成员属性进行初始化后才能调用该方法 */
	public EcLogAnalysisBO buildDqLogAnalysisData() {
		try {
			EcLogDTO ecLogDTO = this.ecLogAnalysisDTO.getLogDTO();
			if (EcBaseUtils.isNull(ecLogDTO)) {
				throw new EcBaseBusinessException(EcLogErrorCodeEnum.DQ_LOG_DTO_CANT_NULL);
			}
			this.ecLogAnalysisDTO.buildRunTimeMinllisTotal().buildRunTimesTotal().buildRunTimeMinllisAvg();
			this.ecLogAnalysisDTO.buildRunTimeMinllisMin().buildRunTimeMinllisMax().buildLogType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public EcLogAnalysisBO setDqLogAnalysisDTOToContainer(Map<String, EcLogAnalysisDTO> dqLogAnalysisDTOContainer) {
		EcLogAnalysisUtils.setDqLogAnalysisDTOToContainer(dqLogAnalysisDTOContainer, ecLogAnalysisDTO);
		return this;
	}

	public EcLogAnalysisBO setDqLogAnalysisDTOToRedis() {
		EcLogAnalysisUtils.setDqLogAnalysisDTOToRedis(ecLogAnalysisDTO);
		return this;
	}

	private EcLogAnalysisBO buildDqLogDTO(EcLogDTO ecLogDTO) {
		this.ecLogAnalysisDTO.setLogDTO(ecLogDTO);
		return this;
	}

	public EcLogAnalysisDTO getDqLogAnalysisDTO() {
		return ecLogAnalysisDTO;
	}

}
