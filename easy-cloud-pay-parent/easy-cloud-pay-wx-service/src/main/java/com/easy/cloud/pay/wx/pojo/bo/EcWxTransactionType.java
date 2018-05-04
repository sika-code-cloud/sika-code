package com.easy.cloud.pay.wx.pojo.bo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.payment.constant.EcWxPayConstant.EcWxPayKey;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.payment.utils.EcPayUtils;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;

/**
 * 
 * <p>
 * 微信交易类型
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午4:02:36
 */
public enum EcWxTransactionType implements EcTransactionType {
	/**
	 * 公众号支付
	 */
	JSAPI("pay/unifiedorder", "") {
		@Override
		public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {
			parameters.put(EcWxPayKey.OPENID_KEY, order.getOpenid());
		}
	},
	/**
	 * 扫码付
	 */
	NATIVE("pay/unifiedorder", "") {
		@Override
		public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {
			parameters.put(EcWxPayKey.PRODUCT__ID_KEY, order.getOutTradeNo());
		}
	},
	/**
	 * 移动支付
	 */
	APP("pay/unifiedorder", ""), 
	/** H5支付 */
	MWEB("pay/unifiedorder", "") {
		@Override
		public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {
			// H5支付专用
			LinkedHashMap<String, Object> value = new LinkedHashMap<>();
			value.put(EcWxPayKey.TYPE_KEY, "Wap");
			// WAP网站URL地址
			value.put(EcWxPayKey.WAP__URL_KEY, order.getWapUrl());
			// WAP 网站名
			value.put(EcWxPayKey.WAP__NAME_KEY, order.getWapName());

			Map<String, Object> sceneInfo = new HashMap<>();
			sceneInfo.put(EcWxPayKey.H5__INFO_KEY, value);
			parameters.put(EcWxPayKey.SCENE__INFO_KEY, EcJSONUtils.parseObject(sceneInfo, String.class));
		}
	},
	/**
	 * 刷卡付
	 */
	MICROPAY("pay/micropay", "") {
		@Override
		public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {
			parameters.put(EcWxPayKey.AUTH__CODE_KEY, order.getAuthCode());
			parameters.remove(EcWxPayKey.NOTIFY__URL_KEY);
			parameters.remove(EcWxPayKey.TRADE__TYPE_KEY);
		}
	},
	/** 查询订单 */
	QUERY("pay/orderquery", ""), 
	/** 关闭订单 */
	CLOSE("pay/closeorder", ""), 
	/** 申请退款 */
	REFUND("secapi/pay/refund", ""), 
	/** 查询退款 */
	REFUNDQUERY("pay/refundquery", ""), 
	/** 下载对账单 */
	DOWNLOADBILL("pay/downloadbill", ""), 
	/** 银行卡转账 */
	BANK("mmpaysptrans/pay_bank", ""), 
	/** 转账查询 */
	QUERY_BANK("mmpaysptrans/query_bank", "");

	EcWxTransactionType(String method, String responseKey) {
		this.method = method;
		this.responseKey = responseKey;
	}

	private String method;
	private String responseKey;

	@Override
	public String getType() {
		return this.name();
	}

	@Override
	public String getResponseKey() {
		return responseKey;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	/** 类型是JSAPI支付类型 */
	public static boolean isJSAPI(String type) {
		return EcStringUtils.equals(JSAPI.getType(), type);
	}

	/** 类型是JSAPI支付类型 */
	public static boolean isJSAPI(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(JSAPI, ecTransactionType);
	}

	/** 是否是扫码支付 */
	public static boolean isNATIVE(String type) {
		return EcStringUtils.equals(NATIVE.getType(), type);
	}

	/** 是否是扫码支付 */
	public static boolean isNATIVE(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(NATIVE, ecTransactionType);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(String type) {
		return EcStringUtils.equals(APP.getType(), type);
	}

	/** 是否是移动App支付 */
	public static boolean isAPP(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(APP, ecTransactionType);
	}

	/** 类型是MWEB---即H5支付类型 */
	public static boolean isMWEB(String type) {
		return EcStringUtils.equals(MWEB.getType(), type);
	}

	/** 类型是MWEB---即H5支付类型 */
	public static boolean isMWEB(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(MWEB, ecTransactionType);
	}

	/** 类型是MICROPAY---即刷卡支付类型 */
	public static boolean isMICROPAY(String type) {
		return EcStringUtils.equals(MICROPAY.getType(), type);
	}

	/** 类型是MICROPAY---即刷卡支付类型 */
	public static boolean isMICROPAY(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(MICROPAY, ecTransactionType);
	}

	/** 类型是BANK---银行卡转账 */
	public static boolean isBANK(String type) {
		return EcStringUtils.equals(BANK.getType(), type);
	}

	/** 类型是BANK---银行卡转账 */
	public static boolean isBANK(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(BANK, ecTransactionType);
	}

	/** 类型是REFUND---退款类型 */
	public static boolean isREFUND(String type) {
		return EcStringUtils.equals(REFUND.getType(), type);
	}

	/** 类型是REFUND---退款类型 */
	public static boolean isREFUND(EcTransactionType ecTransactionType) {
		return EcPayUtils.equalsEcTransactionType(REFUND, ecTransactionType);
	}

	public void setAttribute(Map<String, Object> parameters, EcPayOrderDTO order) {

	}

}
