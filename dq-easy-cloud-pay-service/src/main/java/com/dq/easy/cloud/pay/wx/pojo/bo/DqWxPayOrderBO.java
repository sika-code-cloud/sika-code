package com.dq.easy.cloud.pay.wx.pojo.bo;

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
	protected void initOutTradeNo() {
		if (DqStringUtils.isNotEmpty(super.dqPayOrderDTO.getOutTradeNo())) {
			return;
		}
		String outTradeNo = null;
		if (super.getDqPayOrderDTO().getTransactionType() == DqWxTransactionType.JSAPI) {
			outTradeNo = DqOrderNoGenerator.generateWxPayJsapiOrderNO();
		} else if (super.getDqPayOrderDTO().getTransactionType() == DqWxTransactionType.APP) {
			outTradeNo = DqOrderNoGenerator.generateWxPayJsapiOrderNO();
		} else if (super.getDqPayOrderDTO().getTransactionType() == DqWxTransactionType.NATIVE) {
			outTradeNo = DqOrderNoGenerator.generateWxPayQrCodeOrderNO();
		} else{
			
		}
		super.dqPayOrderDTO.setOutTradeNo(outTradeNo);
	}

	public DqPayOrderBO verifyPubPayData() {
		// 校验链
		super.verifyDqPayOrderDTO();
		super.verifyBody();
		super.verifyOutTradeNo();
		super.verifyPrice();
		super.verifyOpenid();
		super.verifySubject();
		super.verifyTransactionType();
		return this;
	}
	
	@Override
	public DqPayOrderBO verifyGeneratePayQrCodeData() {
		// 校验链
		super.verifyDqPayOrderDTO();
		super.verifyBody();
		super.verifyOutTradeNo();
		super.verifyPrice();
		super.verifySubject();
		super.verifyTransactionType();
		return this;
		
	}

	@Override
	public DqPayOrderBO verifyAppPayData() {
		return null;
	}

}
