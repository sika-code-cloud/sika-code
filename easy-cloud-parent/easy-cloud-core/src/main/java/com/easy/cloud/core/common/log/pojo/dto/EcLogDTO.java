package com.easy.cloud.core.common.log.pojo.dto;

import org.slf4j.Logger;

import com.alibaba.fastjson.annotation.JSONField;
import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;

/**
 * 
 * <p>
 * 日志数据传输对象
 * </p>
 *
 * <pre>
 *  说明：所有日志传输对象的基础类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月8日 上午9:25:57
 */
public class EcLogDTO {
	private EcBaseAspectDTO baseAspectDTO;
	/** 请求路径 */
	private String requestPath;
	/** 开始时间毫秒数 */
	private Long beginTimeMillis;
	/** 结束时间毫秒数 */
	private Long endTimeMillis;
	/** 方法运行时间毫秒数 */
	private long runTimeMinllis;
	/** 日志记录对象 -- 该对象来源于目标class中的Logger属性 */
	@JSONField(serialize = false)
	private Logger logger;
	/** 日志类型---{@link} DqLogConstant.DqLogType */
	private EcLogTypeEnum logType;

	public EcLogDTO() {

	}

	private EcLogDTO(Long beginTimeMillis, Long endTimeMillis) {
		this.beginTimeMillis = beginTimeMillis;
		this.endTimeMillis = endTimeMillis;
	}

	public static EcLogDTO newInstance() {
		return new EcLogDTO();
	}

	public static EcLogDTO newInstance(Long beginTimeMillis, Long endTimeMillis) {
		return new EcLogDTO(beginTimeMillis, endTimeMillis).buildRunTimeMillis();
	}

	public EcLogDTO buildExecuteTimeMillis(Long beginTimeMillis, Long endTimeMillis) {
		return buildRunTimeMillis(endTimeMillis - beginTimeMillis);
	}

	public EcLogDTO buildRunTimeMillis(long executeTimeMinllis) {
		this.runTimeMinllis = executeTimeMinllis;
		return this;
	}

	private EcLogDTO buildRunTimeMillis() {
		this.runTimeMinllis = this.endTimeMillis - this.beginTimeMillis;
		return this;
	}

	public EcBaseAspectDTO getBaseAspectDTO() {
		return baseAspectDTO;
	}

	public void setBaseAspectDTO(EcBaseAspectDTO baseAspectDTO) {
		this.baseAspectDTO = baseAspectDTO;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public Long getBeginTimeMillis() {
		return beginTimeMillis;
	}

	public void setBeginTimeMillis(Long beginTimeMillis) {
		this.beginTimeMillis = beginTimeMillis;
	}

	public Long getEndTimeMillis() {
		return endTimeMillis;
	}

	public void setEndTimeMillis(Long endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
	}

	public Long getRunTimeMinllis() {
		return runTimeMinllis;
	}

	public void setRunTimeMinllis(Long runTimeMinllis) {
		this.runTimeMinllis = runTimeMinllis;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public EcLogTypeEnum getLogType() {
		return logType;
	}

	public void setLogType(EcLogTypeEnum logType) {
		this.logType = logType;
	}

}
