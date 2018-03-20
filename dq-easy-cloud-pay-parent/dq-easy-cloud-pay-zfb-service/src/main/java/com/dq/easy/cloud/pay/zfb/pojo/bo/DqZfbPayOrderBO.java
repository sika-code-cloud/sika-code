package com.dq.easy.cloud.pay.zfb.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.zfb.common.utils.DqZfbPayUtils.DqZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public class DqZfbPayOrderBO extends DqPayOrderBO {

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public DqZfbPayOrderBO() {

	}

	public DqZfbPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		super(dqPayOrderDTO, transactionType);
	}

	public DqZfbPayOrderBO buildDqPayOrderDTO(DqPayOrderDTO dqPayOrderDTO) {
		super.dqPayOrderDTO = dqPayOrderDTO;
		return this;
	}

	@Override
	public DqPayOrderBO initMWebPayData(HttpServletRequest request) {
		return this;
	}

	@Override
	public DqPayOrderBO initMicroPayData(HttpServletRequest request) {
		return this;
	}

	@Override
	protected void initOutTradeNo() {
		if (DqStringUtils.isNotEmpty(super.dqPayOrderDTO.getOutTradeNo())) {
			return;
		}
		DqTransactionType transactionType = getDqPayOrderDTO().getTransactionType();
		super.dqPayOrderDTO.setOutTradeNo(DqZfbOrderNoGenerator.generateZfbOrderNO(transactionType));
	}
	
	@Override
	public DqPayOrderBO verifyPubPayData() {
		// 校验链
		verifyCommonData();
		super.verifyOpenid();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyGeneratePayQrCodeData() {
		// 校验链
		verifyCommonData();
		return this;
		
	}
	
	@Override
	public DqPayOrderBO verifyMWebPayData() {
		verifyCommonData();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyAppPayData() {
		verifyCommonData();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyMicroPayData() {
		super.verifyCommonData();
		super.verifyAuthCode();
		return this;
	}
}
