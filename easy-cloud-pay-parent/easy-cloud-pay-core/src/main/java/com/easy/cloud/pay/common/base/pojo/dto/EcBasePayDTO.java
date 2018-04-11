package com.easy.cloud.pay.common.base.pojo.dto;

import java.util.Map;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.common.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;

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
 * @author daiqi 创建时间 2018年3月16日 下午6:00:26
 */
public abstract class EcBasePayDTO {
	/** 签名参数容器 */
	private Map<String, Object> signatureParameters;
	/** 内容参数容器 */
	protected Map<String, Object> contentSignatureMap;
	
	public EcBasePayDTO() {
		contentSignatureMap = EcMapUtils.newTreeMap();
	}

	public Map<String, Object> getSignatureParameters() {
		return signatureParameters;
	}
	
	protected void setSignatureParameters(Map<String, Object> signatureParameters) {
		this.signatureParameters = signatureParameters;
	}
	/**
	 * 
	 * <p>
	 * 内容签名数据
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param key
	 * @param value
	 * @author daiqi
	 * 创建时间    2018年3月20日 下午4:13:23
	 */
	protected void putContentSignatureData(String key, Object value) {
		if (EcBaseUtils.isNull(value)) {
			return ;
		}
		if (value instanceof String && EcStringUtils.isEmpty((String)value)) {
			return ;
		}
		contentSignatureMap.put(key, value);
	}
	/**
	 * 
	 * <p>
	 * 构建签名参数
	 * </p>
	 *
	 *
	 * @param EcPayConfigStorage
	 *            : EcPayConfigStorageInf : 支付配置参数对象
	 * @param EcTransactionType
	 *            : EcTransactionType : 交易类型枚举接口
	 * @author daiqi 创建时间 2018年3月20日 下午4:20:54
	 */
	public abstract void buildSignatureParameters(EcPayConfigStorageInf EcPayConfigStorage,
			EcTransactionType EcTransactionType);

	@Override
	public String toString() {
		return "EcBasePayDTO [signatureParameters=" + signatureParameters + "]";
	}

}
