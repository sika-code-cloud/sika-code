package com.dq.easy.cloud.model.common.response.vo;

import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.response.common.DqResponseEnum;

/**
 * 基础响应视图对象
 * <p>code和msg建议使用枚举类来进行赋值，相关业务逻辑的响应枚举类可以参考DqResponseEnum</p>
 * <p>使用该方法若只是需要对象通过调用newInstance来创建实例</p>
 * <p>若需要生成json字符串，请使用toString方法生成</p>
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
	 * <p>将响应对象直接转变成json字符串</p>
	 *
	 * <pre></pre>
	 *
	 * @param code : String : 响应码
	 * @param msg : String : 响应信息
	 * @param info : Object : 封装的响应对象
	 * @return 转化为json字符串的DqResponseVO对象
	 *
	 * author daiqi
	 * 创建时间  2018年1月7日 上午3:20:01
	 */
	public static String toString(String code, String msg, Object info){
		return newInstance(code, msg, info).toString();
	}
	
	/**
	 * 
	 * <p>将响应对象直接转变成json字符串</p>
	 *
	 * <pre></pre>
	 *
	 * @param info
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月7日 下午12:18:44
	 */
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
