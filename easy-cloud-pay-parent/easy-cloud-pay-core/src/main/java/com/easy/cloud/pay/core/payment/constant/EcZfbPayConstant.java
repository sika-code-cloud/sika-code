package com.easy.cloud.pay.core.payment.constant;

import com.easy.cloud.pay.core.payment.constant.EcPayConstant;

/**
 * 微信支付常量类
 * @author daiqi
 * @date 2018年2月23日 下午11:41:16
 */
public class EcZfbPayConstant extends EcPayConstant{
	
	/** 支付宝支付键的常量类 */
	public static class EcZfbPayKey extends EcPayKey{
		/** code键名---CODE_KEY---code */
		public static final String CODE_KEY = "code";
		/** msg键名---MSG_KEY---msg */
		public static final String MSG_KEY = "msg";
		/** format键名---FORMAT_KEY---format */
		public static final String FORMAT_KEY = "format";
		/** subject键名---SUBJECT_KEY---subject */
		public static final String SUBJECT_KEY = "subject";
		/** scene键名---SCENE_KEY---scene */
		public final static String SCENE_KEY = "scene";
		/** method的键名---METHOD_KEY---method */
		public static final String METHOD_KEY = "method";
		/** charset的键名---CHARSET_KEY---charset */
		public static final String CHARSET_KEY = "charset";
		/** version的键名---VERSION_KEY---version */
		public static final String VERSION_KEY = "version";
		
		/** biz_content非驼峰键名使用两个下划线隔开---BIZ__CONTENT_KEY---biz_content */
		public final static String BIZ__CONTENT_KEY = "biz_content";
		/** seller_id非驼峰键名使用两个下划线隔开---SELLER__ID_KEY---seller_id */
		public static final String SELLER__ID_KEY = "seller_id";
		/** app_id非驼峰键名使用两个下划线隔开---APP__ID_KEY---app_id */
		public static final String APP__ID_KEY = "app_id";
		/** total_amount非驼峰键名使用两个下划线隔开---TOTAL__AMOUNT_KEY---total_amount */
		public static final String TOTAL__AMOUNT_KEY = "total_amount";
		/** product_code非驼峰键名使用两个下划线隔开---PRODUCT__CODE_KEY---product_code */
		public static final String PRODUCT__CODE_KEY = "product_code";
		/** return_url非驼峰键名使用两个下划线隔开---RETURN__URL_KEY---return_url */
		public static final String RETURN__URL_KEY = "return_url";
		/** notify_id非驼峰键名使用两个下划线隔开---NOTIFY__ID_KEY---notify_id */
		public static final String NOTIFY__ID_KEY = "notify_id";
		/** qr_code非驼峰键名使用两个下划线隔开---QR__CODE_KEY---qr_code */
		public static final String QR__CODE_KEY = "qr_code";
		/** out_request_no非驼峰键名使用两个下划线隔开---OUT__REQUEST__NO_KEY---out_request_no */
		public static final String OUT__REQUEST__NO_KEY = "out_request_no";
		/** refund_amount非驼峰键名使用两个下划线隔开---REFUND__AMOUNT_KEY---refund_amount */
		public static final String REFUND__AMOUNT_KEY = "refund_amount";
		/** out_biz_no非驼峰键名使用两个下划线隔开---OUT__BIZ__NO_KEY---out_biz_no */
		public static final String OUT__BIZ__NO_KEY = "out_biz_no";
		/** payee_type非驼峰键名使用两个下划线隔开---PAYEE__TYPE_KEY---payee_type */
		public static final String PAYEE__TYPE_KEY = "payee_type";
		/** payee_account非驼峰键名使用两个下划线隔开---PAYEE__ACCOUNT_KEY---payee_account */
		public static final String PAYEE__ACCOUNT_KEY = "payee_account";
		/** payer_show_name非驼峰键名使用两个下划线隔开---PAYER__SHOW__NAME_KEY---payer_show_name */
		public static final String PAYER__SHOW__NAME_KEY = "payer_show_name";
		/** payee_real_name非驼峰键名使用两个下划线隔开---PAYEE__REAL__NAME_KEY---payee_real_name */
		public static final String PAYEE__REAL__NAME_KEY = "payee_real_name";
		/** remark键名---REMARK_KEY---remark */
		public static final String REMARK_KEY = "remark";
		/** order_id非驼峰键名使用两个下划线隔开---ORDER__ID_KEY---order_id */
		public static final String ORDER__ID_KEY = "order_id";
		/** trade_no非驼峰键名使用两个下划线隔开---TRADE__NO_KEY---trade_no */
		public static final String TRADE__NO_KEY = "trade_no";
	}
	
	/** 支付宝支付值常量类 */
	public static class EcZfbPayValue extends EcPayValue{
		/** 沙箱sandboxnew目录---SANDBOXNEW---sandboxnew/ */
		public static final String SANDBOXNEW = "sandboxnew/";
		/** CODE_SUCCUSS值---CODE_SUCCUSS---10000 */
		public static final String CODE_SUCCUSS = "10000";
		/** 正式测试环境url---HTTPS_REQ_URL */
		public final static String HTTPS_REQ_URL = "https://openapi.alipay.com/gateway.do";
		/** 沙箱测试环境账号url---DEV_REQ_URL */
		public final static String DEV_REQ_URL = "https://openapi.alipaydev.com/gateway.do";
		/** json的常量值---JSON---json */
		public final static String JSON = "json";
		
	}
	
	/** 支付宝产品代码常量类 */
	public static class EcZfbProductCode {
		/** 支付宝产品代码的常量值---FAST_INSTANT_TRADE_PAY---FAST_INSTANT_TRADE_PAY */
		public final static String FAST_INSTANT_TRADE_PAY = "FAST_INSTANT_TRADE_PAY";
		/** 支付宝产品代码的常量值---QUICK_WAP_PAY---QUICK_WAP_PAY */
		public final static String QUICK_WAP_PAY = "QUICK_WAP_PAY";
		/** 支付宝产品代码的常量值---FACE_TO_FACE_PAYMENT---FACE_TO_FACE_PAYMENT */
		public final static String FACE_TO_FACE_PAYMENT = "FACE_TO_FACE_PAYMENT";
		/** 支付宝产品代码的常量值---QUICK_MSECURITY_PAY---QUICK_MSECURITY_PAY */
		public final static String QUICK_MSECURITY_PAY = "QUICK_MSECURITY_PAY";
	}
}
