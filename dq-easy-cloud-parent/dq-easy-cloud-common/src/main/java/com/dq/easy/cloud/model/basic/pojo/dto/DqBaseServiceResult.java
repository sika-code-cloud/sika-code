package com.dq.easy.cloud.model.basic.pojo.dto;

import java.util.List;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCode;
import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.dto.DqBaseServiceResultException;

/**
 * 
 * <p>
 * 服务返回结果基础类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月2日 下午3:57:33
 */
public class DqBaseServiceResult{

	private String errorCode; // 错误代码
	private String errorMsg; // 错误信息
	private Object result; // 返回结果
	private DqBaseErrorCodeInf dqBaseErrorCodeInf; // 错误代码接口
	
	private DqBaseServiceResultException serviceResultException; // 服务异常结果

	public static DqBaseServiceResult newInstance(){
		return new DqBaseServiceResult();
	}
	public static DqBaseServiceResult newInstance(DqBaseErrorCodeInf dqBaseErrorCodeInf){
		return new DqBaseServiceResult().buildDqBaseErrorCodeInf(dqBaseErrorCodeInf);
	}
	/**
	 * <p>
	 * 创建异常信息的serviceResult对象
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:42:04
	 */
	public static DqBaseServiceResult newInstanceOfException(DqBaseServiceResultException serviceResultException){
		return newInstance().buildServiceResultException(serviceResultException);
	}
	/**
	 * <p>
	 * 创建成功状态的serviceResult对象
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:42:04
	 */
	public static DqBaseServiceResult newInstanceOfSuccess(){
		DqBaseServiceResult dqBaseServiceResult = newInstance();
		return dqBaseServiceResult.buildErrorCode(DqBaseErrorCode.SUCCESS);
	}
	/**
	 * <p>
	 * 创建携带错误代码的serviceResult对象
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:42:04
	 */
	public static DqBaseServiceResult newInstanceOfError(DqBaseErrorCodeInf dqBaseErrorCodeInf){
		return newInstance(dqBaseErrorCodeInf);
	}
	/**
	 * <p>
	 * 创建成功状态的带有响应结果的serviceResult对象
	 * </p>
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:42:04
	 */
	public static DqBaseServiceResult newInstanceOfSucResult(Object result){
		return newInstanceOfSuccess().buildResult(result);
	}
	/**
	 * <p>
	 * 创建带错误代码和响应结果的DqBaseServiceResult实例
	 * </p>
	 * @param errorCode
	 * @param result
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:43:20
	 */
	public static DqBaseServiceResult newInstance(DqBaseErrorCodeInf dqBaseErrorCodeInf, Object result){
		return newInstance(dqBaseErrorCodeInf).buildResult(result);
	}
	
	/**
	 * 
	 * <p>
	 * 根据泛型class获取result对应的泛型对象，若result为list类型将抛出异常
	 * </p>
	 *
	 * @param clazz
	 * @return T
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:33:23
	 */
	public <T> T getTObj(Class<T> clazz){
		return DqJSONUtils.parseObject(this.result, clazz);
	}
	
	/**
	 * 
	 * <p>
	 * 如果result为list则可以将其转换为泛型对应的列表，否则将抛出异常
	 * </p>
	 *
	 * @param clazz : Class<T> : 泛型class
	 * @return List
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:36:05
	 */
	public <T> List<T> getTList(Class<T> clazz){
		return DqJSONUtils.parseArray(this.result, clazz);
	}
	/** 构建errorMsg */
	public DqBaseServiceResult buildDqBaseErrorCodeInf(DqBaseErrorCodeInf dqBaseErrorCodeInf) {
		this.dqBaseErrorCodeInf = dqBaseErrorCodeInf;
		this.errorCode = dqBaseErrorCodeInf.getErrorCode();
		this.errorMsg = dqBaseErrorCodeInf.getErrorMsg();
		return this;
	}
	
	/** 构建errorCode */
	public DqBaseServiceResult buildErrorCode(DqBaseErrorCodeInf dqBaseErrorCodeInf) {
		return buildDqBaseErrorCodeInf(dqBaseErrorCodeInf);
	}
	
	/** 构建errorCode和errorMsg */
	public DqBaseServiceResult buildErrorCodeAndMsg(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		return this;
	}
	
	
	/** 构建result */
	public DqBaseServiceResult buildResult(Object result) {
		this.result = result;
		return this;
	}
	
	/**构建serviceResultException*/
	public DqBaseServiceResult buildServiceResultException(DqBaseServiceResultException serviceResultException) {
		this.serviceResultException = serviceResultException;
		return this;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		if(DqStringUtils.isEmpty(this.errorMsg) && DqBaseUtils.isNotNull(dqBaseErrorCodeInf)) {
			this.errorMsg = dqBaseErrorCodeInf.getErrorMsg(); 
		}
		return errorMsg;
	}

	public DqBaseServiceResultException getServiceResultException() {
		return serviceResultException;
	}

	public void setServiceResultException(DqBaseServiceResultException serviceResultException) {
		this.serviceResultException = serviceResultException;
	}

}
