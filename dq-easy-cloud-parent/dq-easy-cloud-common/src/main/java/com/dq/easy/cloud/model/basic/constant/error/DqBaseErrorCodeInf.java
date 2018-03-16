package com.dq.easy.cloud.model.basic.constant.error;

/**
 * 
 * <p>
 * 错误代码常量基础接口
 * </p>
 *
 * <pre>
 * 1、所有的服务错误代码枚举都应该实现该接口
 * 2、各个错误代码枚举类需要重写getErrorCode() 和 getErrorMsg()方法
 * </pre>
 * <pre>
  	public enum DqSampleErrorCode implements DqBaseErrorCodeInf {
		HTTP_CONTENT_FORMAT_WRONG("HTTP_000001", "内容格式有误")
		;
	
		private String errorCode;
		private String errorMsg;
	
		private DqSampleErrorCode(String errorCode, String errorMsg) {
			this.errorCode = errorCode;
			this.errorMsg = errorMsg;
		}
	
		@Override
		public String getErrorCode() {
			return errorCode;
		}
	
		@Override
		public String getErrorMsg() {
			return errorMsg;
		}
	
	}
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月26日 上午10:00:50
 */
public interface DqBaseErrorCodeInf {
	/** 获取错误码 */
	String getErrorCode();
	/** 获取错误信息 */
	String getErrorMsg();
}
