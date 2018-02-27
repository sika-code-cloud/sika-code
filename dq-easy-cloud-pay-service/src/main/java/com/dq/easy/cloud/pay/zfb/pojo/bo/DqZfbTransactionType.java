package com.dq.easy.cloud.pay.zfb.pojo.bo;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.utils.DqPayUtils;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import java.util.Map;

/**
 * 
 * <p>
 * 支付宝交易类型
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午4:02:36
 */
public enum DqZfbTransactionType implements DqTransactionType {
	/** 即时到帐 */
	DIRECT("alipay.trade.page.pay"),
	/** 手机app支付 */
	APP("alipay.trade.app.pay"), 
	/** 手机网站支付 */
	WAP("alipay.trade.wap.pay")
	/** 扫码付 */
	,SWEEPPAY("alipay.trade.precreate"), 
	/** 条码付 */
	BAR_CODE("alipay.trade.pay"), 
	/** 声波付 */
	WAVE_CODE("alipay.trade.pay")
	// 交易辅助接口

	/** 交易订单查询 */
	,QUERY("alipay.trade.query"), 
	/** 交易订单关闭 */
	CLOSE("alipay.trade.close"), 
	/** 退款 */
	REFUND("alipay.trade.refund"), 
	/** 退款查询 */
	REFUNDQUERY("alipay.trade.fastpay.refund.query"), 
	/** 下载对账单 */
	DOWNLOADBILL("alipay.data.dataservice.bill.downloadurl.query"), 
	/** 转账到支付宝 */
	TRANS("alipay.fund.trans.toaccount.transfer"), 
	/** 转账查询 */
	TRANS_QUERY("alipay.fund.trans.order.query");

	private String method;

	private DqZfbTransactionType(String method) {
		this.method = method;
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

	/** 类型是直接支付类型 */
	public static boolean isDIRECT(String type) {
		return DqStringUtils.equals(DIRECT.getType(), type);
	}

	/** 类型是直接支付类型 */
	public static boolean isDIRECT(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(DIRECT, dqTransactionType);
	}

	/** 是否是手机网站支付 */
	public static boolean isWAP(String type) {
		return DqStringUtils.equals(WAP.getType(), type);
	}

	/** 是否是手机网站支付 */
	public static boolean isWAP(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(WAP, dqTransactionType);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(String type) {
		return DqStringUtils.equals(APP.getType(), type);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(APP, dqTransactionType);
	}

	/** 类型是扫码支付 */
	public static boolean isSWEEPPAY(String type) {
		return DqStringUtils.equals(SWEEPPAY.getType(), type);
	}

	/** 类型是扫码支付 */
	public static boolean isSWEEPPAY(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(SWEEPPAY, dqTransactionType);
	}

	/** 类型是BAR_CODE---即条码支付类型 */
	public static boolean isBAR_CODE(String type) {
		return DqStringUtils.equals(BAR_CODE.getType(), type);
	}

	/** 类型是BAR_CODE---即条码支付类型 */
	public static boolean isBAR_CODE(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(BAR_CODE, dqTransactionType);
	}

	/** 类型是WAVE_CODE---即声波支付类型 */
	public static boolean isWAVE_CODE(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(WAVE_CODE, dqTransactionType);
	}

	/** 类型是TRANS---即声波支付类型 */
	public static boolean isWAVE_CODE(String type) {
		return DqStringUtils.equals(TRANS.getType(), type);
	}
	/** 类型是WAVE_CODE---即转账到支付宝类型 */
	public static boolean isTRANS(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(WAVE_CODE, dqTransactionType);
	}

	/** 类型是TRANS---即转账到支付宝类型 */
	public static boolean isTRANS(String type) {
		return DqStringUtils.equals(TRANS.getType(), type);
	}
	
	public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {

	}
}
