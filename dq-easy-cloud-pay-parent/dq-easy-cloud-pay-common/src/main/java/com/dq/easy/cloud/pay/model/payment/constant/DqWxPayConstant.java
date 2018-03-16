package com.dq.easy.cloud.pay.model.payment.constant;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayConstant;

/**
 * 微信支付常量类
 * @author daiqi
 * @date 2018年2月23日 下午11:41:16
 */
public class DqWxPayConstant extends DqPayConstant{
	
	/** 微信支付键的常量类 */
	public static class DqWxPayKey extends DqPayKey{
		/** appId的键名---APP_ID_KEY---appId */
		public static final String APP_ID_KEY = "appId";
		/** total_fee的键名---ATTACH_KEY---attach */
		public static final String ATTACH_KEY = "attach";
		/** signType的键名---SIGN_TYPE_KEY---signType */
		public static final String SIGN_TYPE_KEY = "signType";
		/** timeStamp的键名---TIME_STAMP_KEY---timeStamp */
		public static final String TIME_STAMP_KEY = "timeStamp";
		/** package的键名---PACKAGE_KEY---package */
		public static final String PACKAGE_KEY = "package";
		/** partnerid键名---PARTNERID_KEY---partnerid */
		public static final String PARTNERID_KEY = "partnerid";
		/** prepayid键名---PREPAYID_KEY---prepayid */
		public static final String PREPAYID_KEY = "prepayid";
		/** data键名---DATA_KEY---data */
		public static final String DATA_KEY = "data";
		/** openid键名---OPENID_KEY---openid */
		public static final String OPENID_KEY = "openid";
		/** type键名---TYPE_KEY---type */
		public static final String TYPE_KEY = "type";
		/** desc键名---DESC_KEY---desc */
		public static final String DESC_KEY = "desc";
		/** nonceStr键名---NONCE_STR_KEY---nonceStr */
		public static final String NONCE_STR_KEY = "nonceStr";
		/** noncestr键名---NONCESTR_KEY---noncestr */
		public static final String NONCESTR_KEY = "noncestr";
		
		/** trade_type非驼峰键名使用两个下划线隔开---TRADE__TYPE_KEY---trade_type */
		public static final String TRADE__TYPE_KEY = "trade_type";
		/** spbill_create_ip非驼峰键名使用两个下划线隔开---SPBILL__CREATE__IP_KEY---spbill_create_ip */
		public static final String SPBILL__CREATE__IP_KEY = "spbill_create_ip";
		/** return_code非驼峰键名使用两个下划线隔开---RETURN__CODE_KEY---return_code */
		public static final String RETURN__CODE_KEY = "return_code";
		/** return_msg非驼峰键名使用两个下划线隔开---RETURN__MSG_KEY---return_msg */
		public static final String RETURN__MSG_KEY = "return_msg";
		/** result_code非驼峰键名使用两个下划线隔开---RESULT__CODE_KEY---result_code */
		public static final String RESULT__CODE_KEY = "result_code";
		/** err_code非驼峰键名使用两个下划线隔开---ERR__CODE_KEY---err_code */
		public static final String ERR__CODE_KEY = "err_code";
		/** result_msg非驼峰键名使用两个下划线隔开---RESULT__MSG_KEY---result_msg */
		public static final String RESULT__MSG_KEY = "result_msg";
		/** prepay_id非驼峰键名使用两个下划线隔开---PREPAY__ID_KEY---prepay_id */
		public static final String PREPAY__ID_KEY = "prepay_id";
		/** code_url非驼峰键名使用两个下划线隔开---CODE__URL_KEY---code_url */
		public static final String CODE__URL_KEY = "code_url";
		/** transaction_id非驼峰键名使用两个下划线隔开---TRANSACTION__ID_KEY---transaction_id */
		public static final String TRANSACTION__ID_KEY = "transaction_id";
		/** out_refund_no非驼峰键名使用两个下划线隔开---OUT__REFUND__NO_KEY---out_refund_no */
		public static final String OUT__REFUND__NO_KEY = "out_refund_no";
		/** total_fee非驼峰键名使用两个下划线隔开---TOTAL__FEE_KEY---total_fee */
		public static final String TOTAL__FEE_KEY = "total_fee";
		/** refund_fee非驼峰键名使用两个下划线隔开---REFUND__FEE_KEY---refund_fee */
		public static final String REFUND__FEE_KEY = "refund_fee";
		/** op_user_id非驼峰键名使用两个下划线隔开---OP__USER__ID_KEY---op_user_id */
		public static final String OP__USER__ID_KEY = "op_user_id";
		/** wap_url非驼峰键名使用两个下划线隔开---WAP__URL_KEY---wap_url */
		public static final String WAP__URL_KEY = "wap_url";
		/** wap_name非驼峰键名使用两个下划线隔开---WAP__NAME_KEY---wap_name */
		public static final String WAP__NAME_KEY = "wap_name";
		/** h5_info非驼峰键名使用两个下划线隔开---H5__INFO_KEY---h5_info */
		public static final String H5__INFO_KEY = "h5_info";
		/** scene_info非驼峰键名使用两个下划线隔开---SCENE__INFO_KEY---scene_info */
		public static final String SCENE__INFO_KEY = "scene_info";
		/** product_id非驼峰键名使用两个下划线隔开---PRODUCT__ID_KEY---product_id */
		public static final String PRODUCT__ID_KEY = "product_id";
		/** partner_trade_no非驼峰键名使用两个下划线隔开---PARTNER__TRADE__NO_KEY---partner_trade_no */
		public static final String PARTNER__TRADE__NO_KEY = "partner_trade_no";
		/** enc_bank_no非驼峰键名使用两个下划线隔开---ENC__BANK__NO_KEY---enc_bank_no */
		public static final String ENC__BANK__NO_KEY = "enc_bank_no";
		/** enc_true_name非驼峰键名使用两个下划线隔开---ENC__TRUE__NAME_KEY---enc_true_name */
		public static final String ENC__TRUE__NAME_KEY = "enc_true_name";
		/** bank_code非驼峰键名使用两个下划线隔开---BANK__CODE_KEY---bank_code */
		public static final String BANK__CODE_KEY = "bank_code";
		/** mweb_url非驼峰键名使用两个下划线隔开---MWEB__URL_KEY---mweb_url */
		public static final String MWEB__URL_KEY = "mweb_url";
		
		
	}
	
	/** 微信支付值常量类 */
	public static class DqWxPayValue extends DqPayValue{
		/** 沙箱sandboxnew目录---SANDBOXNEW---sandboxnew/ */
		public static final String SANDBOXNEW = "sandboxnew/";
		/** SUCCESS值---SUCCESS---SUCCESS */
		public static final String SUCCESS = "SUCCESS";
		/** ok值---OK---ok */
		public static final String OK = "ok";
		/** spbill_create_ip默认值---SPBILL_CREATE_IP_DEFAULT---192.168.1.1 */
		public static final String SPBILL_CREATE_IP_DEFAULT = "192.168.1.1";
		/** 微信请求地址---SPBILL_CREATE_IP_DEFAULT---https://api.mch.weixin.qq.com/ */
		public final static String URI = "https://api.mch.weixin.qq.com/";
		/** app的包值---APP_PACKAGE_VALUE---Sign=WXPay */
		public final static String APP_PACKAGE_VALUE = "Sign=WXPay";
		
		/** 判断statusCode是否为SUCCESS---与大写得SUCCESS比较 */
		public static boolean isSUCCESS(Object statusCode){
			return DqBaseUtils.equals(DqWxPayValue.SUCCESS, statusCode);
		}
		/** 判断statusCode是否不为SUCCESS---与大写得SUCCESS比较 */
		public static boolean isNotSUCCESS(Object statusCode){
			return !isSUCCESS(statusCode);
		}
		
	}
	
}
