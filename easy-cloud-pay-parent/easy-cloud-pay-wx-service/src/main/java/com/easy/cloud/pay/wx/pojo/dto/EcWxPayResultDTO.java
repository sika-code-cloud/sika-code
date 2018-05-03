package com.easy.cloud.pay.wx.pojo.dto;

import java.util.Map;

import com.easy.cloud.pay.core.payment.pojo.dto.EcPayResultDTO;

/**
 * 
 * <p>
 * 微信支付结果数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午2:51:35
 */
public class EcWxPayResultDTO extends EcPayResultDTO {
	public EcWxPayResultDTO() {

	}

	public EcWxPayResultDTO(Map<String, Object> rspResult) {
		super(rspResult);
	}
}
