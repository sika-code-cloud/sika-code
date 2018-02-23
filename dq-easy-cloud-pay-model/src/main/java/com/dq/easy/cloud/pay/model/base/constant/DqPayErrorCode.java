package com.dq.easy.cloud.pay.model.base.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;

/**
 * 
 * <p>
 * 支付错误代码
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:23:52
 */
@Component
public class DqPayErrorCode extends DqBaseErrorCode{
	/** isCertSign is false */
	public static final String PAY_CERTSIGN_IS_FLASE = "PAY_000001";
	
	/** 通用接口不支持 */
	public static final String PAY_GENERAL_INTERFACE_NOT_SUPPORT = "PAY_000002";
	
	static {
		setErrorMsg(PAY_CERTSIGN_IS_FLASE, "certDescriptor fail, isCertSign is false");
		setErrorMsg(PAY_GENERAL_INTERFACE_NOT_SUPPORT, "通用接口不支持");
	}
	
}
