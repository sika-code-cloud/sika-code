package com.easy.cloud.core.common.http.constant;

import com.easy.cloud.core.basic.constant.EcBaseConstant;

/**
 * 
 * <p>
 * http常量类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 上午11:17:31
 */
public class EcHttpConstant extends EcBaseConstant{
	
	/** 方法类型常量 */
	public static enum EcMethodType{
		/** 方法类型为Get */
		GET,
		/** 方法类型为Post */
		POST
	}
	
	/** 请求头的key类 */
	public static class EcRequestHeaderKey {
		public static final String X_REAL_IP_KEY = "X-Real-IP";
	}
	
}
