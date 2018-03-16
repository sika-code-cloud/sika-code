package com.dq.easy.cloud.model.common.log.pojo.bo;

import java.util.Map;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.log.constant.DqLogErrorCode;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogAnalysisDTO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.log.utils.DqLogAnalysisUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;

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
public class DqLogAnalysisBO {

	private DqLogAnalysisDTO dqLogAnalysisDTO;

	public static DqLogAnalysisBO newInstanceFromContainer(Map<String, DqLogAnalysisDTO> containerMap, DqLogDTO dqLogDTO) {
		DqLogAnalysisDTO dqLogAnalysisDTO = DqLogAnalysisUtils.getDqLogAnalysisDTOFromContainer(containerMap, dqLogDTO);
		return newInstance(dqLogAnalysisDTO, dqLogDTO);
	}
	
	public static DqLogAnalysisBO newInstanceFromRedis(DqLogDTO dqLogDTO) {
		DqLogAnalysisDTO dqLogAnalysisDTO = DqLogAnalysisUtils.getDqLogAnalysisDTOFromRedis(dqLogDTO);
		return newInstance(dqLogAnalysisDTO, dqLogDTO);
	}
	
	public static DqLogAnalysisBO newInstance(DqLogAnalysisDTO dqLogAnalysisDTO, DqLogDTO dqLogDTO) {
		if (DqBaseUtils.isNull(dqLogAnalysisDTO)) {
			return new DqLogAnalysisBO(dqLogDTO);
		} else {
			return new DqLogAnalysisBO(dqLogAnalysisDTO).buildDqLogDTO(dqLogDTO);
		}
	}

	public DqLogAnalysisBO() {

	}

	private DqLogAnalysisBO(DqLogDTO dqLogDTO) {
		dqLogAnalysisDTO = DqLogAnalysisDTO.newInstance(dqLogDTO);
	}

	private DqLogAnalysisBO(DqLogAnalysisDTO dqLogAnalysisDTO) {
		this.dqLogAnalysisDTO = dqLogAnalysisDTO;
	}

	/** 构建日志分析数据---必须对dqLogAnalysisDTO中的dqLogDTO成员属性进行初始化后才能调用该方法 */
	public DqLogAnalysisBO buildDqLogAnalysisData() {
		if (DqBaseUtils.isNull(this.dqLogAnalysisDTO)) {

		}
		DqLogDTO dqLogDTO = this.dqLogAnalysisDTO.getDqLogDTO();
		if (DqBaseUtils.isNull(dqLogDTO)) {
			throw DqBaseBusinessException.newInstance(DqLogErrorCode.DQ_LOG_DTO_CANT_NULL);
		}
		this.dqLogAnalysisDTO.buildRunTimeMinllisTotal().buildRunTimesTotal().buildRunTimeMinllisAvg();
		this.dqLogAnalysisDTO.buildRunTimeMinllisMin().buildRunTimeMinllisMax().buildLogType();
		return this;
	}
	
	public DqLogAnalysisBO setDqLogAnalysisDTOToContainer(Map<String, DqLogAnalysisDTO> dqLogAnalysisDTOContainer){
		DqLogAnalysisUtils.setDqLogAnalysisDTOToContainer(dqLogAnalysisDTOContainer, dqLogAnalysisDTO);
		return this;
	}
	
	public DqLogAnalysisBO setDqLogAnalysisDTOToRedis(){
		DqLogAnalysisUtils.setDqLogAnalysisDTOToRedis(dqLogAnalysisDTO);
		return this;
	}
	
	private DqLogAnalysisBO buildDqLogDTO(DqLogDTO dqLogDTO) {
		this.dqLogAnalysisDTO.setDqLogDTO(dqLogDTO);
		return this;
	}

	public DqLogAnalysisDTO getDqLogAnalysisDTO() {
		return dqLogAnalysisDTO;
	}

}
