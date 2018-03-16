package com.dq.easy.cloud.pay.model.base.pojo.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>
 * 基础支付数据传输对象
 * </p>
 *
 * <pre>
 *  说明：提供签名参数容器对象，可以封装需要进行签名的内容
 *  约定：所有与支付相关的数据传输对象都应该直接或间接继承此类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月16日 下午6:00:26
 */
public class DqBasePayDTO {
//	签名参数容器
	protected Map<String, Object> signatureParameters = new HashMap<>();

	public Map<String, Object> getSignatureParameters() {
		return signatureParameters;
	}
	
	
}
