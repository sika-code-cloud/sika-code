package com.dq.easy.cloud.model.common.http.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;

/**
 * 
 * <p>
 * http错误码
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 上午11:21:15
 */
@Component
public class DqHttpErrorCode extends DqBaseErrorCode{
	/** 内容格式有误 */
	public static final String HTTP_CONTENT_FORMAT_WRONG = "HTTP_000001";
	
	static {
		setErrorMsg(HTTP_CONTENT_FORMAT_WRONG, "内容格式有误");
	}
	
}
