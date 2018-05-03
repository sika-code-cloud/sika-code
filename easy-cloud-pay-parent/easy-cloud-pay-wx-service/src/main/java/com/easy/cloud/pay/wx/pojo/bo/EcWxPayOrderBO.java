package com.easy.cloud.pay.wx.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.easy.cloud.core.common.http.constant.EcHttpConstant.EcRequestHeaderKey;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.payment.pojo.bo.EcPayOrderBO;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.wx.common.utils.EcWxPayUtils.EcWxOrderNoGenerator;

/**
 * 
 * <p>
 * 微信订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public class EcWxPayOrderBO extends EcPayOrderBO {

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public EcWxPayOrderBO() {

	}

	public EcWxPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		super(ecPayOrderDTO, transactionType);
	}

	public EcWxPayOrderBO buildEcPayOrderDTO(EcPayOrderDTO ecPayOrderDTO) {
		super.ecPayOrderDTO = ecPayOrderDTO;
		return this;
	}

	@Override
	public EcPayOrderBO initMWebPayData(HttpServletRequest request) {
		super.ecPayOrderDTO.setSpbillCreateIp(request.getHeader(EcRequestHeaderKey.X_REAL_IP_KEY));
		// 设置网页地址
		super.ecPayOrderDTO.setWapUrl(request.getRequestURL().toString());
		super.initWapName();
		return this;
	}

	@Override
	public EcPayOrderBO initMicroPayData(HttpServletRequest request) {
		return this;
	}

	@Override
	protected void initOutTradeNo() {
		if (EcStringUtils.isNotEmpty(super.ecPayOrderDTO.getOutTradeNo())) {
			return;
		}
		EcTransactionType transactionType = getEcPayOrderDTO().getTransactionType();
		super.ecPayOrderDTO.setOutTradeNo(EcWxOrderNoGenerator.generateWxOrderNO(transactionType));
	}
	
	@Override
	public EcPayOrderBO verifyPubPayData() {
		// 校验链
		verifyCommonData();
		super.verifyOpenid();
		return this;
	}
	
	@Override
	public EcPayOrderBO verifyGeneratePayQrCodeData() {
		// 校验链
		verifyCommonData();
		return this;
		
	}
	
	@Override
	public EcPayOrderBO verifyMWebPayData() {
		verifyCommonData();
		super.verifyWapUrl();
		super.verifySpbillCreateIp();
		return this;
	}
	
	@Override
	public EcPayOrderBO verifyAppPayData() {
		verifyCommonData();
		return this;
	}
	
	@Override
	public EcPayOrderBO verifyMicroPayData() {
		verifyCommonData();
		super.verifyAuthCode();
		return this;
	}
}
