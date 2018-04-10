package com.easy.cloud.core.exception.dto;

import java.util.Date;

import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;

/**
 * 
 * <p>
 * 基础服务异常结果类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午7:24:48
 */
public class EcBaseServiceResultException {
	private Date timstamp;
	private String exception;
	private String path;
	private String failureDetails;
	
	public static EcBaseServiceResultException newInstance(){
		return new EcBaseServiceResultException();
	}
	
	public String getTimestampStr(){
		return EcDateFormatUtils.format(timstamp, EcDateFormatUtils.FORMAT_NORMAL);
	}
	
	public Date getTimstamp() {
		return timstamp;
	}
	public void setTimstamp(Date timstamp) {
		this.timstamp = timstamp;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFailureDetails() {
		return failureDetails;
	}
	public void setFailureDetails(String failureDetails) {
		this.failureDetails = failureDetails;
	}
	
}
