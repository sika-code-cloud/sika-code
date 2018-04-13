package com.easy.cloud.pay.wx.pojo.bo;

import com.easy.cloud.pay.common.payment.pojo.bo.EcRefundOrderBO;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.wx.common.utils.EcWxPayUtils.EcWxOrderNoGenerator;

/**
 * 
 * <p>
 * 微信退款订单业务逻辑对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月1日 下午4:09:31
 */
public class EcWxRefundOrderBO extends EcRefundOrderBO{

	public EcWxRefundOrderBO(EcRefundOrderAbstractDTO ecRefundOrderDTO) {
		super(ecRefundOrderDTO);
	}

	@Override
	public EcRefundOrderBO verifyRefundData() {
		super.verifyCommonData();
		super.verifyTotalAmount();
		return this;
	}

	@Override
	protected EcRefundOrderBO initRefundNo() {
		super.refundOrderDTO.setRefundNo(EcWxOrderNoGenerator.generateWxOrderNO(EcWxTransactionType.REFUND));
		return this;
	}


}
