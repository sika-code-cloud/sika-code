package com.dq.easy.cloud.model.common.log.pojo.dto;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseDTO;
/**
 * 
 * <p>
 * 日志分析数据传输对象
 * </p>
 *
 * <pre>
 *  说明：日志分析的业务逻辑对象
 *  约定：创建该对象的实例必须使用newInstance(DqLogDTO dqLogDTO)静态方法进行创建，默认构造只是提供给json序列号
 *  命名规范：
 *  使用示例：DqLogAnalysisDTO.newInstance(dqLogDTO)
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月22日 上午10:17:31
 */
public class DqLogAnalysisDTO extends DqBaseDTO {
	private DqLogDTO dqLogDTO;
	/** 方法运行时间毫秒数---总运行时间 */
	private long runTimeMinllisTotal;
	/** 方法运行次数---总运行次数 */
	private long runTimesTotal;
	/** 方法运行时间毫秒数---平均运行时间 */
	private long runTimeMinllisAvg;
	/** 方法运行时间毫秒数---最大运行时间 */
	private long runTimeMinllisMax;
	/** 方法运行时间毫秒数---最小运行时间 */
	private long runTimeMinllisMin;
	/** 是否构建了总运行次数数据 */
	private boolean isBuildRunTimesTotal;
	/** 是否构建了总运行时间数据 */
	private boolean isBuildRunTimeMinllisTotal;
	/** 日志类型---{@link} DqLogConstant.DqLogType */
	private int logType;
	
	public static DqLogAnalysisDTO newInstance(DqLogDTO dqLogDTO) {
		return new DqLogAnalysisDTO(dqLogDTO);
	}

	public DqLogAnalysisDTO() {

	}

	private DqLogAnalysisDTO(DqLogDTO dqLogDTO) {
		this.dqLogDTO = dqLogDTO;
	}
	
	/** 构建运行时间戳---总运行时间 */
	public DqLogAnalysisDTO buildRunTimeMinllisTotal() {
		this.runTimeMinllisTotal += dqLogDTO.getRunTimeMinllis();
		this.isBuildRunTimeMinllisTotal = true;
		return this;
	}

	/** 构建运行总次数 */
	public DqLogAnalysisDTO buildRunTimesTotal() {
		this.runTimesTotal += 1;
		this.isBuildRunTimesTotal = true;
		return this;
	}
	
	/** 构建运行时间戳---平均运行时间 */
	public DqLogAnalysisDTO buildRunTimeMinllisAvg() {
		if (!this.isBuildRunTimeMinllisTotal){
			this.buildRunTimeMinllisTotal();
		}
		if (!this.isBuildRunTimesTotal){
			this.buildRunTimesTotal();
		}
		this.runTimeMinllisAvg = this.runTimeMinllisTotal / this.runTimesTotal;
		return this;
	}

	/** 构建运行时间戳---最小运行时间 */
	public DqLogAnalysisDTO buildRunTimeMinllisMin() {
		long runTimeMinllis = this.dqLogDTO.getRunTimeMinllis();

		if (runTimeMinllisMin == 0) {
			this.runTimeMinllisMin = runTimeMinllis;
			return this;
		}

		if (this.runTimeMinllisMin > runTimeMinllis) {
			this.runTimeMinllisMin = runTimeMinllis;
		}
		return this;
	}

	/** 构建运行时间戳---最大运行时间 */
	public DqLogAnalysisDTO buildRunTimeMinllisMax() {
		long runTimeMinllis = this.dqLogDTO.getRunTimeMinllis();

		if (runTimeMinllisMax == 0) {
			this.runTimeMinllisMax = runTimeMinllis;
			return this;
		}

		if (this.runTimeMinllisMax < runTimeMinllis) {
			this.runTimeMinllisMax = runTimeMinllis;
		}
		return this;
	}
	
	/** 构建日志类型 */
	public DqLogAnalysisDTO buildLogType() {
		this.logType = dqLogDTO.getLogType();
		return this;
	}
	public DqLogDTO getDqLogDTO() {
		return dqLogDTO;
	}
	
	public void setDqLogDTO(DqLogDTO dqLogDTO) {
		this.dqLogDTO = dqLogDTO;
	}

	public void setRunTimeMinllisAvg(long runTimeMinllisAvg) {
		this.runTimeMinllisAvg = runTimeMinllisAvg;
	}

	public long getRunTimeMinllisAvg() {
		return runTimeMinllisAvg;
	}

	public long getRunTimeMinllisTotal() {
		return runTimeMinllisTotal;
	}

	public void setRunTimeMinllisTotal(long runTimeMinllisTotal) {
		this.runTimeMinllisTotal = runTimeMinllisTotal;
	}

	public long getRunTimeMinllisMax() {
		return runTimeMinllisMax;
	}

	public void setRunTimeMinllisMax(long runTimeMinllisMax) {
		this.runTimeMinllisMax = runTimeMinllisMax;
	}

	public long getRunTimeMinllisMin() {
		return runTimeMinllisMin;
	}

	public void setRunTimeMinllisMin(long runTimeMinllisMin) {
		this.runTimeMinllisMin = runTimeMinllisMin;
	}

	public long getRunTimesTotal() {
		return runTimesTotal;
	}

	public void setRunTimesTotal(long runTimesTotal) {
		this.runTimesTotal = runTimesTotal;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

}
