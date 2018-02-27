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
