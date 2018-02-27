package com.dq.easy.cloud.pay.wx.pojo.bo;

import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.utils.DqPayUtils;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.wx.constant.DqWxPayConstant.DqWxPayKey;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * <p>
 * 微信交易类型
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午4:02:36
 */
public enum DqWxTransactionType implements DqTransactionType {
	/**
	 * 公众号支付
	 */
	JSAPI("pay/unifiedorder") {
		@Override
		public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
			parameters.put(DqWxPayKey.OPENID_KEY, order.getOpenid());
		}
	},
	/**
	 * 扫码付
	 */
	NATIVE("pay/unifiedorder") {
		@Override
		public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
			parameters.put(DqWxPayKey.PRODUCT__ID_KEY, order.getOutTradeNo());
		}
	},
	/**
	 * 移动支付
	 */
	APP("pay/unifiedorder"),
	/**
	 * H5支付
	 */
	MWEB("pay/unifiedorder") {
		@Override
		public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
			// H5支付专用
			LinkedHashMap<String, Object> value = new LinkedHashMap<>();
			value.put(DqWxPayKey.TYPE_KEY, "Wap");
			// WAP网站URL地址
			value.put(DqWxPayKey.WAP__URL_KEY, order.getWapUrl());
			// WAP 网站名
			value.put(DqWxPayKey.WAP__NAME_KEY, order.getWapName());

			Map<String, Object> sceneInfo = new HashMap<>();
			sceneInfo.put(DqWxPayKey.H5__INFO_KEY, value);
			parameters.put(DqWxPayKey.SCENE__INFO_KEY, DqJSONUtils.parseObject(sceneInfo, String.class));
		}
	},
	/**
	 * 刷卡付
	 */
	MICROPAY("pay/micropay") {
		@Override
		public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
			parameters.put(DqWxPayKey.AUTH__CODE_KEY, order.getAuthCode());
			parameters.remove(DqWxPayKey.NOTIFY__URL_KEY);
			parameters.remove(DqWxPayKey.TRADE__TYPE_KEY);
		}
	},
	/**
	 * 查询订单
	 */
	QUERY("pay/orderquery"),
	/**
	 * 关闭订单
	 */
	CLOSE("pay/closeorder"),
	/**
	 * 申请退款
	 */
	REFUND("secapi/pay/refund"),
	/**
	 * 查询退款
	 */
	REFUNDQUERY("pay/refundquery"),
	/**
	 * 下载对账单
	 */
	DOWNLOADBILL("pay/downloadbill"),
	/**
	 * 银行卡转账
	 */
	BANK("mmpaysptrans/pay_bank"),
	/**
	 * 转账查询
	 */
	QUERY_BANK("mmpaysptrans/query_bank");

	DqWxTransactionType(String method) {
		this.method = method;
	}

	private String method;

	@Override
	public String getType() {
		return this.name();
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	/** 类型是JSAPI支付类型 */
	public static boolean isJSAPI(String type) {
		return DqStringUtils.equals(JSAPI.getType(), type);
	}

	/** 类型是JSAPI支付类型 */
	public static boolean isJSAPI(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(JSAPI, dqTransactionType);
	}

	/** 是否是扫码支付 */
	public static boolean isNATIVE(String type) {
		return DqStringUtils.equals(NATIVE.getType(), type);
	}
	
	/** 是否是扫码支付 */
	public static boolean isNATIVE(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(NATIVE, dqTransactionType);
	}
	
	/** 是否是移动App支付 */
	public static boolean isAPP(String type) {
		return DqStringUtils.equals(APP.getType(), type);
	}
	
	/** 是否是移动App支付 */
	public static boolean isAPP(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(APP, dqTransactionType);
	}
	
	/** 类型是MWEB---即H5支付类型 */
	public static boolean isMWEB(String type) {
		return DqStringUtils.equals(MWEB.getType(), type);
	}

	/** 类型是MWEB---即H5支付类型 */
	public static boolean isMWEB(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(MWEB, dqTransactionType);
	}
	
	/** 类型是MICROPAY---即刷卡支付类型 */
	public static boolean isMICROPAY(String type) {
		return DqStringUtils.equals(MICROPAY.getType(), type);
	}

	/** 类型是MICROPAY---即刷卡支付类型 */
	public static boolean isMICROPAY(DqTransactionType dqTransactionType) {
		return DqPayUtils.equalsDqTransactionType(MICROPAY, dqTransactionType);
	}
	
	public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {

	}
}
