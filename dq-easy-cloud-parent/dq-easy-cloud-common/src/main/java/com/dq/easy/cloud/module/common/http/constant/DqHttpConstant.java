package com.dq.easy.cloud.module.common.http.constant;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant;

/**
 * 
 * <p>
 * http常量类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 上午11:17:31
 */
public class DqHttpConstant extends DqBaseConstant{
	
	/** 方法类型常量 */
	public static enum DqMethodType{
		/** 方法类型为Get */
		GET,
		/** 方法类型为Post */
		POST
	}
	
	/** 请求头的key类 */
	public static class DqRequestHeaderKey {
		public static final String X_REAL_IP_KEY = "X-Real-IP";
	}
	
}
