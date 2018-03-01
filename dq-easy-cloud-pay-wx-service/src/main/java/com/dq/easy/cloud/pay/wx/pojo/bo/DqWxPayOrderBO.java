package com.dq.easy.cloud.pay.wx.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.DqRequestHeaderKey;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.wx.common.utils.DqWxPayUtils.DqWxOrderNoGenerator;

/**
 * 
 * <p>
 * 微信订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public class DqWxPayOrderBO extends DqPayOrderBO {

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public DqWxPayOrderBO() {

	}

	public DqWxPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		super(dqPayOrderDTO, transactionType);
	}

	public DqWxPayOrderBO buildDqPayOrderDTO(DqPayOrderDTO dqPayOrderDTO) {
		super.dqPayOrderDTO = dqPayOrderDTO;
		return this;
	}

	@Override
	public DqPayOrderBO initMWebPayData(HttpServletRequest request) {
		super.dqPayOrderDTO.setSpbillCreateIp(request.getHeader(DqRequestHeaderKey.X_REAL_IP_KEY));
		// 设置网页地址
		super.dqPayOrderDTO.setWapUrl(request.getRequestURL().toString());
		super.initWapName();
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
		super.dqPayOrderDTO.setOutTradeNo(DqWxOrderNoGenerator.generateWxOrderNO(transactionType));
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
		super.verifyWapUrl();
		super.verifySpbillCreateIp();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyAppPayData() {
		verifyCommonData();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyMicroPayData() {
		verifyCommonData();
		super.verifyAuthCode();
		return this;
	}
}
