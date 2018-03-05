package com.dq.easy.cloud.model.common.http.constant;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant;

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
		GET, POST
	}
	
	/** 请求头的key类 */
	public static class DqRequestHeaderKey {
		public static final String X_REAL_IP_KEY = "X-Real-IP";
	}
	
}
