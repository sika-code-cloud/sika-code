package com.dq.easy.cloud.model.common.sign.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;

/**
 * 
 * <p>
 * 签名错误码
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午2:40:08
 */
@Component
public class DqSignErrorCode extends DqBaseErrorCode{
	/** HMACSHA256 签名异常 */
	public static final String SIGN_HMACSHA256_EXCEPTION = "SIGN_000001";
	
	static {
		setErrorMsg(SIGN_HMACSHA256_EXCEPTION, "HMACSHA256 签名异常");
	}
}
