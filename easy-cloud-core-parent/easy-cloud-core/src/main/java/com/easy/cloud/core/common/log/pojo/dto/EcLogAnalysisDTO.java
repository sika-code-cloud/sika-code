package com.easy.cloud.core.common.log.pojo.dto;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
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
public class EcLogAnalysisDTO {
	private EcLogDTO logDTO;
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
	private boolean buildRunTimesTotal;
	/** 是否构建了总运行时间数据 */
	private boolean buildRunTimeMinllisTotal;
	/** 日志类型---{@link} DqLogConstant.DqLogType */
	private EcLogTypeEnum logType;
	
	public static EcLogAnalysisDTO newInstance(EcLogDTO ecLogDTO) {
		return new EcLogAnalysisDTO(ecLogDTO);
	}

	public EcLogAnalysisDTO() {

	}

	private EcLogAnalysisDTO(EcLogDTO ecLogDTO) {
		this.logDTO = ecLogDTO;
	}
	
	/** 构建运行时间戳---总运行时间 */
	public EcLogAnalysisDTO buildRunTimeMinllisTotal() {
		this.runTimeMinllisTotal += logDTO.getRunTimeMinllis();
		this.buildRunTimeMinllisTotal = true;
		return this;
	}

	/** 构建运行总次数 */
	public EcLogAnalysisDTO buildRunTimesTotal() {
		this.runTimesTotal += 1;
		this.buildRunTimesTotal = true;
		return this;
	}
	
	/** 构建运行时间戳---平均运行时间 */
	public EcLogAnalysisDTO buildRunTimeMinllisAvg() {
		if (!this.buildRunTimeMinllisTotal){
			this.buildRunTimeMinllisTotal();
		}
		if (!this.buildRunTimesTotal){
			this.buildRunTimesTotal();
		}
		this.runTimeMinllisAvg = this.runTimeMinllisTotal / this.runTimesTotal;
		return this;
	}

	/** 构建运行时间戳---最小运行时间 */
	public EcLogAnalysisDTO buildRunTimeMinllisMin() {
		long runTimeMinllis = this.logDTO.getRunTimeMinllis();

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
	public EcLogAnalysisDTO buildRunTimeMinllisMax() {
		long runTimeMinllis = this.logDTO.getRunTimeMinllis();

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
	public EcLogAnalysisDTO buildLogType() {
		this.logType = logDTO.getLogType();
		return this;
	}

	public EcLogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(EcLogDTO logDTO) {
		this.logDTO = logDTO;
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

	public EcLogTypeEnum getLogType() {
		return logType;
	}

	public void setLogType(EcLogTypeEnum logType) {
		this.logType = logType;
	}

	/**
	 * 
	 * <p>
	 * 将dqLoDTO对象设置为空
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月28日 下午2:13:42
	 */
	public EcLogAnalysisDTO emptyDqLogDTO(){
		this.logDTO = null;
		return this;
	}

}
