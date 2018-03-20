package com.dq.easy.cloud.pay.zfb.pojo.query;

import java.util.TimeZone;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.model.payment.pojo.query.DqOrderAbstractQuery;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.zfb.common.utils.DqZfbPayUtils.DqZfbSignUtils;

/**
 * 
 * <p>
 * 支付宝订单查询对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月20日 下午8:10:31
 */
public class DqZfbOrderQuery extends DqOrderAbstractQuery{

	@Override
	protected void putBillDateTimestampSignData() {
		putBillDateSignData();
		
	}

	@Override
	protected void putPayIdSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTradeNoSignData() {
		super.putContentSignatureData(DqZfbPayKey.TRADE__NO_KEY, getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		super.putContentSignatureData(DqZfbPayKey.OUT__TRADE__NO_KEY, getOutTradeNo());
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
		String fomatDate = DqDateFormatUtils.format(getBillDate(), DqDateFormatUtils.FORMAT_NORMAL_DAY, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		putContentSignatureData(DqZfbPayKey.BILL__DATE_KEY, fomatDate);
	}

	@Override
	protected void putBillTypeSignData() {
		putContentSignatureData(DqZfbPayKey.BILL__TYPE_KEY, getBillType());
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
		super.putContentSignatureData(DqZfbPayKey.OUT__REQUEST__NO_KEY, getRefundTradeNo());
	}

	@Override
	public void buildSignatureParameters(DqPayConfigStorageInf dqPayConfigStorage,
			DqTransactionType dqTransactionType) {
		super.setSignatureParameters(DqZfbSignUtils.buildSignatureParameters(contentSignatureMap, dqPayConfigStorage, dqTransactionType));
	}
	
}
