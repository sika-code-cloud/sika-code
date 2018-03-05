package com.dq.easy.cloud.model.exception.dto;

import java.util.Date;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;

/**
 * 
 * <p>
 * 基础服务异常结果类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午7:24:48
 */
public class DqBaseServiceResultException {
	private Date timstamp;
	private String exception;
	private String path;
	private String failureDetails;
	
	public static DqBaseServiceResultException newInstance(){
		return new DqBaseServiceResultException();
	}
	
	public String getTimestampStr(){
		return DqDateFormatUtils.format(timstamp, DqDateFormatUtils.FORMAT_NORMAL);
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
