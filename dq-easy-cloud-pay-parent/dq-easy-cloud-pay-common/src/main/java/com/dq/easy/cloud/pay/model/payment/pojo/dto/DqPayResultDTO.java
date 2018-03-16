package com.dq.easy.cloud.pay.model.payment.pojo.dto;

import java.util.Map;

/**
 * 
 * <p>
 * 支付相关结果数据传输对象---与业务逻辑无关
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月24日 下午2:46:09
 */
public class DqPayResultDTO {
	private Map<String, Object> rspResult;
	
	
	public DqPayResultDTO() {

	}

	public DqPayResultDTO(Map<String, Object> rspResult) {
		this.rspResult = rspResult;
	}

	public Map<String, Object> getRspResult() {
		return rspResult;
	}

	public void setRspResult(Map<String, Object> rspResult) {
		this.rspResult = rspResult;
	}
	

}
