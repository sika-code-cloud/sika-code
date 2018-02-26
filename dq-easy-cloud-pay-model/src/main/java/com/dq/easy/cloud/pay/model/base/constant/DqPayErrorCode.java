package com.dq.easy.cloud.pay.model.base.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * 支付错误代码
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:23:52
 */
public enum DqPayErrorCode implements DqBaseErrorCodeInf{
	/** isCertSign is false---PAY_CERTSIGN_IS_FLASE---PAY_000001 */
	PAY_CERTSIGN_IS_FLASE ("PAY_000001", "isCertSign is false"),
	/** 通用接口不支持---PAY_CERTSIGN_IS_FLASE---PAY_000002 */
	PAY_GENERAL_INTERFACE_NOT_SUPPORT ("PAY_000002", "通用接口不支持"),
	/** 支付订单数据传输对象不能为空---DQ_PAY_ORDER_DTO_CANT_NULL---PAY_000003 */
	DQ_PAY_ORDER_DTO_CANT_NULL ("PAY_000003", "支付订单数据传输对象不能为空"),
	/** 价格不能为空---PRICE_CANT_NULL---PAY_000004 */
	PRICE_CANT_NULL ("PAY_000004", "价格不能为空"),
	/** 价格不能小于0---PRICE_CANT_LESS_THAN_ZERO---PAY_000005 */
	PRICE_CANT_LESS_THAN_ZERO ("PAY_000005", "价格不能小于0"),
	/** 价格太大---PRICE_CANT_TOO_BIG---PAY_000006 */
	PRICE_CANT_TOO_BIG ("PAY_000006", "价格太大"),
	/** subject不能为空---SUBJECT_CANT_EMPTY---PAY_000007 */
	SUBJECT_CANT_EMPTY ("PAY_000007", "subject不能为空"),
	/** body不能为空---BODY_CANT_EMPTY---PAY_000008 */
	BODY_CANT_EMPTY ("PAY_000008", "body不能为空"),
	/** outTradeNo不能为空---OUT_TRADE_NO_CANT_EMPTY---PAY_000009 */
	OUT_TRADE_NO_CANT_EMPTY ("PAY_000009", "outTradeNo不能为空"),
	/** TransactionType对象不能为空---TRANSACTION_TYPE_CANT_NULL---PAY_000010 */
	TRANSACTION_TYPE_CANT_NULL ("PAY_000010", " TransactionType对象不能为空"),
	/** openid不能为空---OPENID_CANT_EMPTY---PAY_000011 */
	OPENID_CANT_EMPTY ("PAY_000011", "openid不能为空"),
	/** spbillCreateIp不能为空---SPBILL_CREATE_IP_CANT_EMPTY---PAY_000012 */
	SPBILL_CREATE_IP_CANT_EMPTY ("PAY_000012", "spbillCreateIp不能为空"),
	/** wapUrl不能为空---WAP_URL_CANT_EMPTY---PAY_000013 */
	WAP_URL_CANT_EMPTY ("PAY_000013", "wapUrl不能为空"),
	;
	
	private String errorCode;
	private String errorMsg;

	private DqPayErrorCode(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}
}
