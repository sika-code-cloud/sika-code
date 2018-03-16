package com.dq.easy.cloud.pay.model.payment.constant;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant;

/**
 * 支付常量类
 * @author daiqi
 * @date 2018年2月23日 下午11:31:27
 */
public class DqPayConstant extends DqBaseConstant{
	
	/** 支付键类 */
	public static class DqPayKey extends DqPayConstant{
		/** appid的键名---APPID_KEY---appid */
		public static final String APPID_KEY = "appid";
		/** mch_id非驼峰键名使用两个下划线隔开---MCH__ID_KEY---mch_id */
		public static final String MCH__ID_KEY = "mch_id";
		/** nonce_str非驼峰键名使用两个下划线隔开---NONCE__STR_KEY---nonce_str */
		public static final String NONCE__STR_KEY = "nonce_str";
		/** body的键名---BODY_KEY---body */
		public static final String BODY_KEY = "body";
		/** sign的键名---SIGN_KEY---sign */
		public static final String SIGN_KEY = "sign";
		/** timestamp键名---TIMESTAMP_KEY---timestamp */
		public static final String TIMESTAMP_KEY = "timestamp";
		/** amount键名---AMOUNT_KEY---amount */
		public static final String AMOUNT_KEY = "amount";
		
		/** notify_url非驼峰键名使用两个下划线隔开---NOTIFY__URL_KEY---notify_url */
		public static final String NOTIFY__URL_KEY = "notify_url";
		/** sign_type非驼峰键名使用两个下划线隔开---SIGN__TYPE_KEY---sign_type */
		public static final String SIGN__TYPE_KEY = "sign_type";
		/** out_trade_no非驼峰键名使用两个下划线隔开---OUT__TRADE__NO_KEY---out_trade_no */
		public static final String OUT__TRADE__NO_KEY = "out_trade_no";
		/** bill_type非驼峰键名使用两个下划线隔开---BILL__TYPE_KEY---bill_type */
		public static final String BILL__TYPE_KEY = "bill_type";
		/** bill_date非驼峰键名使用两个下划线隔开---BILL__DATE_KEY---bill_date */
		public static final String BILL__DATE_KEY = "bill_date";
		/** auth_code非驼峰键名使用两个下划线隔开---AUTH__CODE_KEY---auth_code */
		public static final String AUTH__CODE_KEY = "auth_code";
	}
	
	/** 支付值类 */
	public static class DqPayValue extends DqPayConstant {
		public static final String WAP_NAME_DEFAULT = "在线支付";
		/** 失败代码---FAIL_CODE---fail */
		public static final String FAIL_CODE = "fail";
		/** 失败描述---FAIL_DESC---失败 */
		public static final String FAIL_DESC = "失败";
		/** 成功代码---FAIL_CODE---success */
		public static final String SUCCESS_CODE = "success";
		/** 成功描述---SUCCESS_DESC---成功 */
		public static final String SUCCESS_DESC = "成功";
	}
}
