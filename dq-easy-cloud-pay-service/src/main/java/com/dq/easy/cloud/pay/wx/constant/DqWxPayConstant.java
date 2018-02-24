package com.dq.easy.cloud.pay.wx.constant;

import com.dq.easy.cloud.pay.model.base.constant.DqPayConstant;

/**
 * 微信支付常量类
 * @author daiqi
 * @date 2018年2月23日 下午11:41:16
 */
public class DqWxPayConstant extends DqPayConstant{
	/** 微信支付键的类 */
	public static class DqWxPayKey extends DqPayKey{
		/** body的键名---BODY_KEY---body */
		public static final String BODY_KEY = "body";
		/** out_trade_no的键名---OUT_TRADE_NO_KEY---out_trade_no */
		public static final String OUT_TRADE_NO_KEY = "out_trade_no";
		/** spbill_create_ip的键名---SPBILL_CREATE_IP_KEY---spbill_create_ip */
		public static final String SPBILL_CREATE_IP_KEY = "spbill_create_ip";
		/** spbill_create_ip的键名---TOTAL_FEE_KEY---total_fee */
		public static final String TOTAL_FEE_KEY = "total_fee";
		/** total_fee的键名---ATTACH_KEY---attach */
		public static final String ATTACH_KEY = "attach";
		/** notify_url的键名---NOTIFY_URL_KEY---notify_url */
		public static final String NOTIFY_URL_KEY = "notify_url";
		/** trade_type的键名---TRADE_TYPE_KEY---trade_type */
		public static final String TRADE_TYPE_KEY = "trade_type";
		
	}
}
