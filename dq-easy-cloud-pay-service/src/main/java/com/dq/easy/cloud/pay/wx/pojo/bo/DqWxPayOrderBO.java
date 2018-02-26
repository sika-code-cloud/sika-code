package com.dq.easy.cloud.pay.wx.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.RequestHeaderKey;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.base.utils.DqPayUtils.DqOrderNoGenerator;
import com.dq.easy.cloud.pay.model.payment.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

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
		super.dqPayOrderDTO.setSpbillCreateIp(request.getHeader(RequestHeaderKey.X_REAL_IP_KEY));
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
		String outTradeNo = null;
		DqTransactionType transactionType = getDqPayOrderDTO().getTransactionType();
		if (DqWxTransactionType.isJSAPI(transactionType)) {
			outTradeNo = DqOrderNoGenerator.generateWxJsapiPayOrderNO();
		} else if (DqWxTransactionType.isAPP(transactionType)) {
			outTradeNo = DqOrderNoGenerator.generateWxJsapiPayOrderNO();
		} else if (DqWxTransactionType.isNATIVE(transactionType)) {
			outTradeNo = DqOrderNoGenerator.generateWxQrCodePayOrderNO();
		} else if (DqWxTransactionType.isMWEB(transactionType)) {
			outTradeNo = DqOrderNoGenerator.generateWxMWebPayOrderNO();
		} else{
			
		}
		super.dqPayOrderDTO.setOutTradeNo(outTradeNo);
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

	private DqPayOrderBO verifyCommonData() {
		// 校验链
		super.verifyDqPayOrderDTO();
		super.verifyBody();
		super.verifyOutTradeNo();
		super.verifyPrice();
		super.verifySubject();
		super.verifyTransactionType();
		return this;
	}
}
