package com.dq.easy.cloud.pay.model.base.pojo.dto;

import java.util.Map;

import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.model.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;

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
public abstract class DqBasePayDTO {
	/** 签名参数容器 */
	private Map<String, Object> signatureParameters;
	
	private String sign;
	
	public DqBasePayDTO() {
		initSignatureParameters();
	}
	
	public Map<String, Object> getSignatureParameters() {
		return signatureParameters;
	}
	
	/** 压入公共的签名参数 */
	public abstract void putCommonSignData(DqPayConfigStorageInf dqPayConfigStorage);
	
	/** 压入sign签名参数 */
	public abstract void putSignSignData();
	
	/**
	 * 
	 * <p>将签名数据put到签名参数容器中</p>
	 *
	 * @param key : String : 参数key
	 * @param value : Object : 参数值
	 *
	 * author daiqi
	 * 创建时间  2018年3月18日 上午1:37:26
	 */
	protected void putSignatureData(String key, Object value) {
		signatureParameters.put(key, value);
	}
	private void initSignatureParameters() {
		signatureParameters = DqMapUtils.newTreeMap();
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
		putSignSignData();
	}
	
	public void buildSign(DqPayConfigStorageInf dqPayConfigStorage){
		String needSignParamsStr = DqSignUtils.parameterText(getSignatureParameters(), DqSymbol.SINGLE_AND, DqZfbPayKey.SIGN_KEY);
		String sign = DqSignUtils.valueOf(dqPayConfigStorage.getSignType()).createSign(needSignParamsStr, dqPayConfigStorage.getKeyPrivate(),
				dqPayConfigStorage.getInputCharset());
		setSign(sign);
	}

	@Override
	public String toString() {
		return "DqBasePayDTO [signatureParameters=" + signatureParameters + "]";
	}
	
}
