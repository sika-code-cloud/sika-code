package com.dq.easy.cloud.model.common.response.common;

/**
 * 基础响应枚举类--建议对于公共的code和msg使用枚举类进行封装
 * @author daiqi
 * 创建日期 2018年1月6日 下午11:45:16
 */
public enum DqResponseEnum {
	SUCCESS("1","success");
	
	private String code;
	private String msg;
	
	private DqResponseEnum(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
