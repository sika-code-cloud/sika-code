package com.easy.cloud.pay.zfb.pojo.dto;

import java.util.Map;

import com.easy.cloud.pay.common.payment.pojo.dto.EcPayResultDTO;

/**
 * 
 * <p>
 * 微信支付结果数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午2:51:35
 */
public class EcZfbPayResultDTO extends EcPayResultDTO {
	public EcZfbPayResultDTO() {

	}

	public EcZfbPayResultDTO(Map<String, Object> rspResult) {
		super(rspResult);
	}
}
