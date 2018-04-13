package com.easy.cloud.pay.wx.pojo.query;

import com.easy.cloud.pay.common.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;

public class EcWxOrderQuery extends EcOrderAbstractQuery{

	@Override
	protected void putBillDateTimestampSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putPayIdSignData() {
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

	@Override
	protected void putBillDateSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putBillTypeSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTradeNoOrBillDateSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putOutTradeNoBillTypeSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putRefundTradeNoSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSignatureParameters(EcPayConfigStorageInf dqPayConfigStorage,
			EcTransactionType ecTransactionType) {
		// TODO Auto-generated method stub
		
	}

}
