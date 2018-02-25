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
	/** isCertSign is false---PAY_CERTSIGN_IS_FLASE---PAY_000001 */
	public static final String PAY_CERTSIGN_IS_FLASE = "PAY_000001";
	/** 通用接口不支持---PAY_CERTSIGN_IS_FLASE---PAY_000002 */
	public static final String PAY_GENERAL_INTERFACE_NOT_SUPPORT = "PAY_000002";
	/** 支付订单数据传输对象不能为空---DQ_PAY_ORDER_DTO_CANT_NULL---PAY_000003 */
	public static final String DQ_PAY_ORDER_DTO_CANT_NULL = "PAY_000003";
	/** 价格不能为空---PRICE_CANT_NULL---PAY_000004 */
	public static final String PRICE_CANT_NULL = "PAY_000004";
	/** 价格不能小于0---PRICE_CANT_LESS_THAN_ZERO---PAY_000005 */
	public static final String PRICE_CANT_LESS_THAN_ZERO = "PAY_000005";
	/** 价格太大---PRICE_CANT_TOO_BIG---PAY_000006 */
	public static final String PRICE_CANT_TOO_BIG = "PAY_000006";
	/** subject不能为空---SUBJECT_CANT_EMPTY---PAY_000007 */
	public static final String SUBJECT_CANT_EMPTY = "PAY_000007";
	/** body不能为空---BODY_CANT_EMPTY---PAY_000008 */
	public static final String BODY_CANT_EMPTY = "PAY_000008";
	/** outTradeNo不能为空---OUT_TRADE_NO_CANT_EMPTY---PAY_000009 */
	public static final String OUT_TRADE_NO_CANT_EMPTY = "PAY_000009";
	/** TransactionType对象不能为空---TRANSACTION_TYPE_CANT_NULL---PAY_000010 */
	public static final String TRANSACTION_TYPE_CANT_NULL = "PAY_000010";
	/** openid不能为空---OPENID_CANT_EMPTY---PAY_000011 */
	public static final String OPENID_CANT_EMPTY = "PAY_000011";
	
	static {
		setErrorMsg(PAY_CERTSIGN_IS_FLASE, "certDescriptor fail, isCertSign is false");
		setErrorMsg(PAY_GENERAL_INTERFACE_NOT_SUPPORT, "通用接口不支持");
		setErrorMsg(DQ_PAY_ORDER_DTO_CANT_NULL, "支付订单数据传输对象不能为空");
		setErrorMsg(PRICE_CANT_NULL, "价格不能为空");
		setErrorMsg(PRICE_CANT_LESS_THAN_ZERO, "价格不能小于0");
		setErrorMsg(PRICE_CANT_TOO_BIG, "价格太大");
		setErrorMsg(SUBJECT_CANT_EMPTY, "subject不能为空");
		setErrorMsg(BODY_CANT_EMPTY, "body不能为空");
		setErrorMsg(OUT_TRADE_NO_CANT_EMPTY, "outTradeNo不能为空");
		setErrorMsg(TRANSACTION_TYPE_CANT_NULL, "TransactionType对象不能为空");
		setErrorMsg(OPENID_CANT_EMPTY, "openid不能为空");
	}
	
}
