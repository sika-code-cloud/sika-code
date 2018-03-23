package com.dq.easy.cloud.pay.wx.pojo.dto;

import com.dq.easy.cloud.pay.module.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.module.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.module.transaction.inf.DqTransactionType;

/**
 * 
 * <p>
 * 微信退款订单数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月16日 下午6:47:06
 */
public class DqWxRefundOrderDTO extends DqRefundOrderAbstractDTO {
	@Override
	public void buildSignatureParameters(DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {

	}

	@Override
	protected void putRefundNoSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTradeNoSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putOutTradeNoSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putRefundAmountSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTotalAmountSignData() {
		// TODO Auto-generated method stub
		
	}


}
