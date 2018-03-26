package com.dq.easy.cloud.pay.zfb.pojo.bo;

import com.dq.easy.cloud.pay.common.payment.pojo.bo.DqRefundOrderBO;
import com.dq.easy.cloud.pay.common.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.zfb.common.utils.DqZfbPayUtils.DqZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝退款订单逻辑对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月1日 下午4:20:22
 */
public class DqZfbRefundOrderBO extends DqRefundOrderBO{
	public DqZfbRefundOrderBO(DqRefundOrderAbstractDTO dqRefundOrderDTO) {
		super(dqRefundOrderDTO);
	}

	@Override
	public DqRefundOrderBO verifyRefundData() {
		return verifyCommonData();
	}

	@Override
	protected DqRefundOrderBO initRefundNo() {
		super.dqRefundOrderDTO.setRefundNo(DqZfbOrderNoGenerator.generateZfbOrderNO(DqZfbTransactionType.REFUND));
		return this;
	}

}
