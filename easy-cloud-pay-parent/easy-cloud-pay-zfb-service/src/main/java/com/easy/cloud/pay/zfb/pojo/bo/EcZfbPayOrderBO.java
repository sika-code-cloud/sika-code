package com.easy.cloud.pay.zfb.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.common.payment.pojo.bo.EcPayOrderBO;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.zfb.common.utils.EcZfbPayUtils.EcZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public class EcZfbPayOrderBO extends EcPayOrderBO {

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public EcZfbPayOrderBO() {

	}

	public EcZfbPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		super(ecPayOrderDTO, transactionType);
	}

	public EcZfbPayOrderBO buildEcPayOrderDTO(EcPayOrderDTO ecPayOrderDTO) {
		super.ecPayOrderDTO = ecPayOrderDTO;
		return this;
	}

	@Override
	public EcPayOrderBO initMWebPayData(HttpServletRequest request) {
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
		super.ecPayOrderDTO.setOutTradeNo(EcZfbOrderNoGenerator.generateZfbOrderNO(transactionType));
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
		return this;
	}
	
	@Override
	public EcPayOrderBO verifyAppPayData() {
		verifyCommonData();
		return this;
	}
	
	@Override
	public EcPayOrderBO verifyMicroPayData() {
		super.verifyCommonData();
		super.verifyAuthCode();
		return this;
	}
}
