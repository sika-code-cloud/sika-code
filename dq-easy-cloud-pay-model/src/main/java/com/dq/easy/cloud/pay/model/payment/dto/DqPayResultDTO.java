package com.dq.easy.cloud.pay.model.payment.dto;

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
	private Map<String, Object> payResult;
	
	
	public DqPayResultDTO() {

	}

	public DqPayResultDTO(Map<String, Object> payResult) {
		this.payResult = payResult;
	}
	
	public Map<String, Object> getPayResult() {
		return payResult;
	}

	public void setPayResult(Map<String, Object> payResult) {
		this.payResult = payResult;
	}

}
