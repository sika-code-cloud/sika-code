package com.easy.cloud.pay.zfb.pojo.bo;

import java.util.Map;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.payment.utils.EcPayUtils;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;

/**
 * 
 * <p>
 * 支付宝交易类型
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午4:02:36
 */
public enum EcZfbTransactionType implements EcTransactionType {
	/** 即时到帐 */
	DIRECT("alipay.trade.page.pay", "alipay_trade_pay_response"),
	/** 手机app支付 */
	APP("alipay.trade.app.pay", "alipay_trade_pay_response"), 
	/** 手机网站支付 */
	WAP("alipay.trade.wap.pay", "alipay_trade_pay_response")
	/** 扫码付 */
	,SWEEPPAY("alipay.trade.precreate", "alipay_trade_precreate_response"), 
	/** 条码付 */
	BAR_CODE("alipay.trade.pay", "alipay_trade_pay_response"), 
	/** 声波付 */
	WAVE_CODE("alipay.trade.pay", "alipay_trade_pay_response")
	// 交易辅助接口

	/** 交易订单查询 */
	,QUERY("alipay.trade.query", "alipay_trade_query_response"), 
	/** 交易订单关闭 */
	CLOSE("alipay.trade.close", "alipay_trade_close_response"), 
	/** 退款 */
	REFUND("alipay.trade.refund", "alipay_trade_refund_response"), 
	/** 退款查询 */
	REFUNDQUERY("alipay.trade.fastpay.refund.query", "alipay_trade_fastpay_refund_query_response"), 
	/** 下载对账单 */
	DOWNLOADBILL("alipay.data.dataservice.bill.downloadurl.query", "alipay_data_dataservice_bill_downloadurl_query_response"), 
	/** 转账到支付宝 */
	TRANS("alipay.fund.trans.toaccount.transfer", "alipay_fund_trans_toaccount_transfer_response"), 
	/** 转账查询 */
	TRANS_QUERY("alipay.fund.trans.order.query", "alipay_fund_trans_order_query_response");

	private String method;
	private String responseKey;
	private EcZfbTransactionType(String method, String responseKey) {
		this.method = method;
		this.responseKey = responseKey;
	}
	@Override
	public String getType() {
		return this.name();
	}

	/*
	 * * 获取接口名称
	 * 
	 * @return 接口名称
	 */
	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getResponseKey() {
		return responseKey;
	}
	/** 类型是直接支付类型 */
	public static boolean isDIRECT(String type) {
		return EcStringUtils.equals(DIRECT.getType(), type);
	}

	/** 类型是直接支付类型 */
	public static boolean isDIRECT(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(DIRECT, ecTransactionType);
	}

	/** 是否是手机网站支付 */
	public static boolean isWAP(String type) {
		return EcStringUtils.equals(WAP.getType(), type);
	}

	/** 是否是手机网站支付 */
	public static boolean isWAP(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(WAP, ecTransactionType);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(String type) {
		return EcStringUtils.equals(APP.getType(), type);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(APP, ecTransactionType);
	}

	/** 类型是扫码支付 */
	public static boolean isSWEEPPAY(String type) {
		return EcStringUtils.equals(SWEEPPAY.getType(), type);
	}

	/** 类型是扫码支付 */
	public static boolean isSWEEPPAY(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(SWEEPPAY, ecTransactionType);
	}

	/** 类型是BAR_CODE---即条码支付类型 */
	public static boolean isBAR_CODE(String type) {
		return EcStringUtils.equals(BAR_CODE.getType(), type);
	}

	/** 类型是BAR_CODE---即条码支付类型 */
	public static boolean isBAR_CODE(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(BAR_CODE, ecTransactionType);
	}

	/** 类型是WAVE_CODE---即声波支付类型 */
	public static boolean isWAVE_CODE(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(WAVE_CODE, ecTransactionType);
	}

	/** 类型是TRANS---即声波支付类型 */
	public static boolean isWAVE_CODE(String type) {
		return EcStringUtils.equals(WAVE_CODE.getType(), type);
	}
	/** 类型是WAVE_CODE---即转账到支付宝类型 */
	public static boolean isTRANS(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(TRANS, ecTransactionType);
	}

	/** 类型是TRANS---即转账到支付宝类型 */
	public static boolean isTRANS(String type) {
		return EcStringUtils.equals(TRANS.getType(), type);
	}
	
	/** 类型是REFUND---即退款类型 */
	public static boolean isREFUND(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(REFUND, ecTransactionType);
	}

	/** 类型是REFUND---即退款类型 */
	public static boolean isREFUND(String type) {
		return EcStringUtils.equals(REFUND.getType(), type);
	}
	
	public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {

	}
}
