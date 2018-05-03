package com.easy.cloud.pay.wx.pojo.dto;

import com.easy.cloud.pay.core.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.core.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;

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
public class EcWxRefundOrderDTO extends EcRefundOrderAbstractDTO {
	@Override
	public void buildSignatureParameters(EcPayConfigStorageInf dqPayConfigStorage, EcTransactionType ecTransactionType) {

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
