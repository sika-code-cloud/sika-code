package com.dq.easy.cloud.pay.model.base.pojo.dto;

import java.util.Map;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

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
public abstract class DqBasePayDTO {
	/** 签名参数容器 */
	private Map<String, Object> signatureParameters;
	/** 签名sign */
	private String sign;

	public DqBasePayDTO() {
		signatureParameters = DqMapUtils.newTreeMap();
	}

	public Map<String, Object> getSignatureParameters() {
		return signatureParameters;
	}

	/**
	 * 
	 * <p>
	 * 构建签名参数
	 * </p>
	 *
	 *
	 * @param dqPayConfigStorage
	 *            : DqPayConfigStorageInf : 支付配置参数对象
	 * @param dqTransactionType
	 *            : DqTransactionType : 交易类型枚举接口
	 * @author daiqi 创建时间 2018年3月20日 下午4:20:54
	 */
	public abstract void buildSignParamters(DqPayConfigStorageInf dqPayConfigStorage,
			DqTransactionType dqTransactionType);

	/** 压入sign签名参数 */
	protected abstract void putSignSignData();

	public String getSign() {
		return sign;
	}

	protected void setSign(String sign) {
		this.sign = sign;
		putSignSignData();
	}

	/**
	 * 
	 * <p>
	 * 构建sign参数
	 * </p>
	 *
	 * @param dqPayConfigStorage
	 * @author daiqi 创建时间 2018年3月20日 下午4:22:51
	 */
	protected abstract void buildSign(DqPayConfigStorageInf dqPayConfigStorage);

	/**
	 * 
	 * <p>
	 * 将签名数据put到签名参数容器中
	 * </p>
	 *
	 * @param key : String : 参数key
	 * @param value : Object : 参数值
	 *
	 * author daiqi 创建时间 2018年3月18日 上午1:37:26
	 */
	protected void putSignatureData(String key, Object value) {
		if (DqBaseUtils.isNotNull(value)) {
			signatureParameters.put(key, value);
		}
	}

	@Override
	public String toString() {
		return "DqBasePayDTO [signatureParameters=" + signatureParameters + "]";
	}

}
