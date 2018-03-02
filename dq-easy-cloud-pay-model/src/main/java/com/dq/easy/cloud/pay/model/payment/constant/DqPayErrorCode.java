package com.dq.easy.cloud.pay.model.payment.constant;


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
	/** authCode不能为空---WAP_URL_CANT_EMPTY---PAY_000014 */
	AUTH_CODE_CANT_EMPTY ("PAY_000014", "authCode不能为空"),
	/** tradeNo不能为空---TRADE_NO_CANT_EMPTY---PAY_000015 */
	TRADE_NO_CANT_EMPTY ("PAY_000015", "tradeNo不能为空"),
	/** billDate不能为空---BILL_DATE_CANT_NULL---PAY_000016 */
	BILL_DATE_CANT_NULL ("PAY_000016", "billDate不能为空"),
	/** billType不能为空---BILL_TYPE_CANT_NULL---PAY_000017 */
	BILL_TYPE_CANT_NULL ("PAY_000016", "billType不能为空"),
	/** tradeNoOrBillDate不能为空---TRADE_NO_OR_BILL_DATE_CANT_NULL---PAY_000018 */
	TRADE_NO_OR_BILL_DATE_CANT_NULL ("PAY_000018", "tradeNoOrBillDate不能为空"),
	/** outTradeNoBillType不能为空---OUT_TRADE_NO_BILL_TYPE_CANT_NULL---PAY_000019 */
	OUT_TRADE_NO_BILL_TYPE_CANT_NULL ("PAY_000019", "outTradeNoBillType不能为空"),
	/** bank不能为空---BANK_CANT_NULL---PAY_000020 */
	BANK_CANT_NULL ("PAY_000020", "bank不能为空"),
	/** outNo不能为空---OUT_NO_CANT_NULL---PAY_000021 */
	OUT_NO_CANT_EMPTY ("PAY_000021", "outNo不能为空"),
	/** payeeAccount不能为空---PAYEE_ACCOUNT_CANT_NULL---PAY_000022 */
	PAYEE_ACCOUNT_CANT_EMPTY ("PAY_000022", "payeeAccount不能为空"),
	/** payeeName不能为空---PAYEE_NAME_CANT_NULL---PAY_000023 */
	PAYEE_NAME_CANT_EMPTY ("PAY_000023", "payeeName不能为空"),
	/** amount不能为空---AMOUNT_CANT_NULL---PAY_000024 */
	AMOUNT_CANT_NULL ("PAY_000024", "amount不能为空"),
	/** 调用第三方支付失败---PAY_FAILURE---PAY_000025 */
	PAY_FAILURE ("PAY_000025", "调用第三方支付失败"),
	/** 退款订单号不能为空---REFUND_ORDER_NO_CANT_EMPTY---PAY_000026 */
	REFUND_ORDER_NO_CANT_EMPTY ("PAY_000026", "退款订单号不能为空"),
	/** 退款金额不能为空---REFUND_AMOUNT_CANT_EMPTY---PAY_000027 */
	REFUND_AMOUNT_CANT_NULL ("PAY_000027", "退款金额不能为空"),
	/** 总金额不能为空---TOTAL_AMOUNT_CANT_NULL---PAY_000028 */
	TOTAL_AMOUNT_CANT_NULL ("PAY_000028", "总金额不能为空"),
	/** outNo和tradeNo不能同时为空---TRADE_NO_AND_OUT_TRADE_NO_CANT_EMPTY---PAY_000029 */
	TRADE_NO_AND_OUT_TRADE_NO_CANT_EMPTY ("PAY_000029", "outNo和tradeNo不能同时为空"),
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
