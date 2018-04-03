package com.easy.cloud.pay.zfb.pojo.bo;

import com.easy.cloud.pay.common.payment.pojo.bo.EcRefundOrderBO;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.zfb.common.utils.EcZfbPayUtils.EcZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝退款订单逻辑对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月1日 下午4:20:22
 */
public class EcZfbRefundOrderBO extends EcRefundOrderBO{
	public EcZfbRefundOrderBO(EcRefundOrderAbstractDTO dqRefundOrderDTO) {
		super(dqRefundOrderDTO);
	}

	@Override
	public EcRefundOrderBO verifyRefundData() {
		return verifyCommonData();
	}

	@Override
	protected EcRefundOrderBO initRefundNo() {
		super.refundOrderDTO.setRefundNo(EcZfbOrderNoGenerator.generateZfbOrderNO(EcZfbTransactionType.REFUND));
		return this;
	}

}
