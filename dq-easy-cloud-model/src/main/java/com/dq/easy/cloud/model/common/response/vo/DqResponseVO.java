package com.dq.easy.cloud.model.common.response.vo;

import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.response.common.DqResponseEnum;

/**
 * 基础响应视图对象
 * @author daiqi
 * 创建日期 2018年1月6日 下午11:23:29
 */
public class DqResponseVO {
//	返回code
	private String code;
//	返回的msg
	private String msg;
//	返回的数据
	private Object info;
	
	/**
	 * 
	 * <p>创建SUCCESS枚举状态的DqBaseResponseVO对象</p>
	 *
	 * <pre></pre>
	 *
	 * @param info
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月7日 上午3:08:52
	 */
	public static DqResponseVO newInstance(Object info){
		return newInstance(DqResponseEnum.SUCCESS.getCode(), DqResponseEnum.SUCCESS.getMsg(), info);
	}
	
	/**
	 * <p>创建DqBaseResponseVO对象</p>
	 *
	 * <pre></pre>
	 *
	 * @param code : String : 传入的code码
	 * @param msg : String : 传入的msg
	 * @param info : Object : 传入的obj对象
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月7日 上午3:10:04
	 */
	public static DqResponseVO newInstance(String code, String msg, Object info){
		return new DqResponseVO().buildCode(code).buildMsg(msg).buildInfo(info);
	}
	
	public DqResponseVO buildCode(String code){
		this.code = code;
		return this;
	}
	
	public DqResponseVO buildMsg(String msg){
		this.msg = msg;
		return this;
	}
	
	public DqResponseVO buildInfo(Object info){
		this.info = info;
		return this;
	}
	
	public DqResponseVO() {
		super();
	}
	/**
	 * 
	 * <p></p>
	 *
	 * <pre></pre>
	 *
	 * @param code
	 * @param msg
	 * @param info
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月7日 上午3:20:01
	 */
	public static String toString(String code, String msg, Object info){
		return newInstance(code, msg, info).toString();
	}
	public static String toString(Object info){
		return newInstance(info).toString();
	}
	@Override
	public String toString() {
		return DqJSONUtils.parseObject(this, String.class);
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
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	
}
