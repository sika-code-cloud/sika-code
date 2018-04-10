package com.easy.cloud.core.basic.pojo.dto;

import java.util.List;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.dto.EcBaseServiceResultException;

/**
 * 
 * <p>
 * 服务返回结果基础类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月2日 下午3:57:33
 */
public class EcBaseServiceResult{
	/** 错误代码 */
	private String errorCode;
	/** 错误信息 */
	private String errorMsg;
	/** 返回结果 */
	private Object result; 
	/** 错误代码接口 */
	private EcBaseErrorCodeInf ecBaseErrorCodeInf; 
	/** 服务异常结果 */
	private EcBaseServiceResultException serviceResultException; 

	public static EcBaseServiceResult newInstance(){
		return new EcBaseServiceResult();
	}
	public static EcBaseServiceResult newInstance(EcBaseErrorCodeInf ecBaseErrorCodeInf){
		return new EcBaseServiceResult().buildDqBaseErrorCodeInf(ecBaseErrorCodeInf);
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
	public static EcBaseServiceResult newInstanceOfException(EcBaseServiceResultException serviceResultException){
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
	public static EcBaseServiceResult newInstanceOfSuccess(){
		EcBaseServiceResult ecBaseServiceResult = newInstance();
		return ecBaseServiceResult.buildErrorCode(EcBaseErrorCodeEnum.SUCCESS);
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
	public static EcBaseServiceResult newInstanceOfError(EcBaseErrorCodeInf ecBaseErrorCodeInf){
		return newInstance(ecBaseErrorCodeInf);
	}
	/**
	 * <p>
	 * 创建成功状态的带有响应结果的serviceResult对象
	 * </p>
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:42:04
	 */
	public static EcBaseServiceResult newInstanceOfSucResult(Object result){
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
	public static EcBaseServiceResult newInstance(EcBaseErrorCodeInf ecBaseErrorCodeInf, Object result){
		return newInstance(ecBaseErrorCodeInf).buildResult(result);
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
		return EcJSONUtils.parseObject(this.result, clazz);
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
		return EcJSONUtils.parseArray(this.result, clazz);
	}
	/** 构建errorMsg */
	public EcBaseServiceResult buildDqBaseErrorCodeInf(EcBaseErrorCodeInf ecBaseErrorCodeInf) {
		this.ecBaseErrorCodeInf = ecBaseErrorCodeInf;
		this.errorCode = ecBaseErrorCodeInf.getErrorCode();
		this.errorMsg = ecBaseErrorCodeInf.getErrorMsg();
		return this;
	}
	
	/** 构建errorCode */
	public EcBaseServiceResult buildErrorCode(EcBaseErrorCodeInf ecBaseErrorCodeInf) {
		return buildDqBaseErrorCodeInf(ecBaseErrorCodeInf);
	}
	
	/** 构建errorCode和errorMsg */
	public EcBaseServiceResult buildErrorCodeAndMsg(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		return this;
	}
	
	
	/** 构建result */
	public EcBaseServiceResult buildResult(Object result) {
		this.result = result;
		return this;
	}
	
	/**构建serviceResultException*/
	public EcBaseServiceResult buildServiceResultException(EcBaseServiceResultException serviceResultException) {
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
		if(EcStringUtils.isEmpty(this.errorMsg) && EcBaseUtils.isNotNull(ecBaseErrorCodeInf)) {
			this.errorMsg = ecBaseErrorCodeInf.getErrorMsg(); 
		}
		return errorMsg;
	}

	public EcBaseServiceResultException getServiceResultException() {
		return serviceResultException;
	}

	public void setServiceResultException(EcBaseServiceResultException serviceResultException) {
		this.serviceResultException = serviceResultException;
	}

}
